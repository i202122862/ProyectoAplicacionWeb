package com.example.demo.controller;


import com.example.demo.interfaces.UserRepository;
import com.example.demo.modelo.RoleEntity;
import com.example.demo.modelo.UserEntity;
import com.example.demo.security.ERole;
import com.example.demo.util.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SecurityController {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userToaRepository;

    @PostMapping("create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO){

        Set<RoleEntity> roleEntitySet=userDTO.getRoles().stream()
                .map(role ->RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build()).collect(Collectors.toSet());

        UserEntity userToaEntity=UserEntity.builder().
                mail(userDTO.getEmail()).password(passwordEncoder.encode(userDTO.getPassword()))
                .username(userDTO.getUsername()).
                roles(roleEntitySet).
                build();


        userToaRepository.save(userToaEntity);

        return new ResponseEntity<>(userToaEntity, HttpStatus.OK);
    }


    public ResponseEntity<?> deleteUser(@RequestParam String id){
        userToaRepository.deleteById(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
