package com.dawes.modelo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "posts")
public class PostVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String title;
	private String subtitle;
	@Column(columnDefinition="content TEXT")
	private String content;
	private String imagePath;
	private LocalDate createTime;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	private UsuarioVO userId;
	
	@OneToMany(mappedBy = "postId",cascade = CascadeType.ALL)
	private List<PostCategoriaVO> categorias;

	public PostVO(int postId, String title, String subtitle, String content, String imagePath, LocalDate createTime,
			UsuarioVO userId,List<PostCategoriaVO> categorias) {
		this.postId = postId;
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.imagePath = imagePath;
		this.createTime = createTime;
		this.userId = userId;
		this.categorias = categorias;
		
	}

	public PostVO(String title, String subtitle, String content, String imagePath, LocalDate createTime, UsuarioVO userId,List<PostCategoriaVO> categorias) {
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.imagePath = imagePath;
		this.createTime = createTime;
		this.userId = userId;
		this.categorias = categorias;
	}

	public PostVO() {
	}

	
	
	public List<PostCategoriaVO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<PostCategoriaVO> categorias) {
		this.categorias = categorias;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public LocalDate getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}

	public UsuarioVO getUserId() {
		return userId;
	}

	public void setUserId(UsuarioVO userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postId;
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
		PostVO other = (PostVO) obj;
		if (postId != other.postId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PostVO [postId=" + postId + ", title=" + title + ", subtitle=" + subtitle + ", content=" + content
				+ ", imagePath=" + imagePath + ", createTime=" + createTime + ", userId=" + userId + "]";
	}
	
	
	
}
