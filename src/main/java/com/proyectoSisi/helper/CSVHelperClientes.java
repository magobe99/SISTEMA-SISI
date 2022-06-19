package com.proyectoSisi.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

import com.proyectoSisi.model.Cliente;


public class CSVHelperClientes {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "DocCliente", "TipoDoc", "Nombres", "Apellidos", "Telefono", "Correo" };

  public static boolean hasCSVFormat(MultipartFile file) {
 System.out.println(file.getContentType());
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<Cliente> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Cliente> developerTutorialList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Cliente developerTutorial = new Cliente(
              Integer.parseInt(csvRecord.get("Id")),
              csvRecord.get("DocCliente"),
              csvRecord.get("TipoDoc"),
              csvRecord.get("Nombres"),
              csvRecord.get("Apellidos"),
              csvRecord.get("Telefono"),
              csvRecord.get("Correo")
              
            );

    	  developerTutorialList.add(developerTutorial);
      }

      return developerTutorialList;
    } catch (IOException e) {
      throw new RuntimeException("fallo al analizar el archivo CSV:" + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Cliente> developerTutorialList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Cliente developerTutorial : developerTutorialList) {
        List<String> data = Arrays.asList(
              String.valueOf(developerTutorial.getId()),
              developerTutorial.getDocCliente(),
              developerTutorial.getTipoDoc(),
              developerTutorial.getNombres(),
              developerTutorial.getApellidos(),
              developerTutorial.getTelefono(),
              developerTutorial.getCorreo()
              
            );

        csvPrinter.printRecord(data);
      }

      csvPrinter.flush();
      return new ByteArrayInputStream(out.toByteArray());
    } catch (IOException e) {
      throw new RuntimeException("no se pueden importar datos al archivo CSV: " + e.getMessage());
    }
  }
}
