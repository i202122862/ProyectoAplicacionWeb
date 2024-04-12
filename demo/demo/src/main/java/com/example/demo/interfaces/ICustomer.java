package com.example.demo.interfaces;

import com.example.demo.modelo.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomer extends CrudRepository<Customer,Integer> {
}
