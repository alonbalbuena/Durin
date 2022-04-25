package com.dawes.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.CategoryVO;
import com.dawes.modelo.CommentVO;
import com.dawes.modelo.PostCategoriaVO;
import com.dawes.modelo.PostVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.CategoryServiciosImpl;
import com.dawes.serviciosImpl.CommentServiciosImpl;
import com.dawes.serviciosImpl.PostCategoryServiciosImpl;
import com.dawes.serviciosImpl.PostServiciosImpl;
import com.dawes.serviciosImpl.UsuarioServiciosImpl;

@Controller
@RequestMapping("/")
public class Controlador {

	@Autowired
	private PostServiciosImpl servicioPost;
	@Autowired
	private CategoryServiciosImpl servicioCategoria;
	@Autowired
	private CommentServiciosImpl servicioComentarios;
	@Autowired
	private UsuarioServiciosImpl servicioUsuarios;
	@Autowired
	private PostCategoryServiciosImpl servicioCategoriaNoticia;

	@RequestMapping("")
	public String buscarNoticias(Model modelo, @AuthenticationPrincipal UsuarioVO usuario) {
		// informacion necesaria para pasar al inicio
		modelo.addAttribute("noticias", servicioPost.findAll());
		modelo.addAttribute("ultimaNoticia", servicioPost.findLast());
		modelo.addAttribute("usuario", usuario);
		return "inicio.html";
	}
	
	@RequestMapping("busqueda")
	public String inicio(Model modelo, @AuthenticationPrincipal UsuarioVO usuario,@RequestParam String q) {
		// informacion necesaria para pasar al inicio
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("busqueda", q);
		modelo.addAttribute("noticias", servicioPost.findByTitleContainingIgnoreCase(q));
		return "busqueda.html";
	}


	@RequestMapping("noticia")
	public String noticia(Model modelo, @RequestParam int postId, @AuthenticationPrincipal UsuarioVO usuario) {

		// informacion necesaria para pasar al inicio
		PostVO noticia = servicioPost.findById(postId).get();
		modelo.addAttribute("noticia", noticia);
		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("comentarios", servicioComentarios.findByPostId(noticia));
		modelo.addAttribute("comentarioNuevo", new CommentVO());

		return "noticia.html";
	}

	@RequestMapping("postear")
	public String posteo(Model modelo) {
		// informacion necesaria para pasar al post
		modelo.addAttribute("nuevoPost", new PostVO());
		// pasamos la lista de categorias existentes para crear el formulario
		modelo.addAttribute("categorias", servicioCategoria.findAll());
		// pasamos una categoria vacia que es la que vamos a usar en un futuro

		return "postear.html";
	}

	@RequestMapping("configurar")
	public String configurar(Model modelo, @AuthenticationPrincipal UsuarioVO usuario) {

		modelo.addAttribute("usuario", usuario);
		modelo.addAttribute("usuarioCambiado", new UsuarioVO());

		return "configurar.html";
	}

	@RequestMapping("permisos")
	public String darPermisos(Model modelo, @AuthenticationPrincipal UsuarioVO usuario) {
		modelo.addAttribute("usuarios", servicioUsuarios.findAll());
		modelo.addAttribute("usuario", usuario);

		return "permisos.html";
	}

	@RequestMapping("categoria")
	public String noticia(Model modelo, @RequestParam Integer categoriaId, @AuthenticationPrincipal UsuarioVO usuario) {

		// informacion necesaria para pasar al inicio
		CategoryVO categoria = servicioCategoria.findById(categoriaId).get();
		modelo.addAttribute("categoria", categoria);

		//convertimos los valores de la tabla intermedio en noticias reales
		List<PostCategoriaVO> categoriaNoticiaFiltradas = servicioCategoriaNoticia.findByCategoryId(categoria);
		List<PostVO> noticiasFiltradas = new ArrayList<PostVO>();
		for (PostCategoriaVO categoriaNoticia : categoriaNoticiaFiltradas) {
			noticiasFiltradas.add(categoriaNoticia.getPostId());
		}
		
		// informacion necesaria para pasar al inicio
		modelo.addAttribute("noticias", noticiasFiltradas);//pasamos solo las noticias de cierta categoria
		modelo.addAttribute("usuario", usuario);
		return "categoria.html";
	}
}
