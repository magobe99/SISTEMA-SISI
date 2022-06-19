package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Producto_procesado;
@Repository
public interface Producto_procesadoRepository extends JpaRepository<Producto_procesado, Integer>{

	Producto_procesado findByCodProduct(Integer codProduct);


	
	
}
