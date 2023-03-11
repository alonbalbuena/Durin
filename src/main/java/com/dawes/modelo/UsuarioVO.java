package com.dawes.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuarios")
public class UsuarioVO implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	private String userName;
	private String email;
	private String password;
	private LocalDate create_time;
	private String avatar_path;

	@OneToMany(mappedBy = "userId")
	private List<PostVO> posts;

	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "rol")
	private RolVO rol;

	public UsuarioVO() {
	}
	
	public UsuarioVO(PostUsuarioDTO usuarioDTO) {
		this.userName = usuarioDTO.getUsuario();
		this.password = usuarioDTO.getContraseña();
		posts = new ArrayList<PostVO>();
	}

	public UsuarioVO(String userName, String email, String password, LocalDate create_time, String avatar_path, List<PostVO> posts,RolVO rol) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.create_time = create_time;
		this.avatar_path = avatar_path;
		this.posts = posts;
		this.rol = rol;
	}

	public UsuarioVO(int idUsuario, String userName, String email, String password, LocalDate create_time,
			String avatar_path,List<PostVO> posts,RolVO rol) {
		this.idUsuario = idUsuario;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.create_time = create_time;
		this.avatar_path = avatar_path;
		this.posts = posts;
		this.rol = rol;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreate_time() {
		return create_time;
	}

	public void setCreate_time(LocalDate create_time) {
		this.create_time = create_time;
	}

	public String getAvatar_path() {
		return avatar_path;
	}

	public void setAvatar_path(String avatar_path) {
		this.avatar_path = avatar_path;
	}
	
	public RolVO getRol() {
		return rol;
	}

	public void setRol(RolVO rol) {
		this.rol = rol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
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
		UsuarioVO other = (UsuarioVO) obj;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioVO [idUsuario=" + idUsuario + ", userName=" + userName + ", email=" + email + ", password="
				+ password + ", create_time=" + create_time + ", avatar_path=" + avatar_path + ",posts " + posts + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// @formatter:off
		Set<RolVO> roles = new HashSet<RolVO>();
		roles.add(rol);
		return roles
				.stream()
				.map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombreRol()))
				.collect(Collectors.toList());
		// @formatter:on
	}

	@Override
	public String getUsername() {
		return userName;
	}
	
	public void setUsername(String userName) {
		this.userName = userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
