package com.example.demo.interfaces;

import com.example.demo.modelo.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity,Long> {


}