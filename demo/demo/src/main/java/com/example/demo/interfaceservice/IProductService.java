package com.example.demo.interfaceservice;

import com.example.demo.modelo.Employee;
import com.example.demo.modelo.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    //Declaracion de los Metodos del CRUD
    public List<Product> listar();
    public Optional<Product> listarId(Integer id);
    public int guardar(Product p);
    public void eliminar(Integer id);
}
