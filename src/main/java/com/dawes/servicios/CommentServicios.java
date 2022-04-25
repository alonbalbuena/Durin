package com.dawes.servicios;

import java.util.Optional;

import com.dawes.modelo.CommentVO;

public interface CommentServicios {

	<S extends CommentVO> S save(S entity);

	Optional<CommentVO> findById(Integer id);

	Iterable<CommentVO> findAll();

	void deleteById(Integer id);

	void delete(CommentVO entity);

	void deleteAll();

	CommentVO findByComment(String comment);

}