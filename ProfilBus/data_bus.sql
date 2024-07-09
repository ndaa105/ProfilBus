CREATE TABLE users (
    nama VARCHAR(30) PRIMARY KEY,
    email VARCHAR(40),
    password VARCHAR(20),
    umur INTEGER,
    address VARCHAR(50),
    profile_image VARCHAR
);
CREATE TABLE bus (
    nama_bus VARCHAR(10)
);
CREATE TABLE halte (
    nama_bus VARCHAR(10),
    nama_halte VARCHAR(30),
    FOREIGN KEY (nama_bus) REFERENCES bus(nama_bus)
);