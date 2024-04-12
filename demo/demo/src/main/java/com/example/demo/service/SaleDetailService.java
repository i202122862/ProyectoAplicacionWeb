package com.example.demo.service;

import com.example.demo.interfaces.ISaleDetail;
import com.example.demo.interfaceservice.ISaleDetailService;
import com.example.demo.interfaceservice.ISaleService;
import com.example.demo.modelo.Sale;
import com.example.demo.modelo.SaleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleDetailService implements ISaleDetailService {

    @Autowired
    public ISaleDetail iSaleDetail;

    @Override
    public List<SaleDetail> listar() {
        return (List<SaleDetail>) iSaleDetail.findAll();
    }

    @Override
    public Optional<SaleDetail> listarId(Integer id) {

        return iSaleDetail.findById(id);
    }

    @Override
    public int guardar(SaleDetail p) {
        int res=0;
        SaleDetail saleDetail= iSaleDetail.save(p);

        if(saleDetail.equals(null)){
            res=1;
        }
        return res;
    }

    @Override
    public void eliminar(Integer id) {
        iSaleDetail.deleteById(id);
    }
}
