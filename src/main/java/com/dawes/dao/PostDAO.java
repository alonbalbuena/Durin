package com.dawes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.PostVO;

//a la interfaz CRUDREPOSITORY le pasamos la clase que vamos a usar y el tipo del id 
@Repository
public interface PostDAO extends CrudRepository<PostVO, Integer> {
	PostVO findByTitle(String title);
	
	@Query(value = "SELECT * FROM posts WHERE create_time = (SELECT MAX(create_time) FROM posts)", nativeQuery = true)
	PostVO findLast();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE PostVO u SET u.title = :newTitle, u.subtitle = :newSubtitle, u.content = :newContent, u.imagePath = :newImagePath WHERE u.postId = :id", nativeQuery = true)
	void actualizarPost(@Param("id") Integer id,@Param("newTitle") String newTitle,@Param("newSubtitle") String newSubtitle,@Param("newContent") String newContent,@Param("newImagePath") String newImagePath);

	// igual que : SELECT u FROM PostVO u WHERE u.title like '%:query%'
	List<PostVO> findByTitleContainingIgnoreCase(String query);
}
