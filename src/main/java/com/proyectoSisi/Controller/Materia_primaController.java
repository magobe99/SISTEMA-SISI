package com.proyectoSisi.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.proyectoSisi.Repository.CategoriaRepository;
import com.proyectoSisi.Repository.Materia_PrimaRepository;
import com.proyectoSisi.Repository.ProduccionRepository;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Categoria;
import com.proyectoSisi.model.Lote;
import com.proyectoSisi.model.Materia_prima;
import com.proyectoSisi.model.Produccion;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Usuario;



@Controller
public class Materia_primaController {
	
	@Autowired
	private Materia_PrimaRepository materia_PrimaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	@Autowired
	private ProduccionRepository produccionRepository;
	
	@GetMapping("/productos")
	public String listarProductos(Model modelo) {
		List<Materia_prima> listarProductos = materia_PrimaRepository.findAll();
		modelo.addAttribute("listarProductos", listarProductos);
		
		List<Categoria> listaCategorias = categoriaRepository.findByMateriaPrima();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		
		modelo.addAttribute("prod", new Produccion());
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		
		return "Inventarios/MP/product_al";
		
	}
	
	/*Guardar Lote*/
	@PostMapping("productos/solicitar" )
	public String guardarLote(@RequestParam(name = "cantidad") Integer canti
			                 ,@RequestParam(name = "materia_primaProduccion") Integer code
			                 ,@Valid Produccion prod,RedirectAttributes redirectAttrs, BindingResult bindingResult) {
		
		Materia_prima value = materia_PrimaRepository.findById(code).get();
		Integer valor = value.getStock_Actual();
		if (valor <= 0){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Solicitud Rechazada - Stock insuficiente")
                    .addFlashAttribute("clase", "danger");
			return "redirect:/productos";
        }
		if (canti > valor){
            redirectAttrs
                    .addFlashAttribute("mensaje", "La cantidad solicitada sobrepasa el stock en inventario")
                    .addFlashAttribute("clase", "danger");
			return "redirect:/productos";
        }

		// Obtener el producto fresco desde la base de datos
		Materia_prima p = materia_PrimaRepository.findById(code).get();
		// Le sumamos la existencia
		p.restarExistencia(canti);
		// Lo guardamos con la existencia ya sumada
		materia_PrimaRepository.save(p);
		
		
		produccionRepository.save(prod);
		redirectAttrs
        .addFlashAttribute("mensaje", "Productos descontados exitosamente")
        .addFlashAttribute("clase", "warning");
		return "redirect:/productos";
		
	}
	
	@GetMapping("productos/nuevo")
	public String mostrarFormularioDeNuevoProducto(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findByMateriaPrima();
		modelo.addAttribute("producto", new Materia_prima());
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		return "Inventarios/MP/NewMateriaPrima";
	}
	
	@PostMapping("productos/guardar")
	public String guardarProductoPRO(@ModelAttribute  @Valid Materia_prima materia_prima,BindingResult result,Model model,@RequestParam("file") MultipartFile imagen, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario: Nuevo Producto");
			model.addAttribute("producto", materia_prima);
			
			redirectAttrs
			.addFlashAttribute("mensaje", "Existieron errores en el formulario")
			.addFlashAttribute("clase", "danger");
			
			return "redirect:/productos";
		}
		

		
		if (!imagen.isEmpty()) {
			//Path directorioImagenes = Paths.get("src//main//resources//static/images");
			String rutaAbsoluta = "C://Producto//recursos";
			
			try {
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				materia_prima.setImagen(imagen.getOriginalFilename());
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		materia_PrimaRepository.save(materia_prima);
		redirectAttrs
			.addFlashAttribute("mensaje", "Producto guardado con exito")
			.addFlashAttribute("clase", "warning");
		return "redirect:/productos";
		
	}
	

	
	
	@GetMapping("/productos/editar/{IdMateriaPrima}")
	public String mostarFormularioDeModificarProducto(@PathVariable("IdMateriaPrima") Integer IdMateriaPrima,Model modelo) {
		Materia_prima producto = materia_PrimaRepository.findById(IdMateriaPrima).get();
		modelo.addAttribute("producto", producto);
		
	
		List<Categoria> listaCategorias = categoriaRepository.findByMateriaPrima();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		return "Inventarios/MP/NewMateriaPrima";
	}
	
	
	@GetMapping("/productos/eliminar/{IdMateriaPrima}")
	public String eliminarProducto(@PathVariable("IdMateriaPrima") Integer IdMateriaPrima,Model modelo, RedirectAttributes redirectAttrs) {
		redirectAttrs
        .addFlashAttribute("mensaje", "Eliminado correctamente")
        .addFlashAttribute("clase", "warning");
		materia_PrimaRepository.deleteById(IdMateriaPrima);
		return "redirect:/productos";
		
	}
	
	@GetMapping("/productos/detalle/{IdMateriaPrima}")
	public String detalleProducto(@PathVariable("IdMateriaPrima") Integer IdMateriaPrima,Model modelo,RedirectAttributes redirectAttrs) {
		
		Materia_prima producto = null;
		
		if (IdMateriaPrima > 0) {
			producto = materia_PrimaRepository.getById(IdMateriaPrima);
			
			if (producto == null) {
				redirectAttrs
		         .addFlashAttribute("mensaje", "ATENCIÓN: El ID del producto no existe")
		         .addFlashAttribute("clase", "warning");
				return "redirect:/productos";
			}
		}else {
			redirectAttrs
	         .addFlashAttribute("mensaje", "ATENCIÓN: Error con el ID del producto")
	         .addFlashAttribute("clase", "warning");
			return "redirect:/productos";
		}
		modelo.addAttribute("titulo", "Detalle del producto  "+ producto.getNomMateriaPrima());
		modelo.addAttribute("producto", producto);
		
		return "Inventarios/MP/detalleProducto";
	}
	
	
}
