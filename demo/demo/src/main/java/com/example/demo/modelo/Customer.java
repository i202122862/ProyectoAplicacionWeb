    package com.example.demo.modelo;


    import jakarta.persistence.*;

    @Entity
    @Table(name = "Clientes")
    public class Customer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ClienteID")
        private Integer customerId;

        @Column(name = "Nombre", nullable = false)
        private String name;

        @Column(name = "Email", unique = true, nullable = false)
        private String email;

        @Column(name = "Telefono")
        private String phone;

        @Column(name = "Direccion")
        private String address;

        // Getters and setters


        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
