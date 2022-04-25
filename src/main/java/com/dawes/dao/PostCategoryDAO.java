package com.dawes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.CategoryVO;
import com.dawes.modelo.PostCategoriaVO;
import com.dawes.modelo.PostVO;
@Repository

public interface PostCategoryDAO extends CrudRepository<PostCategoriaVO, Integer> {
	PostCategoriaVO findByPostIdAndCategoryId(PostVO postId,CategoryVO categoryId);

	List<PostCategoriaVO> findByCategoryId(CategoryVO categoria);
}
