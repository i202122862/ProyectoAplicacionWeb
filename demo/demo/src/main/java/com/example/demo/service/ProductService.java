package com.example.demo.service;

import com.example.demo.interfaces.IProduct;
import com.example.demo.interfaceservice.IProductService;
import com.example.demo.modelo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    public IProduct iProduct;
    @Override
    public List<Product> listar() {
        return (List<Product>) iProduct.findAll();
    }

    @Override
    public Optional<Product> listarId(Integer id) {
        return iProduct.findById(id);
    }

    @Override
    public int guardar(Product p) {
        int res=0;
        Product product=iProduct.save(p);
        if(product.equals(null)){
            res=1;
        }
        return 0;
    }

    @Override
    public void eliminar(Integer id) {
        iProduct.deleteById(id);
    }
}
