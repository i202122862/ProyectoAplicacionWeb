package com.example.demo.service;

import com.example.demo.interfaces.ISale;
import com.example.demo.interfaceservice.ISaleService;
import com.example.demo.modelo.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService implements ISaleService {

    @Autowired
    public ISale iSale;
    @Override
    public List<Sale> listar() {
        return (List<Sale>) iSale.findAll();
    }

    @Override
    public Optional<Sale> listarId(Integer id) {
        return iSale.findById(id);
    }

    @Override
    public int guardar(Sale p) {
        int res=0;
        Sale sale=iSale.save(p);
        if(sale.equals(null)){
            res=1;
        }
        return res;
    }

    @Override
    public void eliminar(Integer id) {
        iSale.deleteById(id);
    }
}
