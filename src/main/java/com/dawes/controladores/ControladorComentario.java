package com.dawes.controladores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.CommentVO;
import com.dawes.modelo.PostVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.CommentServiciosImpl;
import com.dawes.serviciosImpl.PostServiciosImpl;

@Controller
@RequestMapping("/")
public class ControladorComentario {

	@Autowired
	private CommentServiciosImpl servicioComentarios;
	@Autowired
	private PostServiciosImpl servicioPosts;
	@Autowired
	private PostServiciosImpl servicioPost;
	
	@RequestMapping(value ="publicarComentario", method = RequestMethod.POST)
	public String publicarComentario(Model modelo,@RequestParam Integer postId,@ModelAttribute(name = "comentarioNuevo") CommentVO comentario,@AuthenticationPrincipal UsuarioVO usuario) {
		
		comentario.setCreate_time(LocalDate.now());
		//asignamos el comentario al usuario actual logeado
		comentario.setUserId(usuario);
		//asignamos el comentario a un post 
		comentario.setPostId(servicioPosts.findById(postId).get());

		servicioComentarios.save(comentario);
		
		//informacion necesaria para pasar al inicio
		PostVO noticia = servicioPost.findById(postId).get();
		modelo.addAttribute("noticia",noticia );
		modelo.addAttribute("usuario",usuario);
		modelo.addAttribute("comentarios",servicioComentarios.findByPostId(noticia));
		modelo.addAttribute("comentarioNuevo", new CommentVO());
		
		return "noticia.html";
	}
}
