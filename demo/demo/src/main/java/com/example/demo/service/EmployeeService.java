package com.example.demo.service;

import com.example.demo.interfaces.IEmployee;
import com.example.demo.interfaceservice.IEmployeeService;
import com.example.demo.modelo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {


    @Autowired
    public IEmployee iEmployee;

    @Override
    public List<Employee> listar() {
        return (List<Employee>) iEmployee.findAll();
    }

    @Override
    public Optional<Employee> listarId(Integer id) {


        return iEmployee.findById(id);
    }

    @Override
    public int guardar(Employee p) {
        int res=0;
        Employee employee= iEmployee.save(p);
        if(employee.equals(null)){
            res=1;
        }

        return res;
    }

    @Override
    public void eliminar(Integer id) {

        iEmployee.deleteById(id);
    }
}
