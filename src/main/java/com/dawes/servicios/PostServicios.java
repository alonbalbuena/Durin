package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.PostVO;

public interface PostServicios {

	<S extends PostVO> S save(S entity);

	Optional<PostVO> findById(Integer id);

	Iterable<PostVO> findAll();

	void deleteById(Integer id);

	void delete(PostVO entity);

	void deleteAll();

	PostVO findByTitle(String title);

}