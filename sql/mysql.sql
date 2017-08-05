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
    laboratorio INT REFERENCES laboratorio(id),
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
    laboratorio INT REFERENCES laboratorio(id),
    curso INT REFERENCES curso(id),
    qtd_alunos INT NOT NULL,
    turma VARCHAR(10) NOT NULL,
    professor VARCHAR(20) NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    obs VARCHAR(255)
);

CREATE TABLE tb_solicitacao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    curso INT REFERENCES curso(id),
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
    sw INT REFERENCES software(id),
    res INT REFERENCES reserva(id)
);

CREATE TABLE aux_sw_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sw INT REFERENCES software(id),
    res INT REFERENCES solicitacao(id)
);

CREATE TABLE aux_modulo_res (
    id INT AUTO_INCREMENT PRIMARY KEY,
    res INT REFERENCES reserva(id),
    modulo INT REFERENCES modulo(id)
);

CREATE TABLE aux_modulo_soli (
    id INT AUTO_INCREMENT PRIMARY KEY,
    res INT REFERENCES solicitacao(id),
    modulo INT REFERENCES modulo(id)
);

-- Inserção de Valores
INSERT INTO modulo VALUES(1);
INSERT INTO modulo VALUES(2);
INSERT INTO modulo VALUES(3);
INSERT INTO modulo VALUES(4);
INSERT INTO modulo VALUES(5);
INSERT INTO modulo VALUES(6);
INSERT INTO modulo VALUES(7);
INSERT INTO modulo VALUES(8);
INSERT INTO curso VALUES(DEFAULT, 'Engenharia Civil', 'Bacharel');
INSERT INTO curso VALUES(DEFAULT, 'Recursos Humanos', 'Bacharel');
INSERT INTO curso VALUES(DEFAULT, 'Jornalismo', 'Bacharel');
INSERT INTO curso VALUES(DEFAULT, 'Arquitetura', 'Bacharel');
INSERT INTO grupo VALUES (DEFAULT, 'admin', 'memberOf=CN=sigla_admin,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (DEFAULT, 'funcionario', 'memberOf=CN=sigla_func,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (DEFAULT, 'estagiario', 'memberOf=CN=sigla_est,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (DEFAULT, 'coordenador', 'memberOf=CN=sigla_coord,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO grupo VALUES (DEFAULT, 'professor','memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es');
INSERT INTO software VALUES(DEFAULT, 'NetBeans', 'Oracle');
INSERT INTO software VALUES(DEFAULT, 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES(DEFAULT, 'AppInventor', 'MIT');
INSERT INTO software VALUES(DEFAULT, 'PostgreSQL', 'EnterpriseDB');
INSERT INTO software VALUES(DEFAULT, 'MatLab', 'Mathworks');
INSERT INTO software VALUES(DEFAULT, 'LabVIEW', 'National Instruments');
INSERT INTO laboratorio VALUES(DEFAULT, '12-10', 25, 50);
INSERT INTO laboratorio VALUES(DEFAULT, '12-13', 25, 50);
INSERT INTO laboratorio VALUES(DEFAULT, '12-14', 25, 50);
INSERT INTO laboratorio VALUES(DEFAULT, '12-17', 25, 50);
