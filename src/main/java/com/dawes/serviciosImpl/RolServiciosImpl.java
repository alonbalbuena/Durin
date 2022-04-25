package com.dawes.serviciosImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.dao.RolDAO;
import com.dawes.modelo.RolVO;
import com.dawes.servicios.RolServicios;
@Service
public class RolServiciosImpl implements RolServicios {

	@Autowired
	RolDAO rd;

	@Override
	public <S extends RolVO> S save(S entity) {
		return rd.save(entity);
	}

	@Override
	public Optional<RolVO> findById(Integer id) {
		return rd.findById(id);
	}

	@Override
	public Iterable<RolVO> findAll() {
		return rd.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		rd.deleteById(id);
	}

	@Override
	public void delete(RolVO entity) {
		rd.delete(entity);
	}

	@Override
	public void deleteAll() {
		rd.deleteAll();
	}

	@Override
	public RolVO findByNombreRol(String nombreRol) {
		return rd.findByNombreRol(nombreRol);
	}
	
	
}
