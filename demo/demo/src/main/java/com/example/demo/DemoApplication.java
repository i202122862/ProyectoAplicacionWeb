package com.example.demo;

import com.example.demo.interfaces.IRoleRepository;
import com.example.demo.modelo.RoleEntity;
import com.example.demo.security.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	private IRoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner init(){
		return args -> {
			if (roleRepository.count() == 0) {
				Arrays.stream(ERole.values()).forEach(role -> {
					RoleEntity roleEntity = new RoleEntity();
					roleEntity.setName(role);
					roleRepository.save(roleEntity);
				});
			}
		};
	}
}
