package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Materia_prima;
@Repository
public interface Materia_PrimaRepository extends JpaRepository<Materia_prima, Integer>{

	Materia_prima findByCodMateriaPrima(Integer codMateriaPrima);
	



}
