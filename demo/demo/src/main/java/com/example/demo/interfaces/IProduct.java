package com.example.demo.interfaces;

import com.example.demo.modelo.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProduct extends CrudRepository<Product,Integer> {


}
