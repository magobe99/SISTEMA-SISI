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
import com.proyectoSisi.Repository.ClienteRepository;
import com.proyectoSisi.Repository.Producto_procesadoRepository;
import com.proyectoSisi.Repository.ProductosVendidosRepository;
import com.proyectoSisi.Repository.UsuarioRepositorio;
import com.proyectoSisi.Repository.VentasRepository;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.ProductoParaVender;
import com.proyectoSisi.model.ProductoVendido;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Usuario;
import com.proyectoSisi.model.Venta;


@Controller
@RequestMapping(path = "/vender")
public class VenderController {
	
	@Autowired
	private Producto_procesadoRepository producto_procesadoRepository;
	
	@Autowired
	private VentasRepository ventasRepository;
	
	@Autowired
	private ProductosVendidosRepository productosVendidosRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private UsuarioRepositorio usuarioRepository;
	

	
	
	@PostMapping(value = "/quitar/{indice}")
	public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
			carrito.remove(indice);
			this.guardarCarrito(carrito, request);
			
		}
		return "redirect:/vender";
	}
	
	private void limpiarCarrito(HttpServletRequest request) {
        this.guardarCarrito(new ArrayList<>(), request);
    }
	
	@GetMapping(value = "/limpiar")
	public String cancelarVenta(HttpServletRequest request, RedirectAttributes redirectAttrs) {
		this.limpiarCarrito(request);
		redirectAttrs
				.addFlashAttribute("mensaje", "Venta cancelada")
		        .addFlashAttribute("clase", "danger");
		return "redirect:/vender";
		
	}
	
	@GetMapping(value = "/")
	public String interfazVender(Model model, HttpServletRequest request) {
		List<Producto_procesado> listarProductosPRO = producto_procesadoRepository.findAll();
		model.addAttribute("listarProductosPRO", listarProductosPRO);
		
		List<Cliente> listaClientes = clienteRepository.findAll();
		model.addAttribute("listaClientes", listaClientes);
		
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		model.addAttribute("venta", new Venta());
		model.addAttribute("producto", new Producto_procesado());
		Double total = 0.0;
		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		for (ProductoParaVender p: carrito) total += p.getTotal();
		model.addAttribute("total", total);
		return "vender/vender";
		
	}
	
	@PostMapping(value = "/terminar")
	public String terminarVenta(Venta venta,HttpServletRequest request, RedirectAttributes redirectAttrs) {
		

		ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		// Si no hay carrito o esta vacio, regresamos inmediatamente
		if (carrito == null || carrito.size() <= 0) {
			return "redirect:/vender";
		}
		
		ventasRepository.save(venta);
		// Recorrer el carrito
		for (ProductoParaVender productoParaVender : carrito) {
			// Obtener el producto fresco desde la base de datos
			Producto_procesado p = producto_procesadoRepository.findById(productoParaVender.getIdProduct()).orElse(null);
			if (p == null) continue; // Si es nulo o no existe, ignoramos el siguiente código con continue
			// Le restamos existencia
			p.restarExistencia(productoParaVender.getCantidad());
			// Lo guardamos con la existencia ya restada
			producto_procesadoRepository.save(p);
			// Creamos un nuevo producto que será el que se guarda junto con la venta
			ProductoVendido productoVendido = new ProductoVendido(productoParaVender.getCantidad(), productoParaVender.getValorUnitarioVenta(),productoParaVender.getNom_Product(), productoParaVender.getCodProduct(), p, venta);
			// Y LO GUARDAMOS
			productosVendidosRepository.save(productoVendido);			
		}
		
		// Al final limpialos el carrito
		this.limpiarCarrito(request);
		// e indicamos una venta exitosa
		redirectAttrs
		        .addFlashAttribute("mensaje", "Venta realizada correctamente")
		        .addFlashAttribute("clase", "success");
		return "redirect:/vender";
	}
	
	
	
	 private ArrayList<ProductoParaVender> obtenerCarrito(HttpServletRequest request) {
	        ArrayList<ProductoParaVender> carrito = (ArrayList<ProductoParaVender>) request.getSession().getAttribute("carrito");
	        if (carrito == null) {
	            carrito = new ArrayList<>();
	        }
	        return carrito;
	    }

	    private void guardarCarrito(ArrayList<ProductoParaVender> carrito, HttpServletRequest request) {
	        request.getSession().setAttribute("carrito", carrito);
	    }
	    
	 @PostMapping(value ="/agregar")
	 public String agregarAlCarrito(@ModelAttribute Producto_procesado producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
		 
	
		 ArrayList<ProductoParaVender> carrito = this.obtenerCarrito(request);
		 Producto_procesado productoBuscadoPorCodigo = producto_procesadoRepository.findByCodProduct(producto.getCodProduct());
		 if (productoBuscadoPorCodigo == null) {
			 redirectAttrs
		             .addFlashAttribute("mensaje", "El producto con el código " + producto.getCodProduct() + " no existe")
		             .addFlashAttribute("clase", "warning");
			 	return "redirect:/vender";
		}
		if (productoBuscadoPorCodigo.sinExistencia()) {
			redirectAttrs
		            .addFlashAttribute("mensaje", "El producto está agotado")
		            .addFlashAttribute("clase", "danger");
				return "redirect:/vender";
		}
		
		
		boolean encontrado = false;
		for (ProductoParaVender productoParaVenderActual : carrito) {
			if (productoParaVenderActual.getCodProduct().equals(productoBuscadoPorCodigo.getCodProduct())) {
				if (productoBuscadoPorCodigo.noStock(productoParaVenderActual.getCantidad()+1)) {
					redirectAttrs
				            .addFlashAttribute("mensaje", "Stock limite del producto alcanzados")
				            .addFlashAttribute("clase", "danger");
						return "redirect:/vender";
				}
				
				productoParaVenderActual.aumentarCantidad();
				encontrado = true;
				break;
			}
		}
		if (!encontrado) {
			Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
	
			carrito.add(new ProductoParaVender(productoBuscadoPorCodigo.getNom_Product(), productoBuscadoPorCodigo.getCodProduct(), productoBuscadoPorCodigo.getValorUnitarioVenta(), productoBuscadoPorCodigo.getStock_Actual(), productoBuscadoPorCodigo.getIdProduct(), cantidad));
			
		}
		this.guardarCarrito(carrito, request);
        return "redirect:/vender";
	 }
	 

}
