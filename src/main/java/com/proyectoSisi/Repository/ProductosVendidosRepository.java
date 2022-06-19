package com.proyectoSisi.Repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyectoSisi.model.ProductoVendido;



@Repository
public interface ProductosVendidosRepository extends JpaRepository<ProductoVendido, Integer> {
	
	@Query(value ="SELECT * FROM detalle_venta dv JOIN ventas v ON v.id = dv.id_ventas WHERE v.id =:idP", nativeQuery = true)
	Iterable<ProductoVendido> findByPedido(int idP);
	
	@Query(value = "SELECT SUM(dv.cantidad * dv.precio) AS \"Total\" FROM detalle_venta dv JOIN ventas v ON v.id = dv.id_ventas WHERE v.id_client =:idCli", nativeQuery = true)
	Double totalByIdCustomer(int idCli);
	
	@Query("SELECT P FROM ProductoVendido P WHERE P.venta=:id")
	List<ProductoVendido> findByIdVenta(int id);
	
	@Query(value = "SELECT * FROM detalle_venta WHERE id_ventas =:venta", nativeQuery = true)
	List<ProductoVendido> findByVenta(int venta);
	
}
