package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectoSisi.Repository.Existencia_mpRepository;
import com.proyectoSisi.Repository.ProduccionRepository;
import com.proyectoSisi.model.Existencia_mp;
import com.proyectoSisi.model.Produccion;

@Controller
public class ProduccionController {
	
	@Autowired
	private ProduccionRepository produccionRepository;
	
	@Autowired
	private Existencia_mpRepository existencia_mpRepository;
	
	@GetMapping("/producciones")
	public String listaProducciones(Model modelo) {
		List<Produccion> listarProduccion = produccionRepository.findAll();
		modelo.addAttribute("listarProduccion", listarProduccion);
		
		return "Producciones/list_Producciones";
	}
	
	
	@GetMapping("/movimientosMP")
	public String listaMovimientosMP(Model modelo) {
		List<Existencia_mp> listarExistenciasMP = existencia_mpRepository.findAll();
		modelo.addAttribute("listarExistenciasMP", listarExistenciasMP);
		
		return "Producciones/list_movimientosMP";
	}

}
