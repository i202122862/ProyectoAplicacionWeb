package com.example.demo.reporte;

import com.example.demo.modelo.Customer;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class CustomerExporterExcel implements InterfaceExporterExcel{

    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    private List<Customer> listaPersonas;

    public CustomerExporterExcel(List<Customer> listaPersonas) {
        libro = new XSSFWorkbook();
        hoja = libro.createSheet("Excel");
        this.listaPersonas = listaPersonas;
    }

    @Override
    public void escribirCabeceraDeTabla() {
        Row fila = hoja.createRow(0);

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setBold(true);
        fuente.setFontHeight(16);
        estilo.setFont(fuente);

        Cell celda = fila.createCell(0);
        celda.setCellValue("ID");
        celda.setCellStyle(estilo);

        celda = fila.createCell(1);
        celda.setCellValue("Nombres");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Mail");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("Telefonos");
        celda.setCellStyle(estilo);

        celda = fila.createCell(4);
        celda.setCellValue("Dirección");
        celda.setCellStyle(estilo);
    }

    @Override
    public void escribirDatosDeLaTabla() {
        int numeroFilas = 1;

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for(Customer persona : listaPersonas) {
            Row fila = hoja.createRow(numeroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(persona.getCustomerId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(persona.getName());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(persona.getEmail());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);

            celda = fila.createCell(3);
            celda.setCellValue(persona.getPhone());
            hoja.autoSizeColumn(3);
            celda.setCellStyle(estilo);

            celda = fila.createCell(4);
            celda.setCellValue(persona.getAddress());
            hoja.autoSizeColumn(4);
            celda.setCellStyle(estilo);
        }
    }

    @Override
    public void exportar(HttpServletResponse response) throws IOException {

        escribirCabeceraDeTabla();
        escribirDatosDeLaTabla();

        ServletOutputStream outPutStream = response.getOutputStream();
        libro.write(outPutStream);
        libro.close();
        outPutStream.close();
    }
}
