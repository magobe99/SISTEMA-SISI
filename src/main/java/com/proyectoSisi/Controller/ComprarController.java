package com.proyectoSisi.Controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectoSisi.Repository.CompraRepository;
import com.proyectoSisi.Repository.Detalle_compraRepository;
import com.proyectoSisi.Repository.Materia_PrimaRepository;
import com.proyectoSisi.Repository.ProveedorRepository;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.model.Compra;
import com.proyectoSisi.model.Detalle_compra;
import com.proyectoSisi.model.Materia_prima;
import com.proyectoSisi.model.Proveedor;
import com.proyectoSisi.model.Usuario;
import com.proyectoSisi.model.productoParaComprar;

@Controller
@RequestMapping(path = "/comprar")
public class ComprarController {
	
	@Autowired
	private Materia_PrimaRepository materia_PrimaRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private Detalle_compraRepository detalle_compraRepository;
	
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	
	
	@PostMapping(value = "/quitar/{indice}")
	public String quitarDelcarrito2(@PathVariable int indice,HttpServletRequest request) {
		ArrayList<productoParaComprar> carrito2 = this.obtenercarrito2(request);
		if (carrito2 != null && carrito2.size() > 0 && carrito2.get(indice) != null) {
			carrito2.remove(indice);
			this.guardarcarrito2(carrito2, request);
		}
		return "redirect:/comprar";
		
	}
	
	private void limpiarcarrito2(HttpServletRequest request) {
        this.guardarcarrito2(new ArrayList<>(), request);
    }
	
	@GetMapping(value = "/limpiar")
	public String cancelarCompra(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarcarrito2(request);
		redirectAttrs
				.addFlashAttribute("mensaje", "Compra cancelada")
		        .addFlashAttribute("clase", "danger");
		return "redirect:/comprar";
		
	}
	
	@GetMapping(value = "/")
	public String interfazComprar(Model model, HttpServletRequest request) {
		List<Materia_prima> listaMateriaPrima = materia_PrimaRepository.findAll();
		model.addAttribute("listaMateriaPrima", listaMateriaPrima);
		
		List<Proveedor> listaProveedores = proveedorRepository.findAll();
		model.addAttribute("listaProveedores", listaProveedores);
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		model.addAttribute("compra", new Compra());
		model.addAttribute("producto", new Materia_prima());
		Double total = 0.0;
		ArrayList<productoParaComprar> carrito2 = this.obtenercarrito2(request);
		for (productoParaComprar p: carrito2) total += p.getTotal();
		model.addAttribute("total", total);
		return "comprar/Comprar";
	}
	
	@PostMapping(value = "/terminar")
	public String terminarCompra(Compra compra,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		

		ArrayList<productoParaComprar> carrito2 = this.obtenercarrito2(request);
		// Si no hay carrito2 o esta vacio, regresamos inmediatamente
		if (carrito2 == null || carrito2.size() <= 0) {
			return "redirect:/comprar";
		}
		
		
		compraRepository.save(compra);
		// Recorrer el carrito2
		for (productoParaComprar productoParaComprar : carrito2) {
			// Obtener el producto fresco desde la base de datos
			Materia_prima p = materia_PrimaRepository.findById(productoParaComprar.getIdMateriaPrima()).orElse(null);
			if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
			// Le sumamos la existencia
			p.sumarExistencia(productoParaComprar.getCantidad());
			// Lo guardamos con la existencia ya sumada
			materia_PrimaRepository.save(p);
			// Creamos un nuevo producto que será el que se guarda junto con la venta
			Detalle_compra detalleCompra = new Detalle_compra(productoParaComprar.getCantidad(), productoParaComprar.getPrecio(), productoParaComprar.getNomMateriaPrima(), productoParaComprar.getCodMateriaPrima(),p, compra);
			// Y LO GUARDAMOS
			detalle_compraRepository.save(detalleCompra);			
		}
		
		// Al final limpialos el carrito2
		this.limpiarcarrito2(request);
		// e indicamos una venta exitosa
		redirectAttrs
		        .addFlashAttribute("mensaje", "Compra registrada correctamente")
		        .addFlashAttribute("clase", "success");
		return "redirect:/comprar";
	}
	
	
	private ArrayList<productoParaComprar> obtenercarrito2(HttpServletRequest request){
		ArrayList<productoParaComprar> carrito2 = (ArrayList<productoParaComprar>) request.getSession().getAttribute("carrito2");
		if (carrito2 == null) {
            carrito2 = new ArrayList<>();
        }
        return carrito2;	
	}
	
	private void guardarcarrito2(ArrayList<productoParaComprar> carrito2, HttpServletRequest request) {
        request.getSession().setAttribute("carrito2", carrito2);
    }
	
	 @PostMapping(value ="/agregar")
	 public String agregarAlcarrito2(@ModelAttribute Materia_prima producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		 
	
		 ArrayList<productoParaComprar> carrito2 = this.obtenercarrito2(request);
		 Materia_prima productoBuscadoPorCodigo = materia_PrimaRepository.findByCodMateriaPrima(producto.getCodMateriaPrima());
		 if (productoBuscadoPorCodigo == null) {
			 redirectAttrs
		             .addFlashAttribute("mensaje", "El producto con el código " + producto.getCodMateriaPrima() + " no existe")
		             .addFlashAttribute("clase", "warning");
			 	return "redirect:/comprar";
		}

		boolean encontrado = false;
		for (productoParaComprar productoParaComprarActual : carrito2) {
			if (productoParaComprarActual.getCodMateriaPrima().equals(productoBuscadoPorCodigo.getCodMateriaPrima())) {
				productoParaComprarActual.aumentarCantidad();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
			Double precio = Double.parseDouble(request.getParameter("precio"));

	
			carrito2.add(new productoParaComprar(productoBuscadoPorCodigo.getIdMateriaPrima(),productoBuscadoPorCodigo.getCodMateriaPrima(),productoBuscadoPorCodigo.getNomMateriaPrima(),productoBuscadoPorCodigo.getStock_Actual(),cantidad,precio));
			
		}
		this.guardarcarrito2(carrito2, request);
        return "redirect:/comprar";
	 }
	
	

}
