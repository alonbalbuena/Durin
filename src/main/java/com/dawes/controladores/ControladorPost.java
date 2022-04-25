package com.dawes.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.CategoryVO;
import com.dawes.modelo.PostCategoriaVO;
import com.dawes.modelo.PostVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.CategoryServiciosImpl;
import com.dawes.serviciosImpl.PostServiciosImpl;

@Controller
@RequestMapping("/")
public class ControladorPost {

	@Autowired
	private PostServiciosImpl servicioPost;
	@Autowired
	private CategoryServiciosImpl servicioCategorias;

	@Transactional
	@RequestMapping(value = "crearPost", method = RequestMethod.POST)
	public String noticia(Model modelo, @ModelAttribute PostVO nuevoPost, @AuthenticationPrincipal UsuarioVO usuario,
			@RequestParam(name = "categoria") Integer categoriaEscogidaId) {

		// asignamos la hora actual ya que no la metemos por el formulario
		// asignamos la noticia al usuario actualmente logeado
		// introducimos el post en la base de datos para luego poder unirla en la
		// intermedia
		nuevoPost.setCreateTime(LocalDate.now());
		nuevoPost.setUserId(usuario);
		
		Optional<CategoryVO> categoriaEscogida = servicioCategorias.findById(Integer.valueOf(categoriaEscogidaId));
		//al añadirle la categoria al post y tenerlo en cascada se crearan solo en la tabla intermedia
		nuevoPost.setCategorias(new ArrayList<PostCategoriaVO>());
		nuevoPost.getCategorias().add(new PostCategoriaVO(nuevoPost,categoriaEscogida.get()));
		
		servicioPost.save(nuevoPost);

		// informacion necesaria para pasar al inicio
		modelo.addAttribute("noticias", servicioPost.findAll());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("ultimaNoticia", servicioPost.findLast());

		return "inicio.html";
	}

	@RequestMapping("editarNoticia")
	public String editar(Model modelo, @RequestParam int postId) {

		// informacion necesaria para pasar al post
		modelo.addAttribute("nuevoPost", servicioPost.findById(postId).get());
		// pasamos la lista de categorias existentes para crear el formulario
		modelo.addAttribute("categorias", servicioCategorias.findAll());
		// pasamos una categoria vacia que es la que vamos a usar en un futuro

		return "postear.html";
	}

	@RequestMapping(value = "actualizarNoticia", method = RequestMethod.POST)
	public String actualizarBaseDatos(Model modelo, @ModelAttribute PostVO nuevoPost,
			@AuthenticationPrincipal UsuarioVO usuario) {

		servicioPost.actualizarPost(nuevoPost.getPostId(), nuevoPost.getTitle(),
				nuevoPost.getSubtitle(), nuevoPost.getContent(), nuevoPost.getImagePath());

		// informacion necesaria para pasar al inicio
		modelo.addAttribute("noticias", servicioPost.findAll());
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("ultimaNoticia", servicioPost.findLast());

		return "inicio.html";
	}

}
