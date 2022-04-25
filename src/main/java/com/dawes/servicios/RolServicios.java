package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.RolVO;

public interface RolServicios {

	<S extends RolVO> S save(S entity);

	Optional<RolVO> findById(Integer id);

	Iterable<RolVO> findAll();

	void deleteById(Integer id);

	void delete(RolVO entity);

	void deleteAll();

	RolVO findByNombreRol(String nombreRol);

}