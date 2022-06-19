package com.proyectoSisi.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.proyectoSisi.Repository.CategoriaRepository;
import com.proyectoSisi.Repository.LoteRepository;
import com.proyectoSisi.Repository.Producto_procesadoRepository;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Categoria;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.Lote;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Usuario;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Producto_procesadoController {

	@Autowired
	private Producto_procesadoRepository producto_procesadoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;


	
	@GetMapping("/productosPRO")
	public String listarProductosPRO(Model modelo) {
		List<Producto_procesado> listarProductosPRO = producto_procesadoRepository.findAll();
		modelo.addAttribute("listarProductosPRO", listarProductosPRO);
		
		List<Categoria> listaCategorias = categoriaRepository.findByProductoProcesado();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		
		modelo.addAttribute("lote", new Lote());
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		
		return "Inventarios/PP/product_al";
		
	}
	
	
	/*Guardar Lote*/
	@PostMapping("productosPRO/guardarLote" )
	public String guardarLote(@RequestParam(name = "cantidad") Integer canti
			                 ,@RequestParam(name = "Prod_procesado") Integer code,
			                   Lote lote,RedirectAttributes redirectAttrs, BindingResult bindingResult) {
		
		// Obtener el producto fresco desde la base de datos
		Producto_procesado p = producto_procesadoRepository.findById(code).get();
		// Le sumamos la existencia
		p.sumarExistencia(canti);
		// Lo guardamos con la existencia ya sumada
		producto_procesadoRepository.save(p);
		
		
		loteRepository.save(lote);
		redirectAttrs
        .addFlashAttribute("mensaje", "Productos agregados exitosamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/productosPRO";
		
	}
	
	@GetMapping("productosPRO/nuevo")
	public String mostrarFormularioDeNuevoProductoPRO(Model modelo) {
		
		
		List<Categoria> listaCategorias = categoriaRepository.findByProductoProcesado();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		List<Producto_procesado> listarProductosPRO = producto_procesadoRepository.findAll();
		modelo.addAttribute("listarProductosPRO", listarProductosPRO);
		
		modelo.addAttribute("producto", new Producto_procesado());
		
		return "Inventarios/PP/new_product";
	}
	
	@PostMapping("productosPRO/guardar")
	public String guardarProductoPRO(@ModelAttribute @Valid Producto_procesado producto_procesado,BindingResult result,Model model,@RequestParam("file") MultipartFile imagen, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Producto");
			model.addAttribute("producto", producto_procesado);
			
			redirectAttrs
			.addFlashAttribute("mensaje", "Existieron errores en el formulario")
			.addFlashAttribute("clase", "danger");
			
			return "redirect:/productosPRO";
		}

		
		if (!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/images");
			String rutaAbsoluta = "C://Producto//recursos";
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				producto_procesado.setImagen(imagen.getOriginalFilename());
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		producto_procesadoRepository.save(producto_procesado);
		redirectAttrs
			.addFlashAttribute("mensaje", "Producto guardado con exito")
			.addFlashAttribute("clase", "warning");
		return "redirect:/productosPRO";
		
	}
	
	
	
	@GetMapping("/productosPRO/editar/{IdProduct}")
	public String mostrarFormularioDeModificarProducto(@PathVariable("IdProduct") Integer IdProduct,Model modelo,RedirectAttributes redirectAttrs) {
		

		
		Producto_procesado productoPRO = producto_procesadoRepository.findById(IdProduct).get();
		modelo.addAttribute("producto", productoPRO);
		
		List<Categoria> listaCategorias = categoriaRepository.findByProductoProcesado();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		return "Inventarios/PP/new_product";
	}
	
	
	@GetMapping("/productosPRO/eliminar/{IdProduct}")
	public String eliminarProductoPRO(@PathVariable("IdProduct") Integer IdProduct,Model modelo, RedirectAttributes redirectAttrs) {
		 redirectAttrs
         .addFlashAttribute("mensaje", "Eliminado correctamente")
         .addFlashAttribute("clase", "warning");
		producto_procesadoRepository.deleteById(IdProduct);
		return "redirect:/productosPRO";
		
	}
	
	@GetMapping("/productosPRO/detalle/{IdProduct}")
	public String detalleProducto(@PathVariable("IdProduct") Integer IdProduct,Model modelo,RedirectAttributes redirectAttrs) {
		

		
		Producto_procesado producto = null;
		
		if (IdProduct > 0) {
			producto = producto_procesadoRepository.getById(IdProduct);
			
			if (producto == null) {
				redirectAttrs
		         .addFlashAttribute("mensaje", "ATENCIÓN: El ID del producto no existe")
		         .addFlashAttribute("clase", "warning");
				return "redirect:/productosPRO";
			}
		}else {
			redirectAttrs
	         .addFlashAttribute("mensaje", "ATENCIÓN: Error con el ID del producto")
	         .addFlashAttribute("clase", "warning");
			return "redirect:/productosPRO";
		}
		modelo.addAttribute("titulo", "Detalle del producto  "+ producto.getNom_Product());
		modelo.addAttribute("producto", producto);
		
		return "Inventarios/PP/detalleProducto";
	}
	
	
}








