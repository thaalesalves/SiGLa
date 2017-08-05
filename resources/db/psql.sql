-- Criação das tabelas
CREATE TABLE tb_curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    modalidade VARCHAR(50) NOT NULL
);

CREATE TABLE tb_laboratorio (
    id SERIAL PRIMARY KEY,
    numero VARCHAR(6) NOT NULL,
    qtd_comps INT NOT NULL,
    qtd_alunos INT NOT NULL
);

CREATE TABLE tb_software (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL
);

CREATE TABLE tb_equipamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    laboratorio INT REFERENCES tb_laboratorio(id),
    ip VARCHAR(15) NOT NULL,
    mac VARCHAR(30) NOT NULL,
    config VARCHAR NOT NULL,
    status INT NOT NULL
);

CREATE TABLE tb_grupo (
	id SERIAL PRIMARY KEY,
	cargo VARCHAR NOT NULL,
	grupo VARCHAR NOT NULL
);

CREATE TABLE tb_reserva (
    id SERIAL PRIMARY KEY,
    laboratorio INT REFERENCES tb_laboratorio(id) NOT NULL,
    curso INT REFERENCES tb_curso(id) NOT NULL,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE tb_solicitacao (
    id SERIAL PRIMARY KEY,
    curso INT REFERENCES tb_curso(id) NOT NULL,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE tb_modulo (
    id SERIAL PRIMARY KEY
);

CREATE TABLE aux_sw_res (
    id SERIAL PRIMARY KEY,
    sw INT REFERENCES tb_software(id), 
    res INT REFERENCES tb_reserva(id)
);

CREATE TABLE aux_sw_soli (
    id SERIAL PRIMARY KEY,
    sw INT REFERENCES tb_software(id),
    res INT REFERENCES tb_solicitacao(id)
);

CREATE TABLE aux_modulo_res (
    id SERIAL PRIMARY KEY,
    res INT REFERENCES tb_reserva(id),
    modulo INT REFERENCES tb_modulo(id)
);

CREATE TABLE aux_modulo_soli (
    id SERIAL PRIMARY KEY,
    res INT REFERENCES tb_solicitacao(id),
    modulo INT REFERENCES tb_modulo(id)
);

-- Inserção de Valores
INSERT INTO tb_modulo VALUES(1);
INSERT INTO tb_modulo VALUES(2);
INSERT INTO tb_modulo VALUES(3);
INSERT INTO tb_modulo VALUES(4);
INSERT INTO tb_modulo VALUES(5);
INSERT INTO tb_modulo VALUES(6);
INSERT INTO tb_modulo VALUES(7);
INSERT INTO tb_modulo VALUES(8);
