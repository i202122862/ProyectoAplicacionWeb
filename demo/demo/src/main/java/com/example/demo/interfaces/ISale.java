package com.example.demo.interfaces;

import com.example.demo.modelo.Sale;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISale extends CrudRepository<Sale,Integer> {
}
