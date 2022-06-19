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

import com.proyectoSisi.model.Materia_prima;


public class CSVHelperMaterial {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "IdMateriaPrima", "CodMateriaPrima", "NomMateriaPrima", "Contenido_Product", "Unidad_Medidad", "Presentacion_Producto", "Stock_Minimo" };

  public static boolean hasCSVFormat(MultipartFile file) {
 System.out.println(file.getContentType());
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<Materia_prima> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Materia_prima> developerTutorialList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Materia_prima developerTutorial = new Materia_prima(
              Integer.parseInt(csvRecord.get("IdMateriaPrima")),
              Integer.parseInt(csvRecord.get("CodMateriaPrima")),
              csvRecord.get("NomMateriaPrima"),
              Integer.parseInt(csvRecord.get("Contenido_Product")),
              csvRecord.get("Unidad_Medidad"),
              csvRecord.get("Presentacion_Producto"),
              Integer.parseInt(csvRecord.get("Stock_Minimo"))
              
            );

    	  developerTutorialList.add(developerTutorial);
      }

      return developerTutorialList;
    } catch (IOException e) {
      throw new RuntimeException("fallo al analizar el archivo CSV:" + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Materia_prima> developerTutorialList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Materia_prima developerTutorial : developerTutorialList) {
        List<String> data = Arrays.asList(
              String.valueOf(developerTutorial.getIdMateriaPrima()),
              String.valueOf(developerTutorial.getCodMateriaPrima()),
              developerTutorial.getNomMateriaPrima(),
              String.valueOf(developerTutorial.getContenido_Product()),
              developerTutorial.getUnidad_Medidad(),
              developerTutorial.getPresentacion_Producto(),
              String.valueOf(developerTutorial.getStock_Minimo())
              
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
