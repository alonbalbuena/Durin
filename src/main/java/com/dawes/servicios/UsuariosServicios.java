package com.dawes.servicios;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dawes.modelo.UsuarioVO;

public interface UsuariosServicios extends UserDetailsService{

	<S extends UsuarioVO> S save(S entity);

	Optional<UsuarioVO> findById(Integer id);

	Iterable<UsuarioVO> findAll();

	void deleteById(Integer id);

	void delete(UsuarioVO entity);

	void deleteAll();

	UsuarioVO findByUserName(String userName);

}