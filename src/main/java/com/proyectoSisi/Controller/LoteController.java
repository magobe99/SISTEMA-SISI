package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectoSisi.Repository.Existencia_ppRepository;
import com.proyectoSisi.Repository.LoteRepository;
import com.proyectoSisi.model.Existencia_pp;
import com.proyectoSisi.model.Lote;

@Controller
public class LoteController {
	
	@Autowired
	private LoteRepository loteRepository;
	
	@Autowired
	private Existencia_ppRepository existencia_ppRepository;
	
	@GetMapping("/lotes")
	public String listarLotes(Model modelo) {
		List<Lote> listarLote = loteRepository.findAll();
		modelo.addAttribute("listarLote", listarLote);
		
		return "Lotes/list_Lotes";
	}
	
	@GetMapping("/movimientosPP")
	public String listaMovimientosPP(Model modelo) {
		List<Existencia_pp> listarExistenciasPP = existencia_ppRepository.findAll();
		modelo.addAttribute("listarExistenciasPP", listarExistenciasPP);
		
		return "Lotes/list_movimientosPP";
	}

}
