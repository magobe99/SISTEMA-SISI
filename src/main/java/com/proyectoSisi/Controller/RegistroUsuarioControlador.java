package com.proyectoSisi.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectoSisi.Controller.dto.UsuarioRegistroDTO;
import com.proyectoSisi.model.Rol;
import com.proyectoSisi.Repository.RolRepositorio;
import com.proyectoSisi.servicio.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	@Autowired
	private RolRepositorio rolRepositorio;
	
	private UsuarioServicio usuarioServicio;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro(Model modelo) {
		Collection<Rol> listaRoles = rolRepositorio.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		return "Usuarios/registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		usuarioServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
