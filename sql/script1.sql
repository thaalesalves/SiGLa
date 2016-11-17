DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-11-17 21:11:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12355)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2150 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 187 (class 1259 OID 41230)
-- Name: curso; Type: TABLE; Schema: public; Owner: sigla
--

CREATE TABLE curso (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    modalidade character varying(50) NOT NULL
);


ALTER TABLE curso OWNER TO sigla;

--
-- TOC entry 189 (class 1259 OID 41240)
-- Name: equipamento; Type: TABLE; Schema: public; Owner: sigla
--

CREATE TABLE equipamento (
    nome character varying(20) NOT NULL,
    laboratorio integer,
    ip character varying(15) NOT NULL,
    mac character varying(30) NOT NULL,
    config character varying NOT NULL,
    status integer NOT NULL
);


ALTER TABLE equipamento OWNER TO sigla;

--
-- TOC entry 188 (class 1259 OID 41235)
-- Name: laboratorio; Type: TABLE; Schema: public; Owner: sigla
--

CREATE TABLE laboratorio (
    id integer NOT NULL,
    numero character varying(6) NOT NULL,
    qtd_comps integer NOT NULL,
    qtd_alunos integer NOT NULL
);


ALTER TABLE laboratorio OWNER TO sigla;

--
-- TOC entry 190 (class 1259 OID 41253)
-- Name: reserva; Type: TABLE; Schema: public; Owner: sigla
--

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


ALTER TABLE reserva OWNER TO sigla;

--
-- TOC entry 183 (class 1259 OID 41219)
-- Name: seq_curso; Type: SEQUENCE; Schema: public; Owner: sigla
--

CREATE SEQUENCE seq_curso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_curso OWNER TO sigla;

--
-- TOC entry 184 (class 1259 OID 41221)
-- Name: seq_lab; Type: SEQUENCE; Schema: public; Owner: sigla
--

CREATE SEQUENCE seq_lab
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_lab OWNER TO sigla;

--
-- TOC entry 182 (class 1259 OID 41217)
-- Name: seq_reserva; Type: SEQUENCE; Schema: public; Owner: sigla
--

CREATE SEQUENCE seq_reserva
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_reserva OWNER TO sigla;

--
-- TOC entry 181 (class 1259 OID 41215)
-- Name: seq_software; Type: SEQUENCE; Schema: public; Owner: sigla
--

CREATE SEQUENCE seq_software
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_software OWNER TO sigla;

--
-- TOC entry 185 (class 1259 OID 41223)
-- Name: seq_turma; Type: SEQUENCE; Schema: public; Owner: sigla
--

CREATE SEQUENCE seq_turma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE seq_turma OWNER TO sigla;

--
-- TOC entry 186 (class 1259 OID 41225)
-- Name: software; Type: TABLE; Schema: public; Owner: sigla
--

CREATE TABLE software (
    id integer NOT NULL,
    nome character varying(50) NOT NULL,
    fabricante character varying(50) NOT NULL
);


ALTER TABLE software OWNER TO sigla;

--
-- TOC entry 2141 (class 0 OID 41230)
-- Dependencies: 187
-- Data for Name: curso; Type: TABLE DATA; Schema: public; Owner: sigla
--

INSERT INTO curso VALUES (1, 'Sistemas de Informação', 'Bacharel');
INSERT INTO curso VALUES (2, 'Gestão de Projetos', 'MBA');


--
-- TOC entry 2143 (class 0 OID 41240)
-- Dependencies: 189
-- Data for Name: equipamento; Type: TABLE DATA; Schema: public; Owner: sigla
--



--
-- TOC entry 2142 (class 0 OID 41235)
-- Dependencies: 188
-- Data for Name: laboratorio; Type: TABLE DATA; Schema: public; Owner: sigla
--

INSERT INTO laboratorio VALUES (2, '12-14', 25, 50);
INSERT INTO laboratorio VALUES (3, '12-20', 25, 50);
INSERT INTO laboratorio VALUES (4, '12-13', 25, 50);
INSERT INTO laboratorio VALUES (5, '12-10', 25, 50);


--
-- TOC entry 2151 (class 0 OID 0)
-- Dependencies: 183
-- Name: seq_curso; Type: SEQUENCE SET; Schema: public; Owner: sigla
--

SELECT pg_catalog.setval('seq_curso', 2, true);


--
-- TOC entry 2152 (class 0 OID 0)
-- Dependencies: 184
-- Name: seq_lab; Type: SEQUENCE SET; Schema: public; Owner: sigla
--

SELECT pg_catalog.setval('seq_lab', 5, true);


--
-- TOC entry 2153 (class 0 OID 0)
-- Dependencies: 182
-- Name: seq_reserva; Type: SEQUENCE SET; Schema: public; Owner: sigla
--

SELECT pg_catalog.setval('seq_reserva', 22, true);


--
-- TOC entry 2154 (class 0 OID 0)
-- Dependencies: 181
-- Name: seq_software; Type: SEQUENCE SET; Schema: public; Owner: sigla
--

SELECT pg_catalog.setval('seq_software', 3, true);


--
-- TOC entry 2155 (class 0 OID 0)
-- Dependencies: 185
-- Name: seq_turma; Type: SEQUENCE SET; Schema: public; Owner: sigla
--

SELECT pg_catalog.setval('seq_turma', 1, false);


--
-- TOC entry 2140 (class 0 OID 41225)
-- Dependencies: 186
-- Data for Name: software; Type: TABLE DATA; Schema: public; Owner: sigla
--

INSERT INTO software VALUES (1, 'NetBeans', 'Oracle');
INSERT INTO software VALUES (2, 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES (3, 'Creative Cloud', 'Adobe');


--
-- TOC entry 2010 (class 2606 OID 41234)
-- Name: curso_pkey; Type: CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT curso_pkey PRIMARY KEY (id);


--
-- TOC entry 2014 (class 2606 OID 41247)
-- Name: equipamento_pkey; Type: CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY equipamento
    ADD CONSTRAINT equipamento_pkey PRIMARY KEY (nome);


--
-- TOC entry 2012 (class 2606 OID 41239)
-- Name: laboratorio_pkey; Type: CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY laboratorio
    ADD CONSTRAINT laboratorio_pkey PRIMARY KEY (id);


--
-- TOC entry 2016 (class 2606 OID 41260)
-- Name: reserva_pkey; Type: CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_pkey PRIMARY KEY (id);


--
-- TOC entry 2008 (class 2606 OID 41229)
-- Name: software_pkey; Type: CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY software
    ADD CONSTRAINT software_pkey PRIMARY KEY (id);


--
-- TOC entry 2017 (class 2606 OID 41248)
-- Name: equipamento_laboratorio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY equipamento
    ADD CONSTRAINT equipamento_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);


--
-- TOC entry 2020 (class 2606 OID 41271)
-- Name: reserva_curso_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_curso_fkey FOREIGN KEY (curso) REFERENCES curso(id);


--
-- TOC entry 2018 (class 2606 OID 41261)
-- Name: reserva_laboratorio_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_laboratorio_fkey FOREIGN KEY (laboratorio) REFERENCES laboratorio(id);


--
-- TOC entry 2019 (class 2606 OID 41266)
-- Name: reserva_softwares_fkey; Type: FK CONSTRAINT; Schema: public; Owner: sigla
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_softwares_fkey FOREIGN KEY (softwares) REFERENCES software(id);

	
--
-- TOC entry 2144 (class 0 OID 41253)
-- Dependencies: 190
-- Data for Name: reserva; Type: TABLE DATA; Schema: public; Owner: sigla
--

INSERT INTO reserva VALUES (3, 2, 1, 1, '10ºA', 'thalespereira', 1, '1º módulo', 'Segunda-feira', NULL);
INSERT INTO reserva VALUES (5, 2, 1, 2, '2', 'daniellemartin', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (6, 2, 1, 2, '2', 'erikam', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (7, 2, 1, 2, '2', 'daisyeb', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (8, 2, 1, 2, '2', 'wolley', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (9, 2, 1, 2, '2', 'ptoledo', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (10, 2, 1, 2, '2', 'morales', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (11, 2, 1, 2, '2', 'mori', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (12, 2, 1, 2, '2', 'adilson', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (13, 2, 1, 2, '2', 'ronaldo.vitoria', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (14, 2, 1, 2, '2', 'fretz.junior', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (15, 2, 1, 2, '2', 'andreaono', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (17, 2, 1, 2, '2', 'mapa', 2, '1º módulo', 'Sexta-feira', NULL);
INSERT INTO reserva VALUES (18, 2, 1, 2, '2', 'ricardostoll', 2, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO reserva VALUES (19, 2, 1, 2, '2', 'marciab', 1, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO reserva VALUES (20, 2, 1, 2, '2', 'rizieri', 1, '1º módulo', 'Quinta-feira', NULL);
INSERT INTO reserva VALUES (22, 2, 1, 2, '2', 'franciscazanettin', 1, '1º módulo', 'Quinta-feira', NULL);

-- Completed on 2016-11-17 21:11:06

--
-- PostgreSQL database dump complete
--

