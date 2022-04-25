package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.PostCategoriaVO;

public interface PostCategoryServicios {

	<S extends PostCategoriaVO> S save(S entity);

	Optional<PostCategoriaVO> findById(Integer id);

	Iterable<PostCategoriaVO> findAll();

	void deleteById(Integer id);

	void delete(PostCategoriaVO entity);

	void deleteAll();

}