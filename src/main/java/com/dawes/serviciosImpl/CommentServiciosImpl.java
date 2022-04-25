package com.dawes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.dao.CommentDAO;
import com.dawes.modelo.CommentVO;
import com.dawes.modelo.PostVO;
import com.dawes.servicios.CommentServicios;
@Service
public class CommentServiciosImpl implements CommentServicios {

	@Autowired
	CommentDAO cd;

	@Override
	public <S extends CommentVO> S save(S entity) {
		return cd.save(entity);
	}

	@Override
	public Optional<CommentVO> findById(Integer id) {
		return cd.findById(id);
	}

	@Override
	public Iterable<CommentVO> findAll() {
		return cd.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		cd.deleteById(id);
	}

	@Override
	public void delete(CommentVO entity) {
		cd.delete(entity);
	}

	@Override
	public void deleteAll() {
		cd.deleteAll();
	}

	@Override
	public CommentVO findByComment(String comment) {
		return cd.findByComment(comment);
	}


	public List<CommentVO> findByPostId(PostVO postId) {
		return cd.findByPostId(postId);
	}
	
	
}
