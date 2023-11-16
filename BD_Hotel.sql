CREATE DATABASE BD_HOTEL;
USE BD_HOTEL;

CREATE TABLE Habitacion(
	idhabitacion INT NOT NULL AUTO_INCREMENT,
    numero VARCHAR(5) NOT NULL,
    piso VARCHAR(2) NOT NULL,
    descripcion VARCHAR(250) DEFAULT NULL,
    precio DOUBLE NOT NULL,
    estado VARCHAR(15) NOT NULL,
    tipohabitacion VARCHAR(20) NOT NULL,
    PRIMARY KEY(idhabitacion)
);

CREATE TABLE Persona(
	idpersona INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(40) NOT NULL,
    apellido VARCHAR(40) NOT NULL,
    tipodocumento VARCHAR(15) NOT NULL,
    numdocumento VARCHAR(15) NOT NULL,
    telefono VARCHAR(20) DEFAULT NULL,
    email VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(idpersona),
	UNIQUE KEY telefono_unique (telefono),
    UNIQUE KEY email_unique (email)
);

CREATE TABLE Trabajador(
	idpersona INT NOT NULL,
    acceso VARCHAR(15) NOT NULL,
    usuario VARCHAR(15) NOT NULL,
    clave VARCHAR(20) NOT NULL,
    estado VARCHAR(1) NOT NULL,
    PRIMARY KEY(idpersona),
    UNIQUE KEY usuario_unique (usuario),
    FOREIGN KEY(idpersona) REFERENCES Persona(idpersona) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE Cliente(
	idpersona INT NOT NULL,
    codcliente VARCHAR(10) NOT NULL,
    PRIMARY KEY(idpersona),
    UNIQUE KEY codigo_cliente_unique (codcliente),
    FOREIGN KEY(idpersona) REFERENCES Persona(idpersona) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE Producto(
	idproducto INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(45) NOT NULL,
    descripcion VARCHAR(255) DEFAULT NULL,
    precio DOUBLE NOT NULL,
    PRIMARY KEY(idproducto)
);

CREATE TABLE Reserva(
	idreserva INT NOT NULL AUTO_INCREMENT,
    idhabitacion INT NOT NULL,
    idcliente INT NOT NULL,
    idtrabajador INT NOT NULL,
    tiporeserva VARCHAR(20) NOT NULL,
    fechareserva DATE NOT NULL,
    fechaingreso DATE NOT NULL,
    fechasalida DATE NOT NULL,
    costoalojamiento DOUBLE NOT NULL,
    estado VARCHAR(15) NOT NULL,
    PRIMARY KEY(idreserva),
    FOREIGN KEY(idcliente) REFERENCES Cliente(idpersona),
    FOREIGN KEY(idhabitacion) REFERENCES Habitacion(idhabitacion),
    FOREIGN KEY(idtrabajador) REFERENCES Trabajador(idpersona)
);

CREATE TABLE Consumo(
	idconsumo INT NOT NULL AUTO_INCREMENT,
    idreserva INT NOT NULL,
    idproducto INT NOT NULL,
    cantidad DOUBLE NOT NULL,
    precioventa DOUBLE NOT NULL,
    estado VARCHAR(15) NOT NULL,
    PRIMARY KEY(idconsumo),
    FOREIGN KEY(idreserva) REFERENCES Reserva(idreserva),
    FOREIGN KEY(idproducto) REFERENCES Producto(idproducto)
);

CREATE TABLE Pago(
	idpago INT NOT NULL AUTO_INCREMENT,
    idreserva INT NOT NULL,
    tipocomprobante VARCHAR(20) NOT NULL,
    numcomprobante VARCHAR(20) NOT NULL,
    igv DOUBLE NOT NULL,
    pagototal DOUBLE NOT NULL,
    fechaemision DATE NOT NULL,
    fechapago DATE NOT NULL,
    PRIMARY KEY(idpago),
    FOREIGN KEY(idreserva) REFERENCES Reserva(idreserva) ON DELETE NO ACTION ON UPDATE NO ACTION
);