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

import com.proyectoSisi.model.Producto_procesado;


public class CSVHelperProduct {
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "IdProduct", "CodProduct", "Nom_Product", "Contenido_Product", "Unidad_Medida", "Presentacion_Producto", "CostoProduccion", "ValorUnitarioVenta", "Stock_Minimo" };

  public static boolean hasCSVFormat(MultipartFile file) {
 System.out.println(file.getContentType());
    if (TYPE.equals(file.getContentType())
    		|| file.getContentType().equals("application/vnd.ms-excel")) {
      return true;
    }

    return false;
  }

  public static List<Producto_procesado> csvToTutorials(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Producto_procesado> developerTutorialList = new ArrayList<>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
    	  Producto_procesado developerTutorial = new Producto_procesado(
              Integer.parseInt(csvRecord.get("IdProduct")),
              Integer.parseInt(csvRecord.get("CodProduct")),
              csvRecord.get("Nom_Product"),
              Integer.parseInt(csvRecord.get("Contenido_Product")),
              csvRecord.get("Unidad_Medida"),
              csvRecord.get("Presentacion_Producto"),
              Double.parseDouble(csvRecord.get("CostoProduccion")),
              Double.parseDouble(csvRecord.get("ValorUnitarioVenta")),
              Integer.parseInt(csvRecord.get("Stock_Minimo"))
              
            );

    	  developerTutorialList.add(developerTutorial);
      }

      return developerTutorialList;
    } catch (IOException e) {
      throw new RuntimeException("fallo al analizar el archivo CSV:" + e.getMessage());
    }
  }

  public static ByteArrayInputStream tutorialsToCSV(List<Producto_procesado> developerTutorialList) {
    final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

    try (ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
      for (Producto_procesado developerTutorial : developerTutorialList) {
        List<String> data = Arrays.asList(
              String.valueOf(developerTutorial.getIdProduct()),
              String.valueOf(developerTutorial.getCodProduct()),
              developerTutorial.getNom_Product(),
              String.valueOf(developerTutorial.getContenido_Product()),
              developerTutorial.getUnidad_Medida(),
              developerTutorial.getPresentacion_Producto(),
              String.valueOf(developerTutorial.getCostoProduccion()),
              String.valueOf(developerTutorial.getValorUnitarioVenta()),
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
