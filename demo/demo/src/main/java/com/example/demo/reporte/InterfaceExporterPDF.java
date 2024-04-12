package com.example.demo.reporte;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface InterfaceExporterPDF {

    void escribirCabeceraDeLaTabla(PdfPTable tabla);

    void escribirDatosDeLaTabla(PdfPTable tabla);

    void exportar(HttpServletResponse response) throws DocumentException, IOException;
}
