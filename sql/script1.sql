DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- Criação das tabelas
CREATE TABLE curso (
    id integer PRIMARY KEY,
    nome character varying(50) NOT NULL,
    modalidade character varying(50) NOT NULL
);

CREATE TABLE equipamento (
    id INT PRIMARY KEY,
    nome character varying(20) NOT NULL,
    laboratorio integer,
    ip character varying(15) NOT NULL,
    mac character varying(30) NOT NULL,
    config character varying NOT NULL,
    status integer NOT NULL
);

CREATE TABLE laboratorio (
    id integer PRIMARY KEY,
    numero character varying(6) NOT NULL,
    qtd_comps integer NOT NULL,
    qtd_alunos integer NOT NULL
);

CREATE TABLE software (
    id integer PRIMARY KEY,
    nome character varying(50) NOT NULL,
    fabricante character varying(50) NOT NULL
);

CREATE TABLE grupo (
	id INT PRIMARY KEY,
	cargo VARCHAR NOT NULL,
	grupo VARCHAR NOT NULL
);

CREATE TABLE reserva (
    id integer PRIMARY KEY,
    laboratorio integer,
    softwares integer,
    curso integer,
    turma character varying NOT NULL,
    professor character varying NOT NULL,
    tipo integer NOT NULL,
    modulo character varying NOT NULL,
    dia_semana character varying NOT NULL,
    obs character varying
);

CREATE TABLE solicitacao_semestral (
    id INT PRIMARY KEY,
    softwares INT REFERENCES software(id),
    curso INT REFERENCES curso(id),
    turma VARCHAR,
    professor VARCHAR NOT NULL,
    modulo VARCHAR NOT NULL,
    obs VARCHAR NOT NULL
);

-- Criação das Sequences
CREATE SEQUENCE seq_curso;	
CREATE SEQUENCE seq_lab;	
CREATE SEQUENCE seq_reserva;
CREATE SEQUENCE seq_software;
CREATE SEQUENCE seq_turma;
CREATE SEQUENCE seq_grupo;
CREATE SEQUENCE seq_soli_semes;
	
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
INSERT INTO curso VALUES (NEXTVAL('seq_curso'), 'Sistemas de Informação', 'Bacharel');
INSERT INTO curso VALUES (NEXTVAL('seq_curso'), 'Gestão de Projetos', 'MBA');
INSERT INTO laboratorio VALUES (NEXTVAL('seq_lab'), '31-21', 25, 50);
INSERT INTO laboratorio VALUES (NEXTVAL('seq_lab'), '12-14', 25, 50);
INSERT INTO laboratorio VALUES (NEXTVAL('seq_lab'), '12-20', 25, 50);
INSERT INTO laboratorio VALUES (NEXTVAL('seq_lab'), '12-13', 25, 50);
INSERT INTO laboratorio VALUES (NEXTVAL('seq_lab'), '12-10', 25, 50);
INSERT INTO software VALUES (NEXTVAL('seq_software'), 'NetBeans', 'Oracle');
INSERT INTO software VALUES (NEXTVAL('seq_software'), 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES (NEXTVAL('seq_software'), 'Creative Cloud', 'Adobe');
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 3, 1, 2, '2', 'daniellemartin', 1, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 2, 1, 2, '2', 'erikam', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 2, 1, 2, '2', 'daisyeb', 1, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 1, 1, 2, '2', 'wolley', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 2, 1, 2, '2', 'ptoledo', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 5, 1, 2, '2', 'andreaono', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (NEXTVAL('seq_reserva'), 2, 1, 2, '2', 'mapa', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'professor','memberOf=CN=professores-mg,OU=PRPPE,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'funcionario', 'memberOf=CN=DEPTI,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'estagiario', 'memberOf=CN=DEPTI_Estagio,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES (NEXTVAL('seq_grupo'), 'coordenador', 'memberOf=CN=COORDENADORES,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO solicitacao_semestral VALUES (NEXTVAL('seq_soli_semes'), 1, 1, '1ºB', 'daniellemartin', '1º módulo', 'Sem observações.');
INSERT INTO solicitacao_semestral VALUES (NEXTVAL('seq_soli_semes'), 1, 1, '2ºB', 'erikam', '2º módulo', 'Sem observações.');
INSERT INTO solicitacao_semestral VALUES (NEXTVAL('seq_soli_semes'), 1, 1, '3ºB', 'adilson', '3º módulo', 'Sem observações.');

-- Definição de Constraints
ALTER TABLE ONLY equipamento ADD CONSTRAINT equipamento_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_curso_fkey FOREIGN KEY (curso) REFERENCES curso(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_softwares_fkey FOREIGN KEY (softwares) REFERENCES software(id);
