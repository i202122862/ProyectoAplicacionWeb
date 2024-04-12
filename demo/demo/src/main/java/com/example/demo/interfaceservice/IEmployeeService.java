package com.example.demo.interfaceservice;

import com.example.demo.modelo.Customer;
import com.example.demo.modelo.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    //Declaracion de los Metodos del CRUD
    public List<Employee> listar();
    public Optional<Employee> listarId(Integer id);
    public int guardar(Employee p);
    public void eliminar(Integer id);
}
