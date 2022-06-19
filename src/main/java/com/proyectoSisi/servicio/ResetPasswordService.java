package com.proyectoSisi.servicio;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Usuario;

@Service
@Transactional
public class ResetPasswordService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public void updateResetPasswordToken(String token, String email) throws UsuarioNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail2(email);
        if (usuario != null) {
        	usuario.setResetPasswordToken(token);
            usuarioRepositorio.save(usuario);
        } else {
            throw new UsuarioNotFoundException("No se pudo encontrar ningún usuario con el correo electrónico. " + email);
        }
    }
     
    public Usuario getByResetPasswordToken(String token) {
        return usuarioRepositorio.findByResetPasswordToken(token);
    }
     
    public void updatePassword(Usuario usuario, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        usuario.setPassword(encodedPassword);
         
        usuario.setResetPasswordToken(null);
        usuarioRepositorio.save(usuario);
    }

}
