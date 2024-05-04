package com.example.demo.controller;

import com.example.demo.interfaceservice.ICustomerService;
import com.example.demo.interfaceservice.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class TestController {

    @Autowired
    private ICustomerService service;

    @GetMapping("/test")
    public ResponseEntity<?> test(){

        return new ResponseEntity<>(service.listar(),HttpStatus.OK);
    }
}
