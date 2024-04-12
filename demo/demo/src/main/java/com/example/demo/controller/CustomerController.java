package com.example.demo.controller;

import com.example.demo.interfaceservice.ICustomerService;
import com.example.demo.modelo.Customer;
import com.example.demo.reporte.CustomerExporterExcel;
import com.example.demo.reporte.CustomerExporterPDF;
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
@RequestMapping(value = "/cliente")
public class CustomerController {

    @Autowired
    private ICustomerService service;

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Customer> personas = service.listar();
        model.addAttribute("personas", personas);
        return "clienteindex";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Customer> persona = service.listarId(id);
        model.addAttribute("persona",persona);
        return "clienteform";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id) {
        service.eliminar(id);
        return "redirect:/cliente/listar";
    }

    @GetMapping("/new")
    public String agregar(Model model) {
        model.addAttribute("persona", new Customer());
        return "clienteform";
    }

    @PostMapping("/save")
    public String guardar(@Validated Customer p, Model model) {
        service.guardar(p);
        return "redirect:/cliente/listar";
    }


    @GetMapping("/exportarPDF")
    public void exportarListadoDePersonasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Personas_"+ fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Customer> personas = service.listar();

        CustomerExporterPDF exporter = new CustomerExporterPDF(personas);
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

        List<Customer> personas = service.listar();

        CustomerExporterExcel exporter = new CustomerExporterExcel(personas);
        exporter.exportar(response);
    }
}
