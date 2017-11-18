-- Criação das tabelas
CREATE TABLE tb_curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    modalidade VARCHAR(50) NOT NULL
);

CREATE TABLE tb_laboratorio (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(6) NOT NULL,
    qtd_comps INT NOT NULL,
    qtd_alunos INT NOT NULL
);

CREATE TABLE tb_software (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    fabricante VARCHAR(50) NOT NULL
);

CREATE TABLE tb_equipamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    laboratorio INT REFERENCES tb_laboratorio(id),
    ip VARCHAR(15) NOT NULL,
    mac VARCHAR(30) NOT NULL,
    config VARCHAR(255) NOT NULL,
    motivo VARCHAR,
    data_retirada VARCHAR,
    status INT NOT NULL
);

CREATE TABLE tb_grupo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(20) NOT NULL,
    grupo VARCHAR(255) NOT NULL
);

CREATE TABLE tb_reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    laboratorio INT REFERENCES tb_laboratorio(id),
    curso INT REFERENCES tb_curso(id),
    qtd_alunos INT NOT NULL,
    turma VARCHAR(10) NOT NULL,
    professor VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    obs VARCHAR(255)
);

CREATE TABLE tb_solicitacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso INT REFERENCES tb_curso(id),
    qtd_alunos INT NOT NULL,
    turma VARCHAR(10) NOT NULL,
    professor VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    obs VARCHAR(255)
);

CREATE TABLE tb_modulo (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE aux_sw_res (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES tb_software(id),
    res INT REFERENCES tb_reserva(id)
);

CREATE TABLE aux_sw_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES tb_software(id),
    res INT REFERENCES tb_solicitacao(id)
);

CREATE TABLE aux_modulo_res (
    id INT AUTO_INCREMENT PRIMARY KEY,
    res INT REFERENCES tb_reserva(id),
    modulo INT REFERENCES tb_modulo(id)
);

CREATE TABLE aux_modulo_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
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
