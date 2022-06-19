package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Compra;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer>{

}
