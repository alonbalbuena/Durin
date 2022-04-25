package com.dawes.controladores;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.modelo.PostUsuarioDTO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.RolServiciosImpl;
import com.dawes.serviciosImpl.UsuarioServiciosImpl;

@Controller
@RequestMapping("/")
public class ControladorRegistro {
	
	@Autowired
	private UsuarioServiciosImpl servicioUser;
	@Autowired
	private RolServiciosImpl servicioRol;

	
	@RequestMapping("registro")
	public String registro(Model modelo) {
		modelo.addAttribute("usuarioNuevo",new PostUsuarioDTO());
		return "registro.html";
	}
	
	
	@RequestMapping(value = "crearUsuario", method = RequestMethod.POST)
	public String registro(@ModelAttribute(name = "usuarioNuevo")PostUsuarioDTO usuarioNuevoDTO,Model modelo) {
		//verificamos que el usuario no existe ya antes de introducirlo
		if (servicioUser.findByUserName(usuarioNuevoDTO.getUsuario()) == null){
			
			UsuarioVO nuevoUsuario = new UsuarioVO(usuarioNuevoDTO);
			nuevoUsuario.setCreate_time(LocalDate.now());
			nuevoUsuario.setAvatar_path("img/avatar.png");
			
			servicioUser.save(servicioUser.crearNuevoUsuario(nuevoUsuario));
			modelo.addAttribute("mensaje", "El usuario ha sido creado satisfactoriamente");
		}else {
			modelo.addAttribute("mensaje", "El usuario ya existe");
		}
		
		servicioUser.findById(1).get().setRol(servicioRol.findById(2).get());
		
		return "registro.html";
	}
}
