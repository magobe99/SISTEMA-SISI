package com.proyectoSisi.servicio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.proyectoSisi.Controller.dto.ReporteVentasDTO;

import net.sf.jasperreports.engine.JRException;

public interface ReporteVentasServiceAPI {
	

	ReporteVentasDTO obtenerReporteVentas(Map<String, Object> params) throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteDetalleVentas(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteProductoGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteProductoCategoria(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteMateriaPrima(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteVentasUsuarios(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteMateriaPrimaCategoria(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteCategoriasGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteClienteGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteLotesFecha(Map<String, Object> params) throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteProduccionesGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteUsuariosGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteProveedoresGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteProduccionesFechas(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteCompras(Map<String, Object> params) throws JRException, IOException, SQLException;

	ReporteVentasDTO obtenerReporteDetalleCompra(Map<String, Object> params)
			throws JRException, IOException, SQLException;

	
	
	

}
