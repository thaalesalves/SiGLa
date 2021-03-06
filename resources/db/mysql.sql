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
    laboratorio INT REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE,
    ip VARCHAR(15) NOT NULL,
    mac VARCHAR(30) NOT NULL,
    config VARCHAR(255) NOT NULL,
    status INT NOT NULL
);

CREATE TABLE tb_incidente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    motivo VARCHAR(255) NOT NULL,
    equipamento INT REFERENCES tb_equipamento(id) ON DELETE CASCADE ON UPDATE CASCADE,
    data_retirada VARCHAR(10) NOT NULL,
    data_devolucao VARCHAR(10),
    resolucao VARCHAR(255)
);

CREATE TABLE tb_grupo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cargo VARCHAR(20) NOT NULL,
    grupo VARCHAR(255) NOT NULL
);

CREATE TABLE tb_reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    laboratorio INT REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE,
    curso INT REFERENCES tb_curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
    qtd_alunos INT NOT NULL,
    turma VARCHAR(10) NOT NULL,
    professor VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    obs VARCHAR(255)
);

CREATE TABLE tb_solicitacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso INT REFERENCES tb_curso(id) ON DELETE CASCADE ON UPDATE CASCADE,
    qtd_alunos INT NOT NULL,
    turma VARCHAR(10) NOT NULL,
    professor VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    obs VARCHAR(255)
);

CREATE TABLE tb_modulo (
    id INT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE tb_fornecedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE tb_representante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(255),
    email VARCHAR(255),
    fornecedor INT REFERENCES tb_fornecedor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tb_licenca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aquisicao VARCHAR(255) NOT NULL,
    vencimento VARCHAR(255) NOT NULL,
    status INT NOT NULL,
    software INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    fornecedor INT REFERENCES tb_fornecedor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tb_licenca_codigo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_tipo VARCHAR(255) NOT NULL,
    codigo VARCHAR(255) NOT NULL,
    licenca INT REFERENCES tb_licenca(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_res (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    res INT REFERENCES tb_reserva(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    res INT REFERENCES tb_solicitacao(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_modulo_res (
    id INT AUTO_INCREMENT PRIMARY KEY,
    res INT REFERENCES tb_reserva(id) ON DELETE CASCADE ON UPDATE CASCADE,
    modulo INT REFERENCES tb_modulo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_modulo_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
    res INT REFERENCES tb_solicitacao(id) ON DELETE CASCADE ON UPDATE CASCADE,
    modulo INT REFERENCES tb_modulo(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE aux_sw_lab (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES tb_software(id) ON DELETE CASCADE ON UPDATE CASCADE,
    lab INT REFERENCES tb_laboratorio(id) ON DELETE CASCADE ON UPDATE CASCADE
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
