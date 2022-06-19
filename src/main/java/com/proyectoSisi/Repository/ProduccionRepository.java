package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Produccion;

@Repository
public interface ProduccionRepository extends JpaRepository<Produccion, Integer>{

}
