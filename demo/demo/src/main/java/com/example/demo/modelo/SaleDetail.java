package com.example.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Detalle_Ventas")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Detalle_VentaID")
    private Integer saleDetailId;

    @ManyToOne
    @JoinColumn(name = "VentaID", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "ProductoID", nullable = false)
    private Product product;

    @Column(name = "Cantidad", nullable = false)
    private Integer quantity;

    @Column(name = "Precio_Venta", nullable = false)
    private Double salePrice;

    // Getters and setters


    public Integer getSaleDetailId() {
        return saleDetailId;
    }

    public void setSaleDetailId(Integer saleDetailId) {
        this.saleDetailId = saleDetailId;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }
}
