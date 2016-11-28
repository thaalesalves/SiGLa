# SiGLa
## Sistema de Gestão de Laboratórios

O SiGLa é um sistema voltado para a gestão dos laboratórios de informática de instituições acadêmicas como colégios, faculdades e universidades, auxiliando no controle de tarefas diárias e gerando relatórios de estado dos laboratórios e seus equipamentos. O sistema é voltado também para o controle de reservas, permitindo que o corpo docente da instituição tenha acesso ao estado dos laboratórios e que ele possa solicitar reservas para utilização dos laboratórios no período de aulas.

### Integração com o Active Directory
Ao invés de um controle de usuários próprio, o SiGLa é integrado com o AD DC da Instituição, vinculando todos os usuários do domínio ao sistema, que, de acordo com grupos de segurança que o usuário faz parte, dá direitos de acesso a ele (validando entre professor, coordenador e funcionário da Instituição), invalidando o login dos usuários que não são parte dos grupos definidos.  

### Reservas de Laboratório
O SiGLa permite que o corpo docente da instituição tenha acesso à interface de usuário e solicite o uso de laboratórios, informando os dados necessários com algum tempo de antecedência. Os professores com laboratórios reservados poderão monitorar o estado do equipamento contido no laboratório de uso dele, e também poderão controlar o uso e fazer solicitações adicionais. Os coordenadores poderão manipular as reservas dos professores que eles coordenam, invertendo reservas entre professores, e também poderão contatar outros coordenadores para rever reservas entre diferentes cursos.

### Controle de Tarefas
O SiGLa também permitirá que o departamento de TI da instituição monitore as tarefas diárias do laboratório, tendo controle sobre quais computadores foram retirados e porquê, estado da infraestrutura, pedidos de manutenção, instalação de softwares e afins. 

