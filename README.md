[![License: LGPL v3](https://img.shields.io/badge/License-LGPL%20v3-blue.svg)](https://www.gnu.org/licenses/lgpl-3.0)

# SiGLa

O SiGLa é um sistema voltado para instituições de ensino que utilizam laboratórios de informática, facilitar a tarefa de gestão de laboratórios.
- Controle tarefas diárias da equipe do laboratório;
- Gerencie as reservas de laboratório;
- Gerencie os softwares que a Instituição possui;
- Monitore e controle o estado do equipamento dos laboratórios de informática;
- Tenha pleno controle sobre as oprações do laboratório.

## Live demo
O SiGLa está disponível para live demo para aqueles que gostariam de testá-lo e vê-lo em ação. Existem níveis de acesso com funções de diferentes, e a senha será sempre o mesmo que o nome de usuário.
### Como acessar
Basta clicar [aqui](https://sigla.thalesalv.es), e a página de login aparecerá. Os usuários disponíveis são **admin**, **funcionario**, **estagiario**, **coordenador** e **professor**. As senhas são o mesmo que os usuários (i.e., a senha do usuário **professor** é **professor**).
## Features
### Integração com o Active Directory
O SiGLa possui integração com Active Directory, unificando o login de usuários com aqueles existentes no Active Directory Domain Controller. O controle de acesso é feito a partir de grupos de distribuição dados aos usuários no AD, que atribuem permissões específicas no sistema aos usuários.

### Disparo de Emails
O SiGLa dispara dois emails na solicitação de reserva, sendo um para o docente solicitante e outro para a equipe de gestão do laboratório. A análise da reserva é feita pela equipe do laboratório, e fica sujeita a aprovação, rejeição ou arquivamento. 

### Controle de Reservas
O SiGLa permite controlar reservas do laboratório, desde o momento da solicitação até o término da reserva. Uma reserva solicitada fica pendente até que um membro da equipe avalie a possibilidade de alocação e dê o retorno ao docente solicitante.

### Controle de Estado de Equipamento
O SiGLa possui um controle de estado de equipamento, que depende do cadastro dos computadores e softwares dos laboratórios, que é integrado com as tarefas. Caso algum equipamento esteja danificado, uma tarefa de retirada do equipamento será criada, e o estado do laboratório será atualizado com o equipamento retirado, alterando o mapa do laboratório. O corpo docente terá acesso a esta informação.

### Controle de Reservas de Software
O SiGLa permite o cadastro de licenças atreladas aos softwares que o sistema já possui. São cadastrados data de aquisição, de vencimento, códigos de licença e fornecedor da licença, e existem validações que evitam que o sistema exiba na tela de reservas softwares que estejam com suas reservas vencidas. O SiGLa possui um módulo que avisa a equipe do laboratório que uma licença específica está para vencer um mês antes da data registrada, o que facilita o controle de reservas ativas e inativas.

## Especificações Técnicas
### Tecnologias Utilizadas
##### Banco de Dados
- PostgreSQL
- MySQL

##### Linguagens
- Java EE
- JavaScript
- CSS3
- HTML5
- LDAP

##### Infraestrutura
- Debian 9
- Microsoft Windows Server 2016
- Amazon Web Services
- Microsoft Active Directory Domain Services (AD DS)
- JavaMail
- Postfix
- Maven
- LDAP
- ZohoMail

##### Bibliotecas & Frameworks
- jQuery
- Google Gson
- PNotify
- Project Lombok
- Bootstrap
- jUnit
- DateJS
- Quartz

##### Tecnologias compatíveis utilizadas no passado
- Windows Server 2012
- Microsoft Azure
- Azure Active Directory
- MailGun
- MailChimp
- MailJet
- Heroku
- IBM Bluemix
- Migadu

### Padrões de Desenvolvimento
- Abstract Factory
- Orientação a Objetos
- DAO
- Factory
- ICommand
- MVC