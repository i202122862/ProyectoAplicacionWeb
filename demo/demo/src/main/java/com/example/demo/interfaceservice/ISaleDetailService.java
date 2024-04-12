package com.example.demo.interfaceservice;

import com.example.demo.modelo.Employee;
import com.example.demo.modelo.SaleDetail;

import java.util.List;
import java.util.Optional;

public interface ISaleDetailService {

    //Declaracion de los Metodos del CRUD
    public List<SaleDetail> listar();
    public Optional<SaleDetail> listarId(Integer id);
    public int guardar(SaleDetail p);
    public void eliminar(Integer id);
}
