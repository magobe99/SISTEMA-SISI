package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer>{

}
