package com.dawes.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RolVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rolId;
	private String nombreRol;
	
	@OneToMany(mappedBy = "rol")
	private List<UsuarioVO> usuarios;
	
	public RolVO(int rolId, String nombreRol) {
		this.rolId = rolId;
		this.nombreRol = nombreRol;
		usuarios = new ArrayList<UsuarioVO>();
	}
	public RolVO(String nombreRol) {
		this.nombreRol = nombreRol;
		usuarios = new ArrayList<UsuarioVO>();
	}
	public RolVO() {
	}
	public int getRolId() {
		return rolId;
	}
	public void setRolId(int rolId) {
		this.rolId = rolId;
	}
	public String getNombreRol() {
		return nombreRol;
	}
	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
	
	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rolId;
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
		RolVO other = (RolVO) obj;
		if (rolId != other.rolId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "RolVO [rolId=" + rolId + ", nombreRol=" + nombreRol + "]";
	}
	
	
}
