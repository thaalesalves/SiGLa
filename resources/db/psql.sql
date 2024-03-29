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
    laboratorio INT REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ip VARCHAR(15) NOT NULL,
    mac VARCHAR(30) NOT NULL,
    config VARCHAR NOT NULL,
    status INT NOT NULL
);

CREATE TABLE tb_incidente (
    id SERIAL PRIMARY KEY,
    motivo VARCHAR NOT NULL,
    equipamento INTEGER REFERENCES tb_equipamento(id) ON DELETE CASCADE ON UPDATE CASCADE,
    data_retirada VARCHAR NOT NULL,
    data_devolucao VARCHAR,
    resolucao VARCHAR
);

CREATE TABLE tb_grupo (
    id SERIAL PRIMARY KEY,
    cargo VARCHAR NOT NULL,
    grupo VARCHAR NOT NULL
);

CREATE TABLE tb_reserva (
    id SERIAL PRIMARY KEY,
    laboratorio INT REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE,
    curso INT REFERENCES tb_curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE tb_solicitacao (
    id SERIAL PRIMARY KEY,
    curso INT REFERENCES tb_curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
    qtd_alunos INT NOT NULL,
    turma VARCHAR NOT NULL,
    professor VARCHAR NOT NULL,
    dia_semana VARCHAR NOT NULL,
    obs VARCHAR
);

CREATE TABLE tb_modulo (
    id SERIAL PRIMARY KEY
);

CREATE TABLE tb_fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    telefone VARCHAR,
    email VARCHAR
);

CREATE TABLE tb_representante (
    id SERIAL PRIMARY KEY,
    nome VARCHAR NOT NULL,
    telefone VARCHAR,
    email VARCHAR,
    fornecedor INTEGER REFERENCES tb_fornecedor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tb_licenca (
    id SERIAL PRIMARY KEY,
    aquisicao VARCHAR NOT NULL,
    vencimento VARCHAR NOT NULL,
    status INTEGER NOT NULL,
    software INTEGER REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    fornecedor INTEGER REFERENCES tb_fornecedor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tb_licenca_codigo (
    id SERIAL PRIMARY KEY,
    codigo_tipo VARCHAR NOT NULL,
    codigo VARCHAR NOT NULL,
    licenca INTEGER REFERENCES tb_licenca(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_res (
    id SERIAL PRIMARY KEY,
    sw INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    res INT REFERENCES tb_reserva(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_soli (
    id SERIAL PRIMARY KEY,
    sw INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    res INT REFERENCES tb_solicitacao(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_modulo_res (
    id SERIAL PRIMARY KEY,
    res INT REFERENCES tb_reserva(id) ON DELETE CASCADE ON UPDATE CASCADE,
    modulo INT REFERENCES tb_modulo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_modulo_soli (
    id SERIAL PRIMARY KEY,
    res INT REFERENCES tb_solicitacao(id) ON DELETE CASCADE ON UPDATE CASCADE,
    modulo INT REFERENCES tb_modulo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_lab (
    id SERIAL PRIMARY KEY,
    sw INTEGER REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    lab INTEGER REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE
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
