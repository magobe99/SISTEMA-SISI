package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Detalle_compra;

@Repository
public interface Detalle_compraRepository extends JpaRepository<Detalle_compra, Integer>{

}
