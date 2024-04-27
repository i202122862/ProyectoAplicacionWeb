package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody Login login){

        System.out.println("prueba....");
        System.out.println(login.username);
        System.out.println(login.password);

        return new ResponseEntity<>("",HttpStatus.OK);
    }
}


class Login{
    public String username;
    public String password;
}