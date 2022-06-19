package com.proyectoSisi.servicio;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.proyectoSisi.Controller.dto.UsuarioRegistroDTO;
import com.proyectoSisi.model.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	
	public List<Usuario> listarUsuarios();
	
	
}
