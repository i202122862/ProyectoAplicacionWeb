package com.example.demo.service;

import com.example.demo.interfaces.ICustomer;
import com.example.demo.interfaceservice.ICustomerService;
import com.example.demo.modelo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    public ICustomer iCustomer;
    @Override
    public List<Customer> listar() {
        return (List<Customer>) iCustomer.findAll();
    }

    @Override
    public Optional<Customer> listarId(Integer id) {
        return iCustomer.findById(id);
    }

    @Override
    public int guardar(Customer p) {
        int res=0;
            Customer customer=iCustomer.save(p);
            if(customer.equals(null)){
                res=1;
            }
        return res;
    }

    @Override
    public void eliminar(Integer id) {
        iCustomer.deleteById(id);
    }
}
