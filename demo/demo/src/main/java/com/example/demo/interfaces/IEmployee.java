package com.example.demo.interfaces;

import com.example.demo.modelo.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployee extends CrudRepository<Employee,Integer> {
}
