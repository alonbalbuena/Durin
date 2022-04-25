package com.dawes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.RolVO;

@Repository
public interface RolDAO extends CrudRepository<RolVO, Integer> {
	RolVO findByNombreRol(String nombreRol);
}
