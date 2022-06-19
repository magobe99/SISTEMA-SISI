package com.proyectoSisi.servicio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoSisi.Repository.ClienteRepository;
import com.proyectoSisi.Repository.ProveedorRepository;
import com.proyectoSisi.Repository.Producto_procesadoRepository;
import com.proyectoSisi.Repository.Materia_PrimaRepository;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.Proveedor;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Materia_prima;
import com.proyectoSisi.helper.CSVHelper;
import com.proyectoSisi.helper.CSVHelperClientes;
import com.proyectoSisi.helper.CSVHelperProduct;
import com.proyectoSisi.helper.CSVHelperMaterial;

@Service
@Scope("singleton")
public class CSVService {
	@Autowired
	ProveedorRepository repository;

	@Autowired
	ClienteRepository repositoryCli;
	
	@Autowired
	Producto_procesadoRepository repositoryPRO;
	
	@Autowired
	Materia_PrimaRepository repositoryMAT;

	///// PROVEEDORES/////////

	public void save(MultipartFile file) {
		try {
			List<Proveedor> tutorials = CSVHelper.csvToTutorials(file.getInputStream());
			repository.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fallo al almacenar datos csv:" + e.getMessage());
		}
	}

	public ByteArrayInputStream load() {
		List<Proveedor> tutorials = repository.findAll();

		ByteArrayInputStream in = CSVHelper.tutorialsToCSV(tutorials);
		return in;
	}

	public List<Proveedor> getAllTutorials() {
		return repository.findAll();
	}

	///// Clientes/////////

	public void savecli(MultipartFile file) {
		try {
			List<Cliente> tutorials = CSVHelperClientes.csvToTutorials(file.getInputStream());
			repositoryCli.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fallo al almacenar datos csv:" + e.getMessage());
		}
	}

	public ByteArrayInputStream loadcli() {
		List<Cliente> tutorials = repositoryCli.findAll();

		ByteArrayInputStream in = CSVHelperClientes.tutorialsToCSV(tutorials);
		return in;
	}

	public List<Cliente> getAllTutorialscli() {
		return repositoryCli.findAll();
	}
	
///// PRODUCTOS PROCESADOS/////////

	public void savepro(MultipartFile file) {
		try {
			List<Producto_procesado> tutorials = CSVHelperProduct.csvToTutorials(file.getInputStream());
			repositoryPRO.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fallo al almacenar datos csv:" + e.getMessage());
		}
	}

	public ByteArrayInputStream loadpro() {
		List<Producto_procesado> tutorials = repositoryPRO.findAll();

		ByteArrayInputStream in = CSVHelperProduct.tutorialsToCSV(tutorials);
		return in;
	}

	public List<Producto_procesado> getAllTutorialspro() {
		return repositoryPRO.findAll();
	}

///// MATERIA PRIMA/////////

	public void savemat(MultipartFile file) {
		try {
			List<Materia_prima> tutorials = CSVHelperMaterial.csvToTutorials(file.getInputStream());
			repositoryMAT.saveAll(tutorials);
		} catch (IOException e) {
			throw new RuntimeException("fallo al almacenar datos csv:" + e.getMessage());
		}
	}

	public ByteArrayInputStream loadmat() {
		List<Materia_prima> tutorials = repositoryMAT.findAll();

		ByteArrayInputStream in = CSVHelperMaterial.tutorialsToCSV(tutorials);
		return in;
	}

	public List<Materia_prima> getAllTutorialsmat() {
		return repositoryMAT.findAll();
	}
}
