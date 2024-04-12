package com.example.demo.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmpleadoID")
    private Integer employeeId;

    @Column(name = "Nombre", nullable = false)
    private String name;

    @Column(name = "Cargo", nullable = false)
    private String position;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Telefono")
    private String phone;

    // Getters and setters


    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
