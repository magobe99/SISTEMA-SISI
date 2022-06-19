package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Existencia_mp;
@Repository
public interface Existencia_mpRepository extends JpaRepository<Existencia_mp, Integer>{

}
