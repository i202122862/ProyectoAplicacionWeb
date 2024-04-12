package com.example.demo.controller;


import com.example.demo.interfaceservice.ICustomerService;
import com.example.demo.interfaceservice.IEmployeeService;
import com.example.demo.modelo.Customer;
import com.example.demo.modelo.Employee;
import com.example.demo.reporte.CustomerExporterExcel;
import com.example.demo.reporte.CustomerExporterPDF;
import com.example.demo.reporte.EmployeeExporterExcel;
import com.example.demo.reporte.EmployeeExporterPDF;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/empleado")
public class EmployeeController {

    @Autowired
    private IEmployeeService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Employee> personas = service.listar();
        model.addAttribute("personas", personas);
        return "empleadoindex";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Employee> persona = service.listarId(id);
        model.addAttribute("persona",persona);
        return "empleadoform";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id) {
        service.eliminar(id);
        return "redirect:/empleado/listar";
    }

    @GetMapping("/new")
    public String agregar(Model model) {
        model.addAttribute("persona", new Employee());
        return "empleadoform";
    }

    @PostMapping("/save")
    public String guardar(@Validated Employee p, Model model) {
        service.guardar(p);
        return "redirect:/empleado/listar";
    }


    @GetMapping("/exportarPDF")
    public void exportarListadoDePersonasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Personas_"+ fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Employee> personas = service.listar();

        EmployeeExporterPDF exporter = new EmployeeExporterPDF(personas);
        exporter.exportar(response);
    }

    @GetMapping("/exportarExcel")
    public void exportarListadoDePersonasEnExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Personas_"+ fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);

        List<Employee> personas = service.listar();

        EmployeeExporterExcel exporter = new EmployeeExporterExcel(personas);
        exporter.exportar(response);
    }
}
