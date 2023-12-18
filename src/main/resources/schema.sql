CREATE TABLE IF NOT EXISTS
original(
    serial INT NOT NULL AUTO_INCREMENT,
    modelo VARCHAR2(4),
    marca VARCHAR2(80),
    precio NUMERIC(10,2) DEFAULT 0,
    equipo VARCHAR2(30),
    PRIMARY KEY (serial));


