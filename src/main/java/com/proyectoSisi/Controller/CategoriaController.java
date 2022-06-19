package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSisi.Repository.CategoriaRepository;
import com.proyectoSisi.Repository.Tipo_productoRepository;
import com.proyectoSisi.model.Categoria;
import com.proyectoSisi.model.Tipo_producto;

@Controller
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private Tipo_productoRepository tipo_productoRepository;
	
	@GetMapping("/categorias")
	public String listarCategorias(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		modelo.addAttribute("listaCategorias", listaCategorias);
		return "Inventarios/Categorias/list_category";
	}
	
	@GetMapping("/categorias/nuevo")
	public String mostrarFormularioDeNuevaCategoria(Model modelo) {
		List<Tipo_producto> listaTipo_producto = tipo_productoRepository.findAll();
		modelo.addAttribute("listaTipo_producto", listaTipo_producto);
		modelo.addAttribute("categoria", new Categoria());
		return "Inventarios/Categorias/new_categori";
	}
	
	@PostMapping("/categorias/guardar")
	public String guardarCategoria(Categoria categoria,RedirectAttributes redirectAttrs) {
		categoriaRepository.save(categoria);
		redirectAttrs
        .addFlashAttribute("mensaje", "Categoria registrada correctamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/categorias";
	}
	
	@GetMapping("/categorias/editar/{id_Categoria}")
	public String mostarFormularioDeModificarCategoria(@PathVariable("id_Categoria") Integer id_Categoria,Model modelo) {
		Categoria categoria = categoriaRepository.findById(id_Categoria).get();
		modelo.addAttribute("categoria", categoria);
		
		List<Tipo_producto> listaTipo_producto = tipo_productoRepository.findAll();
		modelo.addAttribute("listaTipo_producto", listaTipo_producto);
		
		return "Inventarios/Categorias/new_categori";
	}
	
	
	@GetMapping("/categorias/eliminar/{id_Categoria}")
	public String eliminarcategoria(@PathVariable("id_Categoria") Integer id_Categoria,Model modelo,RedirectAttributes redirectAttrs) {
		categoriaRepository.deleteById(id_Categoria);
		redirectAttrs
        .addFlashAttribute("mensaje", "Categoria eliminada exitosamente")
        .addFlashAttribute("clase", "danger");
		return "redirect:/categorias";
		
	}
	
}
