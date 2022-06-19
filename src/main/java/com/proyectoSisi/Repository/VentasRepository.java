package com.proyectoSisi.Repository;


import com.proyectoSisi.model.Venta;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface VentasRepository extends CrudRepository<Venta, Integer>{

	@Query("SELECT P FROM Venta P WHERE P.id=:idOrden AND P.cliente.id=:idCli")
	Optional<Venta> findByIdAndClienteId(int idCli, int idOrden);
	
	@Query(value = "SELECT COUNT(id) FROM ventas", nativeQuery = true)
	List<Venta> findByVentas();
	


}
