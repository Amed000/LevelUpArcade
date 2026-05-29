CREATE DATABASE IF NOT EXISTS levelup_arcade;
USE levelup_arcade;
-- =========================
-- USUARIOS (FASE 7 SEGURIDAD)
-- =========================
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol ENUM('ADMIN', 'EMPLEADO') NOT NULL
);
-- =========================
-- CLIENTES
-- =========================
CREATE TABLE cliente (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefono VARCHAR(20)
);
-- =========================
-- PROVEEDORES
-- =========================
CREATE TABLE proveedor (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);
-- =========================
-- CATEGORÍAS
-- =========================
CREATE TABLE categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);
-- =========================
-- PRODUCTOS
-- =========================
CREATE TABLE producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    stock INT NOT NULL CHECK (stock >= 0),
    id_categoria INT NOT NULL,
    id_proveedor INT NOT NULL,

    FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)
        ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
        ON DELETE CASCADE ON UPDATE CASCADE
);
-- =========================
-- PEDIDOS
-- =========================
CREATE TABLE pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    estado ENUM('PENDIENTE', 'PREPARADO', 'ENVIADO', 'ENTREGADO', 'CANCELADO') NOT NULL,
    id_cliente INT NOT NULL,
    id_usuario INT NOT NULL,

    FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente)
        ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);
-- =========================
-- DETALLE PEDIDO
-- =========================
CREATE TABLE detalle_pedido (
    id_detalle INT AUTO_INCREMENT PRIMARY KEY,
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario >= 0),

    FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
        ON DELETE CASCADE ON UPDATE CASCADE,

    FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
        ON DELETE CASCADE ON UPDATE CASCADE
);
-- =========================
-- DATOS INICIALES (PARA TESTS)
-- =========================

INSERT INTO categoria (nombre) VALUES ('Consolas'), ('Videojuegos');

INSERT INTO proveedor (nombre, telefono)
VALUES ('Sony', '111111111'), ('Nintendo', '222222222');

INSERT INTO cliente (nombre, email, telefono)
VALUES ('Cliente Test', 'test@test.com', '600000000');

INSERT INTO usuario (username, password, rol)
VALUES
('admin', 'admin123', 'ADMIN'),
('empleado', 'emp123', 'EMPLEADO');

select * from usuario;
select * from producto;

ALTER TABLE pedido
MODIFY id_cliente INT NULL;

ALTER TABLE pedido
MODIFY id_usuario INT NULL;