/* Inserção de Softwares */
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Visual Studio', 'Microsoft');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'NetBeans', 'Oracle');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Office', 'Microsoft');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'PostgreSQL', 'EnterpriseDB');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'MySQL', 'Oracle');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Creative Cloud', 'Adobe');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Chrome', 'Google');
INSERT INTO software VALUES(NEXTVAL('seq_software'), 'Project', 'Microsoft');

/* Inserção de Cursos */
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Sistemas de Informação', 'Bacharel');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Gestão de Projetos', 'MBA');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Análise e Desenvolvimento de Sistemas', 'Tecnólogo');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Engenharia Biomédica', 'Doutorado');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Biotecnologia', 'Mestrado');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Educação Física', 'Licenciatura');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Mecatrônica', 'Técnico');
INSERT INTO curso VALUES(NEXTVAL('seq_curso'), 'Gestão Ambiental', 'Pronatec');

/* Inserção de Turmas */
INSERT INTO turma VALUES(NEXTVAL('seq_turma'), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 7, 'A', 32, '2016-2');
INSERT INTO turma VALUES(NEXTVAL('seq_turma'), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 8, 'B', 32, '2016-2');
INSERT INTO turma VALUES(NEXTVAL('seq_turma'), (SELECT id FROM curso WHERE nome = 'Gestão de Projetos' AND modalidade = 'MBA'), 1, 'B', 22, '2016-2');
INSERT INTO turma VALUES(NEXTVAL('seq_turma'), (SELECT id FROM curso WHERE nome = 'Engenharia Biomédica' AND modalidade = 'Doutorado'), 1, 'A', 10, '2016-2');

/* Inserção de Laboratórios */
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-14', 25, 50);
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-17', 25, 50);
INSERT INTO laboratorio VALUES(NEXTVAL('seq_lab'), '12-20', 25, 50);

/* Inserção de Equipamentos */
INSERT INTO equipamento VALUES('1214LAB001', (SELECT id FROM laboratorio WHERE numero = '12-14'), '10.8.114.1', 'AABBCCDDEEFF', 'Lenovo 62', 1);
INSERT INTO equipamento VALUES('1214LAB002', (SELECT id FROM laboratorio WHERE numero = '12-14'), '10.8.114.2', 'AABBCCDDEEFF', 'Lenovo 62', 1);
INSERT INTO equipamento VALUES('1217LAB001', (SELECT id FROM laboratorio WHERE numero = '12-17'), '10.8.117.1', 'AABBCCDDEEFF', 'Lenovo 63', 1);
INSERT INTO equipamento VALUES('1217LAB002', (SELECT id FROM laboratorio WHERE numero = '12-17'), '10.8.114.2', 'AABBCCDDEEFF', 'Lenovo 63', 1);

/* Inserção de Reservas */
INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-17'), (SELECT id FROM software WHERE nome = 'Visual Studio' AND fabricante = 'Microsoft'), (SELECT turma.id FROM turma, curso WHERE turma.semestre = 7 AND turma.turma = 'A' AND curso.nome = 'Sistemas de Informação' AND curso.modalidade = 'Bacharel' AND turma.curso = curso.id), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 'daniellemartin', 1, NULL, NULL, NULL, NULL);
INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-17'), (SELECT id FROM software WHERE nome = 'NetBeans' AND fabricante = 'Oracle'), (SELECT turma.id FROM turma, curso WHERE turma.semestre = 7 AND turma.turma = 'A' AND curso.nome = 'Sistemas de Informação' AND curso.modalidade = 'Bacharel' AND turma.curso = curso.id), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 'daniellemartin', 1, NULL, NULL, NULL, NULL);
INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-14'), (SELECT id FROM software WHERE nome = 'Visual Studio' AND fabricante = 'Microsoft'), (SELECT turma.id FROM turma, curso WHERE turma.semestre = 8 AND turma.turma = 'B' AND curso.nome = 'Sistemas de Informação' AND curso.modalidade = 'Bacharel' AND turma.curso = curso.id), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 'erikam', 1, NULL, NULL, NULL, NULL);
INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-14'), (SELECT id FROM software WHERE nome = 'Visual Studio' AND fabricante = 'Microsoft'), (SELECT turma.id FROM turma, curso WHERE turma.semestre = 8 AND turma.turma = 'B' AND curso.nome = 'Sistemas de Informação' AND curso.modalidade = 'Bacharel' AND turma.curso = curso.id), (SELECT id FROM curso WHERE nome = 'Sistemas de Informação' AND modalidade = 'Bacharel'), 'erikam', 1, NULL, NULL, NULL, NULL);
INSERT INTO reserva VALUES(NEXTVAL('seq_reserva'), (SELECT id FROM laboratorio WHERE numero = '12-20'), (SELECT id FROM software WHERE nome = 'Project' AND fabricante = 'Microsoft'), (SELECT turma.id FROM turma, curso WHERE turma.semestre = 1 AND turma.turma = 'B' AND curso.nome = 'Gestão de Projetos' AND curso.modalidade = 'MBA' AND turma.curso = curso.id), (SELECT id FROM curso WHERE nome = 'Gestão de Projetos' AND modalidade = 'MBA'), 'daisyeb', 1, NULL, NULL, NULL, NULL);


/* Testes de Seleção */
SELECT reserva.id, curso.modalidade, curso.nome AS curso, laboratorio.numero AS laboratorio, software.fabricante, software.nome AS software, reserva.professor, turma.semestre, turma.turma, reserva.tipo FROM curso, reserva, laboratorio, software, turma WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso AND reserva.professor = 'daniellemartin';
SELECT reserva.id, curso.modalidade, curso.nome AS curso, laboratorio.numero AS laboratorio, software.fabricante, software.nome AS software, reserva.professor, turma.semestre, turma.turma, reserva.tipo FROM curso, reserva, laboratorio, software, turma WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso AND reserva.professor = 'erikam';
SELECT reserva.id, curso.modalidade, curso.nome AS curso, laboratorio.numero AS laboratorio, software.fabricante, software.nome AS software, reserva.professor, turma.semestre, turma.turma, reserva.tipo FROM curso, reserva, laboratorio, software, turma WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso AND reserva.professor = 'daisyeb';