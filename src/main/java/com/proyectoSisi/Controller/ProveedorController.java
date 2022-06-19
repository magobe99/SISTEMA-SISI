package com.proyectoSisi.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSisi.Repository.ProveedorRepository;
import com.proyectoSisi.model.Proveedor;

@Controller
public class ProveedorController {

	@Autowired
	private ProveedorRepository proveedorRepository;
	

	@GetMapping("/proveedores")
	public String listarProveedores(Model modelo) {
		List<Proveedor> listarProveedores = proveedorRepository.findAll();
		modelo.addAttribute("listarProveedores", listarProveedores);
		
		return "Proveedores/list_proveedor";
		
	}
	
	@GetMapping("proveedores/nuevo")
	public String mostrarFormularioDeNuevoProveedor(Model modelo) {
		modelo.addAttribute("proveedor", new Proveedor());
		
		return "Proveedores/new_proveedor";
	}
	
	@PostMapping("proveedores/guardar")
	public String guardarProveedor(Proveedor proveedor,RedirectAttributes redirectAttrs) {
		

		
		proveedorRepository.save(proveedor);
		redirectAttrs
        .addFlashAttribute("mensaje", "Proveedor registrado correctamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/proveedores";
		
	}
	
	
	
	@GetMapping("/proveedores/editar/{id}")
	public String mostrarFormularioDeModificarProveedor(@PathVariable("id") Integer id,Model modelo) {
		Proveedor proveedor = proveedorRepository.findById(id).get();
		modelo.addAttribute("proveedor", proveedor);
		
		
		return "Proveedores/new_proveedor";
	}
	
	@GetMapping("/proveedores/eliminar/{id}")
	public String eliminarProveedor(@PathVariable("id") Integer id,Model modelo,RedirectAttributes redirectAttrs) {
		proveedorRepository.deleteById(id);
		redirectAttrs
        .addFlashAttribute("mensaje", "Proveedor eliminado exitosamente")
        .addFlashAttribute("clase", "danger");
		return "redirect:/proveedores";
	}
	
	
	
}
