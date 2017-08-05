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
INSERT INTO tb_curso VALUES(DEFAULT, 'Engenharia Civil', 'Bacharel');
INSERT INTO tb_curso VALUES(DEFAULT, 'Recursos Humanos', 'Bacharel');
INSERT INTO tb_curso VALUES(DEFAULT, 'Jornalismo', 'Bacharel');
INSERT INTO tb_curso VALUES(DEFAULT, 'Arquitetura', 'Bacharel');
INSERT INTO tb_grupo VALUES (DEFAULT, 'admin', 'memberOf=CN=sigla_admin,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO tb_grupo VALUES (DEFAULT, 'funcionario', 'memberOf=CN=sigla_func,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO tb_grupo VALUES (DEFAULT, 'estagiario', 'memberOf=CN=sigla_est,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO tb_grupo VALUES (DEFAULT, 'coordenador', 'memberOf=CN=sigla_coord,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO tb_grupo VALUES (DEFAULT, 'professor','memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO tb_software VALUES(DEFAULT, 'NetBeans', 'Oracle');
INSERT INTO tb_software VALUES(DEFAULT, 'Visual Studio', 'Microsoft');
INSERT INTO tb_software VALUES(DEFAULT, 'AppInventor', 'MIT');
INSERT INTO tb_software VALUES(DEFAULT, 'PostgreSQL', 'EnterpriseDB');
INSERT INTO tb_software VALUES(DEFAULT, 'MatLab', 'Mathworks');
INSERT INTO tb_software VALUES(DEFAULT, 'LabVIEW', 'National Instruments');
INSERT INTO tb_laboratorio VALUES(DEFAULT, '12-10', 25, 50);
INSERT INTO tb_laboratorio VALUES(DEFAULT, '12-13', 25, 50);
INSERT INTO tb_laboratorio VALUES(DEFAULT, '12-14', 25, 50);
INSERT INTO tb_laboratorio VALUES(DEFAULT, '12-17', 25, 50);
