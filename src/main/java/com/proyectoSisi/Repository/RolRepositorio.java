package com.proyectoSisi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long>{

}
