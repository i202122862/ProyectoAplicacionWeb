package com.example.demo.modelo;

import jakarta.persistence.*;


@Entity
@Table(name = "Productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductoID")
    private Integer productId;

    @Column(name = "Nombre", nullable = false)
    private String name;

    @Column(name = "Precio", nullable = false)
    private Double price;

    @Column(name = "Stock", nullable = false)
    private Integer stock;

    // Getters and setters


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}

