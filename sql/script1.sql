DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- Criação das tabelas
CREATE TABLE curso (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    modalidade character varying(50) NOT NULL
);

CREATE TABLE equipamento (
    nome character varying(20) NOT NULL,
    laboratorio integer,
    ip character varying(15) NOT NULL,
    mac character varying(30) NOT NULL,
    config character varying NOT NULL,
    status integer NOT NULL
);

CREATE TABLE laboratorio (
    id integer NOT NULL,
    numero character varying(6) NOT NULL,
    qtd_comps integer NOT NULL,
    qtd_alunos integer NOT NULL
);

CREATE TABLE software (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    fabricante character varying(50) NOT NULL
);

CREATE TABLE grupo (
	id INT PRIMARY KEY,
	cargo VARCHAR NOT NULL,
	grupo VARCHAR NOT NULL
);

CREATE TABLE reserva (
    id integer NOT NULL,
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

-- Criação das Sequences
CREATE SEQUENCE seq_curso;	
CREATE SEQUENCE seq_lab;	
CREATE SEQUENCE seq_reserva;
CREATE SEQUENCE seq_software;
CREATE SEQUENCE seq_turma;
CREATE SEQUENCE seq_grupo;
	
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
INSERT INTO curso VALUES (1, 'Sistemas de Informação', 'Bacharel');
INSERT INTO curso VALUES (2, 'Gestão de Projetos', 'MBA');
INSERT INTO laboratorio VALUES (1, '31-21', 25, 50);
INSERT INTO laboratorio VALUES (2, '12-14', 25, 50);
INSERT INTO laboratorio VALUES (3, '12-20', 25, 50);
INSERT INTO laboratorio VALUES (4, '12-13', 25, 50);
INSERT INTO laboratorio VALUES (5, '12-10', 25, 50);
INSERT INTO software VALUES (1, 'NetBeans', 'Oracle');
INSERT INTO software VALUES (2, 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES (3, 'Creative Cloud', 'Adobe');
INSERT INTO reserva VALUES (5, 3, 1, 2, '2', 'daniellemartin', 1, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (6, 2, 1, 2, '2', 'erikam', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (7, 2, 1, 2, '2', 'daisyeb', 1, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (8, 1, 1, 2, '2', 'wolley', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (9, 2, 1, 2, '2', 'ptoledo', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (12, 2, 1, 2, '2', 'adilson', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (13, 4, 1, 2, '2', 'ronaldo.vitoria', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (14, 2, 1, 2, '2', 'fretz.junior', 1, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (15, 5, 1, 2, '2', 'andreaono', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (17, 2, 1, 2, '2', 'mapa', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (18, 3, 1, 2, '2', 'ricardostoll', 2, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO reserva VALUES (19, 2, 1, 2, '2', 'marciab', 1, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO reserva VALUES (22, 1, 1, 2, '2', 'franciscazanettin', 1, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO grupo VALUES(NEXTVAL('seq_grupo'), 'professor','memberOf=CN=professores-mg,OU=PRPPE,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES(NEXTVAL('seq_grupo'), 'funcionario', 'memberOf=CN=DEPTI,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES(NEXTVAL('seq_grupo'), 'estagiario', 'memberOf=CN=DEPTI_Estagio,OU=DEPTI,OU=Predio 5 - ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');
INSERT INTO grupo VALUES(NEXTVAL('seq_grupo'), 'coordenador', 'memberOf=CN=COORDENADORES,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br');

-- Definição de Constraints
ALTER TABLE ONLY curso ADD CONSTRAINT curso_pkey PRIMARY KEY (id);
ALTER TABLE ONLY equipamento ADD CONSTRAINT equipamento_pkey PRIMARY KEY (nome);
ALTER TABLE ONLY laboratorio ADD CONSTRAINT laboratorio_pkey PRIMARY KEY (id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);
ALTER TABLE ONLY software ADD CONSTRAINT software_pkey PRIMARY KEY (id);
ALTER TABLE ONLY equipamento ADD CONSTRAINT equipamento_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_curso_fkey FOREIGN KEY (curso) REFERENCES curso(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);
ALTER TABLE ONLY reserva ADD CONSTRAINT reserva_softwares_fkey FOREIGN KEY (softwares) REFERENCES software(id);
