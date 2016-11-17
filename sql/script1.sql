DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE SEQUENCE seq_software;
CREATE SEQUENCE seq_reserva;
CREATE SEQUENCE seq_curso;
CREATE SEQUENCE seq_lab;
CREATE SEQUENCE seq_turma;

CREATE TABLE software (
	id INTEGER PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	fabricante VARCHAR(50) NOT NULL
);

CREATE TABLE curso (
	id INTEGER PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	modalidade VARCHAR(50) NOT NULL
);

CREATE TABLE laboratorio (
	id INTEGER PRIMARY KEY,
	numero VARCHAR(6) NOT NULL,
	qtd_comps INTEGER NOT NULL,
	qtd_alunos INTEGER NOT NULL
);

CREATE TABLE equipamento (
	nome VARCHAR(20) PRIMARY KEY,
	laboratorio INTEGER REFERENCES laboratorio(id),
	ip VARCHAR(15) NOT NULL,
	mac VARCHAR(30) NOT NULL,
	config VARCHAR NOT NULL,
	status INTEGER NOT NULL
);

CREATE TABLE reserva (
    id INT PRIMARY KEY,
    laboratorio INT REFERENCES laboratorio(id),
    softwares INT REFERENCES software(id),
    curso INT REFERENCES curso(id),
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    tipo INT NOT NULL,
    modulo VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);