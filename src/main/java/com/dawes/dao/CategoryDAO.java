package com.dawes.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.CategoryVO;

@Repository
public interface CategoryDAO extends CrudRepository<CategoryVO, Integer> {

	CategoryVO findByNameCategory(String nameCategory);
}
