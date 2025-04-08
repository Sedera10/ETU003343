CREATE DATABASE examen;
use examen;

CREATE TABLE admins(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL
);

INSERT INTO admins(nom,password) VALUES('Sedera','success');


CREATE TABLE credits(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(200) NOT NULL,
    montant DECIMAL
);

CREATE TABLE depenses(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idCredit INT NOT NULL,
    montant DECIMAL,
    date DATE
);