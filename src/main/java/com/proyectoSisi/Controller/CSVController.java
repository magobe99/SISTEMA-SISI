package com.proyectoSisi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.proyectoSisi.servicio.CSVService;
import com.proyectoSisi.helper.ResponseMessage;
import com.proyectoSisi.helper.CSVHelper;
import com.proyectoSisi.helper.CSVHelperClientes;
import com.proyectoSisi.helper.CSVHelperProduct;
import com.proyectoSisi.helper.CSVHelperMaterial;
import com.proyectoSisi.model.Proveedor;
import com.proyectoSisi.model.Cliente;
import com.proyectoSisi.model.Producto_procesado;
import com.proyectoSisi.model.Materia_prima;

// @CrossOrigin("https://sistema-sisi.herokuapp.com")
@Controller
@RequestMapping("/api/csv")
public class CSVController {

	@Autowired
	CSVService fileService;

	//// Proveedores///

	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelper.hasCSVFormat(file)) {
			try {
				fileService.save(file);

				message = "Subió el archivo con éxito: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/csv/download/")
						.path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
			} catch (Exception e) {
				message = "No se pudo cargar el archivo 'Proveedores': " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
			}
		}

		message = "Sube un archivo csv!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
	}

	@GetMapping("/tutorials")
	public ResponseEntity<List<Proveedor>> getAllTutorials() {
		try {
			List<Proveedor> tutorials = fileService.getAllTutorials();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/download/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
		InputStreamResource file = new InputStreamResource(fileService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	//// Clientes///

	@PostMapping("/uploadCli")
	public ResponseEntity<ResponseMessage> uploadFileCli(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelperClientes.hasCSVFormat(file)) {
			try {
				fileService.savecli(file);

				message = "Subió el archivo clientes con éxito: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/csv/downloadCli/").path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
			} catch (Exception e) {
				message = "No se pudo cargar el archivo 'Clientes': " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
			}
		}

		message = "Sube un archivo csv!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
	}

	@GetMapping("/tutorialsCli")
	public ResponseEntity<List<Cliente>> getAllTutorialsCli() {
		try {
			List<Cliente> tutorials = fileService.getAllTutorialscli();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/downloadCli/{fileName:.+}")
	public ResponseEntity<Resource> downloadFileCli(@PathVariable String fileName) {
		InputStreamResource files = new InputStreamResource(fileService.loadcli());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(files);
	}

////Productos procesados///

	@PostMapping("/uploadPRO")
	public ResponseEntity<ResponseMessage> uploadFilepro(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelperProduct.hasCSVFormat(file)) {
			try {
				fileService.savepro(file);

				message = "Subió el archivo con éxito: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/csv/downloadPRO/").path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
			} catch (Exception e) {
				message = "No se pudo cargar el archivo 'Producto Procesado': " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
			}
		}

		message = "Sube un archivo csv!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
	}

	@GetMapping("/tutorialsPRO")
	public ResponseEntity<List<Producto_procesado>> getAllTutorialspro() {
		try {
			List<Producto_procesado> tutorials = fileService.getAllTutorialspro();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/downloadPRO/{fileName:.+}")
	public ResponseEntity<Resource> downloadFilepro(@PathVariable String fileName) {
		InputStreamResource file = new InputStreamResource(fileService.loadpro());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

////Materia Prima///

	@PostMapping("/uploadMAT")
	public ResponseEntity<ResponseMessage> uploadFilemat(@RequestParam("file") MultipartFile file) {
		String message = "";

		if (CSVHelperMaterial.hasCSVFormat(file)) {
			try {
				fileService.savemat(file);

				message = "Subió el archivo con éxito: " + file.getOriginalFilename();

				String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/csv/downloadMAT/").path(file.getOriginalFilename()).toUriString();

				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message, fileDownloadUri));
			} catch (Exception e) {
				message = "No se pudo cargar el archivo 'Materia Prima': " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message, ""));
			}
		}

		message = "Sube un archivo csv!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message, ""));
	}

	@GetMapping("/tutorialsMAT")
	public ResponseEntity<List<Materia_prima>> getAllTutorialsmat() {
		try {
			List<Materia_prima> tutorials = fileService.getAllTutorialsmat();

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/downloadMAT/{fileName:.+}")
	public ResponseEntity<Resource> downloadFilemat(@PathVariable String fileName) {
		InputStreamResource file = new InputStreamResource(fileService.loadmat());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

}
