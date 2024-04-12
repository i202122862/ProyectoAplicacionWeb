package com.example.demo.interfaceservice;

import com.example.demo.modelo.Employee;
import com.example.demo.modelo.Sale;

import java.util.List;
import java.util.Optional;

public interface ISaleService {

    //Declaracion de los Metodos del CRUD
    public List<Sale> listar();
    public Optional<Sale> listarId(Integer id);
    public int guardar(Sale p);
    public void eliminar(Integer id);
}
