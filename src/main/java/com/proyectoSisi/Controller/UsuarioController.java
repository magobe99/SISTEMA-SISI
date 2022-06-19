package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSisi.Repository.RolRepositorio;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Rol;
import com.proyectoSisi.model.Usuario;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Autowired
	private RolRepositorio rolRepository;
	
	
	
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model modelo) {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		
		return "Usuarios/list_usuarios";
	}
	
	
	
	@PostMapping("/usuarios/guardar")
	public String guardarUsuario(Usuario usuario,RedirectAttributes redirectAttrs) {
		usuarioRepository.save(usuario);
		redirectAttrs
        .addFlashAttribute("mensaje", "El usuario fue actualizado correctamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String mostarFormularioDeModificarUsuario(@PathVariable("id") Long id,Model modelo) {
		Usuario usuario =usuarioRepository.findById(id).get();
		modelo.addAttribute("usuario", usuario);
		
		List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		
		return "Usuarios/new_user";
	}
	
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") Long id,Model modelo,RedirectAttributes redirectAttrs) {
		usuarioRepository.deleteById(id);
		redirectAttrs
        .addFlashAttribute("mensaje", "Usuario eliminado exitosamente")
        .addFlashAttribute("clase", "danger");
		return "redirect:/usuarios";
		
	}
	

}
