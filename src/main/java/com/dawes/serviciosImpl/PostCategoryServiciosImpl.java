package com.dawes.serviciosImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.dao.PostCategoryDAO;
import com.dawes.modelo.CategoryVO;
import com.dawes.modelo.PostCategoriaVO;
import com.dawes.modelo.PostVO;
import com.dawes.servicios.PostCategoryServicios;
@Service
public class PostCategoryServiciosImpl implements PostCategoryServicios {

	@Autowired
	PostCategoryDAO pd;

	@Override
	public <S extends PostCategoriaVO> S save(S entity) {
		return pd.save(entity);
	}

	@Override
	public Optional<PostCategoriaVO> findById(Integer id) {
		return pd.findById(id);
	}

	@Override
	public Iterable<PostCategoriaVO> findAll() {
		return pd.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		pd.deleteById(id);
	}

	@Override
	public void delete(PostCategoriaVO entity) {
		pd.delete(entity);
	}

	@Override
	public void deleteAll() {
		pd.deleteAll();
	}

	public PostCategoriaVO findByPostIdAndCategoryId(PostVO postId, CategoryVO categoryId) {
		return pd.findByPostIdAndCategoryId(postId, categoryId);
	}

	public List<PostCategoriaVO> findByCategoryId(CategoryVO categoria) {
		return pd.findByCategoryId(categoria);
	}
	
	
}
