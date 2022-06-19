package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSisi.Repository.ClienteRepository;
import com.proyectoSisi.model.Cliente;


@Controller
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	

	
	@GetMapping("/clientes")
	public String listarClientes(Model modelo) {
		List<Cliente> listarClientes = clienteRepository.findAll();
		modelo.addAttribute("listarClientes", listarClientes);
		
		return "Clientes/list_client";
		
	}
	
	@GetMapping("clientes/nuevo")
	public String mostrarFormularioDeNuevoCliente(Model modelo) {
		modelo.addAttribute("cliente", new Cliente());
		
		return "Clientes/new_client";
	}
	
	@PostMapping("clientes/guardar")
	public String guardarCliente(Cliente cliente,RedirectAttributes redirectAttrs) {
		clienteRepository.save(cliente);
		redirectAttrs
        .addFlashAttribute("mensaje", "Cliente registrado correctamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/clientes";
		
	}
	
	
	
	@GetMapping("/clientes/editar/{id}")
	public String mostrarFormularioDeModificarCliente(@PathVariable("id") Integer id,Model modelo) {
		Cliente cliente = clienteRepository.findById(id).get();
		modelo.addAttribute("cliente", cliente);
		
		
		return "Clientes/new_client";
	}
	
	
	@GetMapping("/clientes/eliminar/{id}")
	public String eliminarCliente(@PathVariable("id") Integer id,Model modelo,RedirectAttributes redirectAttrs) {
		clienteRepository.deleteById(id);
		redirectAttrs
        .addFlashAttribute("mensaje", "Cliente eliminado exitosamente")
        .addFlashAttribute("clase", "danger");
		return "redirect:/clientes";
		
	}
	
	
}
