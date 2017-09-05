# SiGLa

O SiGLa é um sistema voltado para instituições de ensino que utilizam laboratórios de informática, facilitar a tarefa de gestão de laboratórios.
- Controle tarefas diárias da equipe do laboratório;
- Gerencie as reservas de laboratório;
- Gerencie os softwares que a Instituição possui;
- Monitore e controle o estado do equipamento dos laboratórios de informática;
- Tenha pleno controle sobre as oprações do laboratório.

## Features
--------
### Integração com o Active Directory
O SiGLa possui integração com Active Directory, unificando o login de usuários com aqueles existentes no Active Directory Domain Controller. O controle de acesso é feito a partir de grupos de distribuição dados aos usuários no AD, que atribuem permissões específicas no sistema aos usuários.

### Disparo de Emails
O SiGLa dispara dois emails na solicitação de reserva, sendo um para o docente solicitante e outro para a equipe de gestão do laboratório. A análise da reserva é feita pela equipe do laboratório, e fica sujeita a aprovação, rejeição ou arquivamento. 

### Controle de Reservas
O SiGLa permite controlar reservas do laboratório, desde o momento da solicitação até o término da reserva. Uma reserva solicitada fica pendente até que um membro da equipe avalie a possibilidade de alocação e dê o retorno ao docente solicitante.

### Controle de Tarefas
O SiGLa possui um módulo de controle de tarefas, que permite delegar tarefas aos membros da equipe e acompanhar o desenrolar delas até que elas sejam concluídas, sendo necessário dar detalhes das etapas concluídas.

### Controle de Estado de Equipamento
O SiGLa possui um controle de estado de equipamento, que depende do cadastro dos computadores e softwares dos laboratórios, que é integrado com as tarefas. Caso algum equipamento esteja danificado, uma tarefa de retirada do equipamento será criada, e o estado do laboratório será atualizado com o equipamento retirado, alterando o mapa do laboratório. O corpo docente terá acesso a esta informação.

## Especificações Técnicas
--------
### Tecnologias Utilizadas
##### Banco de Dados
- PostgreSQL
- MySQL

##### Programação
- Java EE
- JavaScript
- CSS3
- HTML5

##### Infraestrutura
- Microsoft Windows Server 2016
- Microsoft Azure
- Microsoft Active Directory Domain Services (AD DS)
- JavaMail
- Postfix
- Maven
- LDAP
- MailGun / MailJet

##### Bibliotecas & Frameworks
- jQuery
- Google Gson
- PNotify
- Project Lombok
- Bootstrap

### Padrões de Desenvolvimento
- Orientação a Objetos
- DAO
- Factory
- ICommand
- Facade
- MVC