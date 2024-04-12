create database sistemaventas;
use sistemaventas;

CREATE TABLE Productos (
    ProductoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Precio DECIMAL(10, 2) NOT NULL,
    Stock INT NOT NULL
);

CREATE TABLE Clientes (
    ClienteID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Telefono VARCHAR(20),
    Direccion VARCHAR(255)
);


CREATE TABLE Empleados (
    EmpleadoID INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL,
    Cargo VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Telefono VARCHAR(20)
);

CREATE TABLE Ventas (
    VentaID INT AUTO_INCREMENT PRIMARY KEY,
    Fecha_Venta DATE,
    ClienteID INT NOT NULL,
    EmpleadoID INT,
    FOREIGN KEY (ClienteID) REFERENCES Clientes(ClienteID),
    FOREIGN KEY (EmpleadoID) REFERENCES Empleados(EmpleadoID)
);

CREATE TABLE Detalle_Ventas (
    Detalle_VentaID INT AUTO_INCREMENT PRIMARY KEY,
    VentaID INT NOT NULL,
    ProductoID INT NOT NULL,
    Cantidad INT NOT NULL,
    Precio_Venta DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (VentaID) REFERENCES Ventas(VentaID),
    FOREIGN KEY (ProductoID) REFERENCES Productos(ProductoID)
);

INSERT INTO Clientes (Nombre, Email, Telefono, Direccion)
VALUES
    ('Cliente 1', 'cliente1@example.com', '1234567890', 'Dirección 1'),
    ('Cliente 2', 'cliente2@example.com', '9876543210', 'Dirección 2'),
    ('Cliente 3', 'cliente3@example.com', '5555555555', 'Dirección 3'),
    ('Cliente 4', 'cliente4@example.com', '4444444444', 'Dirección 4'),
    ('Cliente 5', 'cliente5@example.com', '1111111111', 'Dirección 5'),
    ('Cliente 6', 'cliente6@example.com', '9999999999', 'Dirección 6');

INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 1', 10.99, 50);
INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 2', 15.50, 30);
INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 3', 20.00, 20);
INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 4', 25.75, 40);
INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 5', 30.50, 10);
INSERT INTO Productos (Nombre, Precio, Stock) VALUES ('Producto 6', 35.25, 25);



INSERT INTO Empleados (Nombre, Cargo, Email, Telefono) VALUES 
('Juan Perez', 'Gerente', 'juan.perez@email.com', '123-456-7890'),
('Ana Lopez', 'Contador', 'ana.lopez@email.com', '234-567-8901'),
('Carlos García', 'Analista', 'carlos.garcia@email.com', '345-678-9012'),
('Laura Martínez', 'Desarrollador', 'laura.martinez@email.com', '456-789-0123'),
('Sofia Rodríguez', 'Diseñador', 'sofia.rodriguez@email.com', '567-890-1234'),
('Miguel Hernández', 'Soporte Técnico', 'miguel.hernandez@email.com', '678-901-2345');




-- Assuming ClienteID and EmpleadoID are sequential starting from 1
INSERT INTO Ventas (Fecha_Venta, ClienteID, EmpleadoID) VALUES 
('2023-11-25', 1, 1), 
('2023-11-25', 2, 2), 
('2023-11-25', 3, 3), 
('2023-11-25', 4, 4), 
('2023-11-25', 5, 5), 
('2023-11-25', 6, 6);

INSERT INTO DetalleVentas (VentaID, ProductoID, Cantidad, PrecioVenta) VALUES 
(1, 1, 2, 10.99), 
(2, 2, 1, 15.50), 
(3, 3, 3, 20.00), 
(4, 4, 2, 25.75), 
(5, 5, 1, 30.50), 
(6, 6, 1, 35.25);