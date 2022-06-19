package com.proyectoSisi.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
	
	
	@Query(value = "SELECT * FROM categorias c WHERE c.id_tipo_product=1", nativeQuery = true)
	public List<Categoria> findByProductoProcesado();
	
	
	@Query(value = "SELECT * FROM categorias c WHERE c.id_tipo_product=2", nativeQuery = true)
	public List<Categoria> findByMateriaPrima();


	
	
}
