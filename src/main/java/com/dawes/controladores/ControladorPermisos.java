package com.dawes.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.serviciosImpl.RolServiciosImpl;
import com.dawes.serviciosImpl.UsuarioServiciosImpl;

@Controller
public class ControladorPermisos {

	@Autowired
	private UsuarioServiciosImpl servicioUsuarios;
	@Autowired
	private RolServiciosImpl servicioRol;
	
	@RequestMapping(value = "/cambiarPermisos",method = RequestMethod.POST)
	public String cambiarPermisos(Model modelo,@RequestParam("id") Integer idUsuario) {
		
		UsuarioVO usuarioSeleccionado = servicioUsuarios.findById(idUsuario).get();
		
		RolVO rolUsuario = servicioRol.findById(1).get();
		RolVO rolAdmin = servicioRol.findById(2).get();
		
		//cambiamos al otro rol
		//si tenia "usuario" le cambiamos a "admin" y viceversa
		if(usuarioSeleccionado.getRol().getRolId() == 1) {
			servicioUsuarios.cambiarRol(idUsuario, rolAdmin);
			modelo.addAttribute("usuarios", servicioUsuarios.findAll());
		}else {
			servicioUsuarios.cambiarRol(idUsuario, rolUsuario);
			modelo.addAttribute("usuarios", servicioUsuarios.findAll());
		}
		
		//campos necesarios para la vista
		return "permisos.html";
	}
}
