package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.proyectoSisi.Repository.ClienteRepository;
import com.proyectoSisi.Repository.Producto_procesadoRepository;
import com.proyectoSisi.Repository.VentasRepository;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Venta;
import com.proyectoSisi.servicio.UsuarioServicio;

@Controller
public class RegistroControlador {
	
	
	@Autowired
	private Producto_procesadoRepository producto_procesadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private VentasRepository ventasRepository;
	

	
	 
	
	@GetMapping("/login")
	public String iniciarSesion(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
 
        return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String verPaginaDeInicio(Model modelo) {
		List<Producto_procesado> listarProductosPRO = producto_procesadoRepository.findAll();
		modelo.addAttribute("listarProductosPRO", listarProductosPRO);
		List<Cliente> listarClientes = clienteRepository.findAll();
		modelo.addAttribute("listarClientes", listarClientes);
		

		return "index";
	}
	
	@GetMapping("/inventarios")
	public String verPaginaDeInventarios(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		return "Inventarios/Inventarios";
	}
	
	@GetMapping("/noEncontrado")
	public String verPaginaDeNoResultados() {

		return "error/noResultados";
	}
	
	
}
