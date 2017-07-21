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
INSERT INTO modulo VALUES(1);
INSERT INTO modulo VALUES(2);
INSERT INTO modulo VALUES(3);
INSERT INTO modulo VALUES(4);
INSERT INTO modulo VALUES(5);
INSERT INTO modulo VALUES(6);
INSERT INTO modulo VALUES(7);
INSERT INTO modulo VALUES(8);
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Engenharia Civil', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Recursos Humanos', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Jornalismo', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Arquitetura', 'Bacharel');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'professor','memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=sigla,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'funcionario', 'memberOf=CN=sigla_func,OU=GRUPOS,OU=SiGLa,DC=sigla,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'estagiario', 'memberOf=CN=sigla_est,OU=GRUPOS,OU=SiGLa,DC=sigla,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'coordenador', 'memberOf=CN=sigla_coord,OU=GRUPOS,OU=SiGLa,DC=sigla,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'admin', 'memberOf=CN=sigla_admin,OU=GRUPOS,OU=SiGLa,DC=sigla,DC=thalesalv,DC=es');
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
