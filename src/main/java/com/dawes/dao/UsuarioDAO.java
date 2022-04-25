package com.dawes.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dawes.modelo.RolVO;
import com.dawes.modelo.UsuarioVO;

@Repository
public interface UsuarioDAO extends CrudRepository<UsuarioVO, Integer> {
	UsuarioVO findByUserName(String userName);
	
	@Modifying
	@Transactional
	@Query("UPDATE UsuarioVO u SET u.userName = :newUserName, u.email = :newEmail, u.password = :newPassword, u.avatar_path = :newAvatar WHERE u.idUsuario = :id")
	void actualizarUsuario(@Param("id") Integer id,@Param("newUserName") String userName,@Param("newEmail") String email,@Param("newPassword") String password,@Param("newAvatar") String avatar);
	
	@Modifying
	@Transactional
	@Query("UPDATE UsuarioVO u SET u.rol = :idrol WHERE u.idUsuario = :id")
	void cambiarRol(@Param("id") Integer idUsuario,@Param("idrol") RolVO rol);
}
