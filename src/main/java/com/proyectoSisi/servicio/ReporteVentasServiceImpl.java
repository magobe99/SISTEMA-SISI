package com.proyectoSisi.servicio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSisi.commons.JasperReportManager;
import com.proyectoSisi.commons.TipoReporteEnum;
import com.proyectoSisi.Controller.dto.ReporteVentasDTO;

import net.sf.jasperreports.engine.JRException;


@Service
public class ReporteVentasServiceImpl implements ReporteVentasServiceAPI {

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;


	@Override
	public ReporteVentasDTO obtenerReporteVentas(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "ReporteVentas";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	
	@Override
	public ReporteVentasDTO obtenerReporteDetalleVentas(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "DetalleVenta";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteProductoGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "ProductoGeneral";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteProductoCategoria(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "ProductoCategoria";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteMateriaPrima(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "materiasPrimas";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteMateriaPrimaCategoria(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "Categoria";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteVentasUsuarios(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "ventasUsuarios";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	
	@Override
	public ReporteVentasDTO obtenerReporteCategoriasGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "categorias";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteClienteGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "clientesGeneral";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteLotesFecha(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "lotesFechas";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteProduccionesGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "produccionesGeneral";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteProduccionesFechas(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "produccionesMPFechas";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteUsuariosGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "users";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteProveedoresGeneral(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "proveedoresGeneral";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteVentasDTO obtenerReporteCompras(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "ReporteCompras";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	@Override
	public ReporteVentasDTO obtenerReporteDetalleCompra(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "DetalleCompra";
		ReporteVentasDTO dto = new ReporteVentasDTO();
		String extension = params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name()) ? ".xlsx"
				: ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	


}
