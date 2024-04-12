package com.example.demo.reporte;

import com.example.demo.modelo.Product;
import com.example.demo.modelo.Sale;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class SaleExporterPDF implements InterfaceExporterPDF{

    private List<Sale> listaPersonas;

    public SaleExporterPDF(List<Sale> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }


    @Override
    public void escribirCabeceraDeLaTabla(PdfPTable tabla) {
        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.RED);
        celda.setPadding(5);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("ID",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Fecha_Venta",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre_Cliente",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Nombre_Empleado",fuente));
        tabla.addCell(celda);

    }

    @Override
    public void escribirDatosDeLaTabla(PdfPTable tabla) {

        for(Sale persona : listaPersonas) {
            tabla.addCell(String.valueOf(persona.getSaleId()));
            tabla.addCell(String.valueOf(persona.getSaleDate()));
            tabla.addCell(persona.getCustomer().getName());
            tabla.addCell(persona.getEmployee().getName());
            ;
        }
    }

    @Override
    public void exportar(HttpServletResponse response) throws DocumentException, IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de Ventas", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(80);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {2f,6f,8f,4f});
        tabla.setWidthPercentage(80);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();

    }
}
