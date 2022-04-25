package com.dawes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.UsuarioServiciosImpl;

@Controller
public class ControlladorConfiguracion {

	@Autowired
	private UsuarioServiciosImpl usuarioServicio;
	@Autowired
	private PasswordEncoder codificador;

	@RequestMapping(value = "/cambiarConfiguracion", method = RequestMethod.POST)
	public String configurar(Model modelo, @AuthenticationPrincipal UsuarioVO usuario,
			@ModelAttribute("usuarioCambiado") UsuarioVO usuarioCambiado) {

		System.out.println(usuarioCambiado);
		
		usuarioCambiado.setIdUsuario(usuario.getIdUsuario());
		usuarioCambiado.setCreate_time(usuario.getCreate_time());

		if (usuarioCambiado.getUsername().isEmpty())
			usuarioCambiado.setUsername(usuario.getUsername());
		if (usuarioCambiado.getEmail().isEmpty())
			usuarioCambiado.setEmail(usuario.getEmail());
		//si la contraseña no la cambiamos no la volvemos a codificar
		if (usuarioCambiado.getPassword().isEmpty())
			usuarioCambiado.setPassword(usuario.getPassword());
		else usuarioCambiado.setPassword(codificador.encode(usuarioCambiado.getPassword()));
			
		if (usuarioCambiado.getAvatar_path().isEmpty())
			usuarioCambiado.setAvatar_path(usuario.getAvatar_path());

		System.out.println(usuarioCambiado);
		
		usuarioServicio.actualizarUsuario(usuarioCambiado.getIdUsuario(), usuarioCambiado.getUsername(),
				usuarioCambiado.getEmail(),usuarioCambiado.getPassword(), usuarioCambiado.getAvatar_path());

		//informacion necesaria para volver
		modelo.addAttribute("usuario", usuarioCambiado);
		modelo.addAttribute("usuarioCambiado",new UsuarioVO());
		
		return "configurar.html";
	}
}
