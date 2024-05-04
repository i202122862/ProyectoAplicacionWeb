package com.example.demo.controller;


import com.example.demo.interfaces.UserRepository;
import com.example.demo.modelo.RoleEntity;
import com.example.demo.modelo.UserEntity;
import com.example.demo.security.ERole;
import com.example.demo.security.JWTUtils;
import com.example.demo.service.UserDetailServiceImpl;
import com.example.demo.util.dto.LoginDTO;
import com.example.demo.util.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
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

    //@PreAuthorize("hasRole('ROLE_USER')")//pruebas para roles

    @GetMapping("/test-role")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAdminData() {

        // Código que solo debe ser accesible por usuarios con rol ADMIN
        return ResponseEntity.ok("Datos sensibles de Administrador");
    }


}
