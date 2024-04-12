package com.example.demo.reporte;

import com.example.demo.modelo.Customer;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CustomerExporterPDF implements InterfaceExporterPDF{

    private List<Customer> listaPersonas;

    public CustomerExporterPDF(List<Customer> listaPersonas) {
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

        celda.setPhrase(new Phrase("Nombres",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Mail",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Telefonos",fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Dirección",fuente));
        tabla.addCell(celda);

    }

    @Override
    public void escribirDatosDeLaTabla(PdfPTable tabla) {

        for(Customer persona : listaPersonas) {
            tabla.addCell(String.valueOf(persona.getCustomerId()));
            tabla.addCell(persona.getName());
            tabla.addCell(persona.getEmail());
            tabla.addCell(persona.getPhone());
            tabla.addCell(persona.getAddress());
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

        Paragraph titulo = new Paragraph("Lista de Clientes", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);

        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(80);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {2f,6f,8f,4f,4f});
        tabla.setWidthPercentage(80);

        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);

        documento.add(tabla);
        documento.close();

    }
}
