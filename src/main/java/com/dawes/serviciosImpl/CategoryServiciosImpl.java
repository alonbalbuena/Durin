package com.dawes.serviciosImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.dao.CategoryDAO;
import com.dawes.modelo.CategoryVO;
import com.dawes.servicios.CategoryService;

@Service
public class CategoryServiciosImpl implements CategoryService{

	@Autowired
	CategoryDAO cd;

	@Override
	public <S extends CategoryVO> S save(S entity) {
		return cd.save(entity);
	}

	@Override
	public <S extends CategoryVO> Iterable<S> saveAll(Iterable<S> entities) {
		return cd.saveAll(entities);
	}

	@Override
	public Optional<CategoryVO> findById(Integer id) {
		return cd.findById(id);
	}

	@Override
	public Iterable<CategoryVO> findAll() {
		return cd.findAll();
	}

	@Override
	public void deleteById(Integer id) {
		cd.deleteById(id);
	}

	@Override
	public void delete(CategoryVO entity) {
		cd.delete(entity);
	}

	public CategoryVO findByNameCategory(String nameCategory) {
		return cd.findByNameCategory(nameCategory);
	}
	
	
}
