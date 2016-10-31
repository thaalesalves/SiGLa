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

CREATE TABLE turma (
	id INTEGER PRIMARY KEY,
	curso INTEGER REFERENCES curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
	semestre INTEGER,
	turma VARCHAR NOT NULL,
	qtd_alunos INTEGER NOT NULL,
	ano_letivo VARCHAR NOT NULL
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
	id INTEGER PRIMARY KEY,
	laboratorio INTEGER REFERENCES laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE,
	softwares INTEGER REFERENCES software(id) ON DELETE CASCADE ON UPDATE CASCADE,
	turma INTEGER REFERENCES turma(id) ON DELETE CASCADE ON UPDATE CASCADE,
	curso INTEGER REFERENCES curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
	professor VARCHAR NOT NULL,
	tipo INTEGER NOT NULL,
	data_ent DATE,
	data_sai DATE,
	hor_ent VARCHAR,
	hor_sai VARCHAR
);