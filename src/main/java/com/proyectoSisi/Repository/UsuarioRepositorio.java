package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{
	
	
	public Usuario findByEmail(String email);
	
	public Usuario getUserByEmail(String email);
	
	@Query("SELECT c FROM Usuario c WHERE c.email = ?1")
	public Usuario findByEmail2(String email);
	
	public Usuario findByResetPasswordToken(String token);
	
	
}
