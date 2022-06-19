package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Tipo_producto;
@Repository
public interface Tipo_productoRepository extends JpaRepository<Tipo_producto, Integer>{

}
