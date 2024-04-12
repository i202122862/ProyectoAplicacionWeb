package com.example.demo.reporte;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface InterfaceExporterExcel {

    void escribirCabeceraDeTabla();

    void escribirDatosDeLaTabla();

    void exportar(HttpServletResponse response) throws IOException;
}
