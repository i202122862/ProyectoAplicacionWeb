package com.example.demo.reporte;

import com.example.demo.modelo.Product;
import com.example.demo.modelo.Sale;
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

public class SaleExporterExcel implements InterfaceExporterExcel{

    private XSSFWorkbook libro;
    private XSSFSheet hoja;
    private List<Sale> listaPersonas;

    public SaleExporterExcel(List<Sale> listaPersonas) {
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
        celda.setCellValue("Fecha_Venta");
        celda.setCellStyle(estilo);

        celda = fila.createCell(2);
        celda.setCellValue("Nombre_Cliente");
        celda.setCellStyle(estilo);

        celda = fila.createCell(3);
        celda.setCellValue("Nombre_Empleado");
        celda.setCellStyle(estilo);

    }

    @Override
    public void escribirDatosDeLaTabla() {
        int numeroFilas = 1;

        CellStyle estilo = libro.createCellStyle();
        XSSFFont fuente = libro.createFont();
        fuente.setFontHeight(14);
        estilo.setFont(fuente);

        for(Sale persona : listaPersonas) {
            Row fila = hoja.createRow(numeroFilas++);

            Cell celda = fila.createCell(0);
            celda.setCellValue(persona.getSaleId());
            hoja.autoSizeColumn(0);
            celda.setCellStyle(estilo);

            celda = fila.createCell(1);
            celda.setCellValue(persona.getSaleDate());
            hoja.autoSizeColumn(1);
            celda.setCellStyle(estilo);

            celda = fila.createCell(2);
            celda.setCellValue(persona.getCustomer().getName());
            hoja.autoSizeColumn(2);
            celda.setCellStyle(estilo);

            celda = fila.createCell(3);
            celda.setCellValue(persona.getEmployee().getName());
            hoja.autoSizeColumn(3);
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
