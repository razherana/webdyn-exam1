-- Active: 1738011862925@@127.0.0.1@3306@exam1S4_naina
CREATE DATABASE exam1S4_naina;

CREATE TABLE webdyn1_prevision (
  id INT PRIMARY KEY AUTO_INCREMENT,
  libelle VARCHAR(50) NOT NULL UNIQUE,
  montant DOUBLE NOT NULL
);

CREATE TABLE webdyn1_depense (
  id INT PRIMARY KEY AUTO_INCREMENT,
  prevision_id INT REFERENCES webdyn1_prevision(id),
  libelle VARCHAR(100) NOT NULL,
  montant DOUBLE NOT NULL,
  created_at DATETIME DEFAULT NOW()
);

CREATE TABLE webdyn1_user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL
);

INSERT INTO webdyn1_user (username, password) VALUES
('admin', 'admin');