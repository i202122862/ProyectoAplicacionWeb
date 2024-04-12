package com.example.demo.interfaceservice;

import com.example.demo.modelo.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    //Declaracion de los Metodos del CRUD
    public List<Customer> listar();
    public Optional<Customer> listarId(Integer id);
    public int guardar(Customer p);
    public void eliminar(Integer id);

}
