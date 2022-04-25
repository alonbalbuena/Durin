package com.dawes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.CommentVO;
import com.dawes.modelo.PostVO;
@Repository

public interface CommentDAO extends CrudRepository<CommentVO, Integer> {

	CommentVO findByComment(String comment);
	
	List<CommentVO> findByPostId(PostVO postId);
}
