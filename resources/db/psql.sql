-- Criação das tabelas
CREATE TABLE curso (
    id INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    modalidade VARCHAR(50) NOT NULL
);

CREATE TABLE laboratorio (
    id INT PRIMARY KEY,
    numero VARCHAR(6) NOT NULL,
    qtd_comps INT NOT NULL,
    qtd_alunos INT NOT NULL
);

CREATE TABLE software (
    id INT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL
);

CREATE TABLE equipamento (
    id INT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    laboratorio INT REFERENCES laboratorio(id),
    ip VARCHAR(15) NOT NULL,
    mac VARCHAR(30) NOT NULL,
    config VARCHAR NOT NULL,
    status INT NOT NULL
);

CREATE TABLE grupo (
	id INT PRIMARY KEY,
	cargo VARCHAR NOT NULL,
	grupo VARCHAR NOT NULL
);

CREATE TABLE reserva (
    id INT PRIMARY KEY,
    laboratorio INT REFERENCES laboratorio(id) NOT NULL,
    curso INT REFERENCES curso(id) NOT NULL,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE solicitacao (
    id INT PRIMARY KEY,
    curso INT REFERENCES curso(id) NOT NULL,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE modulo (
    id INT PRIMARY KEY
);

CREATE TABLE sw_res (
    id INT PRIMARY KEY,
    sw INT REFERENCES software(id),
    res INT REFERENCES reserva(id)
);

CREATE TABLE sw_soli (
    id INT PRIMARY KEY,
    sw INT REFERENCES software(id),
    res INT REFERENCES solicitacao(id)
);

CREATE TABLE modulo_res (
    id INT PRIMARY KEY,
    res INT REFERENCES reserva(id),
    modulo INT REFERENCES modulo(id)
);

CREATE TABLE modulo_soli (
    id INT PRIMARY KEY,
    res INT REFERENCES solicitacao(id),
    modulo INT REFERENCES modulo(id)
);

-- Criação das Sequences
CREATE SEQUENCE seq_sw_res;
CREATE SEQUENCE seq_sw_soli;
CREATE SEQUENCE seq_curso;
CREATE SEQUENCE seq_lab;
CREATE SEQUENCE seq_reserva;
CREATE SEQUENCE seq_software;
CREATE SEQUENCE seq_turma;
CREATE SEQUENCE seq_grupo;
CREATE SEQUENCE seq_soli;
CREATE SEQUENCE seq_modulo_res;
CREATE SEQUENCE seq_modulo_soli;

-- Inserção de Valores
INSERT INTO modulo VALUES(1);
INSERT INTO modulo VALUES(2);
INSERT INTO modulo VALUES(3);
INSERT INTO modulo VALUES(4);
INSERT INTO modulo VALUES(5);
INSERT INTO modulo VALUES(6);
INSERT INTO modulo VALUES(7);
INSERT INTO modulo VALUES(8);
