package com.proyectoSisi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectoSisi.Repository.CompraRepository;

@Controller
@RequestMapping(path= "/compras")
public class ComprasController {
	@Autowired
	CompraRepository compraRepository;
	
	@GetMapping(value = "/")
    public String mostrarCompras(Model model) {
        model.addAttribute("compras", compraRepository.findAll());
        return "Compras/ver_compras";
    }
 
 @GetMapping(value = "/detalle/{id}")
    public String mostrardetalleVentas(@PathVariable("id") Integer id,Model model) {
        model.addAttribute("compras", compraRepository.findById(id).get());
        return "Compras/ver_detalleCompra";
    }

}
