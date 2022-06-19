package com.proyectoSisi.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyectoSisi.Repository.VentasRepository;


@Controller
@RequestMapping(path= "/ventas")
public class VentasController {
	@Autowired
	VentasRepository ventasRepository;
	
	
	
	 @GetMapping(value = "/")
	    public String mostrarVentas(Model model) {
	        model.addAttribute("ventas", ventasRepository.findAll());
	        return "ventas/ver_ventas";
	    }
	 
	 @GetMapping(value = "/detalle/{id}")
	    public String mostrardetalleVentas(@PathVariable("id") Integer id,Model model) {
	        model.addAttribute("ventas", ventasRepository.findById(id).get());
	        return "ventas/ver_detallevVenta";
	    }
	
}
