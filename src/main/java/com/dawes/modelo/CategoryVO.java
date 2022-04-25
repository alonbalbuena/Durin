package com.dawes.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class CategoryVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;
	private String nameCategory;
	
	@OneToMany(mappedBy = "categoryId",cascade = CascadeType.ALL)
	private List<PostCategoriaVO> posts;
	
	public CategoryVO(int categoryId, String nameCategory) {
		this.categoryId = categoryId;
		this.setNameCategory(nameCategory);
		posts = new ArrayList<PostCategoriaVO>();
	}

	public CategoryVO(String nameCategory) {
		this.setNameCategory(nameCategory);
		posts = new ArrayList<PostCategoriaVO>();
	}

	public CategoryVO() {
	}

	public String getNameCategory() {
		return nameCategory;
	}

	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public List<PostCategoriaVO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostCategoriaVO> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryVO other = (CategoryVO) obj;
		if (categoryId != other.categoryId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoryVO [categoryId=" + categoryId + ", nameCategory=" + nameCategory + ", posts=" + posts + "]";
	}
	
	
}
