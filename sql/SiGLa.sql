DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

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
    modulo VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE solicitacao (
    id INT PRIMARY KEY,
    curso INT REFERENCES curso(id) NOT NULL,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    modulo VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
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
	
-- Definições de propriedade
ALTER TABLE curso OWNER TO sigla;
ALTER TABLE equipamento OWNER TO sigla;
ALTER TABLE laboratorio OWNER TO sigla;
ALTER TABLE reserva OWNER TO sigla;
ALTER TABLE seq_curso OWNER TO sigla;
ALTER TABLE seq_lab OWNER TO sigla;
ALTER TABLE seq_reserva OWNER TO sigla;
ALTER TABLE seq_software OWNER TO sigla;
ALTER TABLE seq_turma OWNER TO sigla;
ALTER TABLE software OWNER TO sigla;

-- Inserção de Valores
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Engenharia Civil', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Recursos Humanos', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Jornalismo', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Arquitetura', 'Bacharel');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'professor','memberOf=CN=professores-mg,OU=PRPPE,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'funcionario', 'memberOf=CN=DEPTI,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'estagiario', 'memberOf=CN=DEPTI_Estagio,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'coordenador', 'memberOf=CN=COORDENADORES,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'NetBeans', 'Oracle');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'AppInventor', 'MIT');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'PostgreSQL', 'EnterpriseDB');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'MatLab', 'Mathworks');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'LabVIEW', 'National Instruments');
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-10', 25, 50);
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-13', 25, 50);
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-14', 25, 50);
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-17', 25, 50);
