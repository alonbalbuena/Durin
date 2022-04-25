package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.CategoryVO;

public interface CategoryService {

	<S extends CategoryVO> S save(S entity);

	<S extends CategoryVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<CategoryVO> findById(Integer id);

	Iterable<CategoryVO> findAll();

	void deleteById(Integer id);

	void delete(CategoryVO entity);

}