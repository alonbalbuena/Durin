package com.dawes.serviciosImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dawes.dao.UsuarioDAO;
import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;
import com.dawes.servicios.UsuariosServicios;
@Service
public class UsuarioServiciosImpl implements UsuariosServicios {

	@Autowired
	UsuarioDAO ud;
	@Autowired
	private PasswordEncoder codificador;
	@Autowired
	private RolServiciosImpl rolServicio;

	@Override
	public <S extends UsuarioVO> S save(S entity) {
		return ud.save(entity);
	}

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return ud.findById(id);
	}

	@Override
	public Iterable<UsuarioVO> findAll() {
		return ud.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		ud.deleteById(id);
	}

	@Override
	public void delete(UsuarioVO entity) {
		ud.delete(entity);
	}

	@Override
	public void deleteAll() {
		ud.deleteAll();
	}

	@Override
	public UsuarioVO findByUserName(String userName) {
		return ud.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return ud.findByUserName(username);
	}
	
	//implementado de passwordDecoder
	//Crearemos un nuevo usuario con la contraseña codificada y le asignamos un rol
	public UsuarioVO crearNuevoUsuario(UsuarioVO usuarioNuevo) {
		usuarioNuevo.setPassword(codificador.encode(usuarioNuevo.getPassword()) );
		//asignamos un rol ya existente previamente en la base de datos
		usuarioNuevo.setRol(rolServicio.findByNombreRol("usuario"));
		return usuarioNuevo;
	}

	public void actualizarUsuario(Integer id, String userName, String email, String password, String avatar) {
		ud.actualizarUsuario(id, userName, email, password, avatar);
	}

	public void cambiarRol(Integer idUsuario, RolVO idRol) {
		ud.cambiarRol(idUsuario, idRol);
	}
	
	
	
}
