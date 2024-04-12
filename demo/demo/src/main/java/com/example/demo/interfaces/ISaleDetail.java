package com.example.demo.interfaces;

import com.example.demo.modelo.Sale;
import com.example.demo.modelo.SaleDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleDetail extends CrudRepository<SaleDetail,Integer> {
}
