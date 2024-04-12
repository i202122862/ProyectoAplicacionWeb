package com.example.demo.controller;

import com.example.demo.interfaceservice.ICustomerService;
import com.example.demo.interfaceservice.IEmployeeService;
import com.example.demo.interfaceservice.IProductService;
import com.example.demo.interfaceservice.ISaleService;
import com.example.demo.modelo.Employee;
import com.example.demo.modelo.Product;
import com.example.demo.modelo.Sale;
import com.example.demo.modelo.SaleDetail;
import com.example.demo.reporte.EmployeeExporterExcel;
import com.example.demo.reporte.EmployeeExporterPDF;
import com.example.demo.reporte.SaleExporterExcel;
import com.example.demo.reporte.SaleExporterPDF;
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
import java.util.*;

@Controller
@RequestMapping(value = "/venta")
public class SaleController {

    @Autowired
    private ISaleService service;

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IProductService productService;
    ;


    @GetMapping("/listar")
    public String listar(Model model) {
        List<Sale> personas = service.listar();
        model.addAttribute("personas", personas);
        return "saleindex";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Optional<Sale> saleOptional = service.listarId(id);

        Sale persona = saleOptional.get();
        if (persona.getSaleDetails().isEmpty()) {
            persona.getSaleDetails().add(new SaleDetail());
        }

        model.addAttribute("persona",persona);
        model.addAttribute("allCustomers", customerService.listar()); // listAll() should return all customers
        model.addAttribute("allEmployees", employeeService.listar()); // listAll() should return all employees
        model.addAttribute("allProducts", productService.listar()); // Assuming you have a productService

        return "saleform";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(Model model, @PathVariable int id) {
        service.eliminar(id);
        return "redirect:/venta/listar";
    }

    @GetMapping("/new")
    public String agregar(Model model) {
        Sale sale = new Sale();
        sale.setSaleDetails(Arrays.asList(new SaleDetail())); // Initialize with one empty SaleDetail

        model.addAttribute("persona", sale);

        model.addAttribute("allCustomers", customerService.listar()); // listAll() should return all customers
        model.addAttribute("allEmployees", employeeService.listar()); // listAll() should return all employees
        model.addAttribute("allProducts", productService.listar()); // Assuming you have a productService

        return "saleform";
    }

    @PostMapping("/save")
    public String guardar(@Validated Sale p, Model model) {

        p.getSaleDetails().removeIf(detail ->
                detail.getProduct() == null || detail.getQuantity() == null || detail.getSalePrice() == null);


        for (SaleDetail detail : p.getSaleDetails()) {
            detail.setSale(p); // Set the current sale to each detail
        }

        service.guardar(p);
        return "redirect:/venta/listar";
    }

    @GetMapping("/exportarPDF")
    public void exportarListadoDePersonasEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Personas_"+ fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Sale> personas = service.listar();

        SaleExporterPDF exporter = new SaleExporterPDF(personas);
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

        List<Sale> personas = service.listar();

        SaleExporterExcel exporter = new SaleExporterExcel(personas);
        exporter.exportar(response);
    }
}
