package com.dawes.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comentarios")
public class CommentVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private UsuarioVO userId;
	@ManyToOne
	@JoinColumn(name = "postId")
	private PostVO postId;
	
	private String comment;
	private LocalDate create_time;

	public CommentVO(int commentId, UsuarioVO userId, PostVO postId, String comment, LocalDate create_time) {
		this.commentId = commentId;
		this.userId = userId;
		this.postId = postId;
		this.comment = comment;
		this.create_time = create_time;
	}

	public CommentVO() {
	}

	public CommentVO(UsuarioVO userId, PostVO postId, String comment, LocalDate create_time) {
		this.userId = userId;
		this.postId = postId;
		this.comment = comment;
		this.create_time = create_time;
	}

	public UsuarioVO getUserId() {
		return userId;
	}

	public void setUserId(UsuarioVO userId) {
		this.userId = userId;
	}

	public PostVO getPostId() {
		return postId;
	}

	public void setPostId(PostVO postId) {
		this.postId = postId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getCreate_time() {
		return create_time;
	}

	public void setCreate_time(LocalDate create_time) {
		this.create_time = create_time;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId;
		result = prime * result + ((create_time == null) ? 0 : create_time.hashCode());
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
		CommentVO other = (CommentVO) obj;
		if (commentId != other.commentId)
			return false;
		if (create_time == null) {
			if (other.create_time != null)
				return false;
		} else if (!create_time.equals(other.create_time))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentVO [commentId=" + commentId + ", userId=" + userId + ", postId=" + postId + ", comment="
				+ comment + ", create_time=" + create_time + "]";
	}
	
	
}
