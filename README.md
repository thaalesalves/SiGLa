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

## Por que eu desenvolvi o SiGLa?
O SiGLa é, acima de tudo, meu projeto de TCC. O projeto foi iniciado em 2016, quando eu estava no quinto semestre da faculdade. Na época eu trabalhava na Universidade, e era responsável pelos laboratórios de informática, e todas as operações eram (ainda são) feitas manualmente. Reservas eram feitas por email, e o mapeamento de laboratórios era feito por planilhas de Excel. Isso tornava o trabalho bem difícil, pois cada detalhe, cada informação do ambiente, estava em um lugar diferente. Levantar relatórios, verificar reservas e afins era bem dolorido de fazer. A proposta do SiGLa é unificar esses pontos, centraliza-los. Com um sistema integrado para gerenciar tarefas, reservas, usuários, utilização, licenças de softwares, quantidade de computadores e tudo mais ficaria bem fácil, então pensei em unir o útil ao... útil. Um projeto de TCC que facilitasse meu trabalho, e de quebra daria acesso fácil aos professores, que muitas vezes não sabiam onde dariam aula; com a interface de professor, eles podem checar as próprias reservas, e também teriam um canal direto com a equipe do laboratório. Poderiam fazer solicitações por lá, e como o sistema tem validações de atributos do laboratórios, a chance de erros humanos - como reservar um laboratório já utilizado ou colocar uma turma num laboratório que não a comporta - cairia drasticamente. O sistema foi modelado com base nas regras de negócio do laboratório da UMC, mas estou procurando parametriza-lo cada vez mais para que possa ser utilizado por qualquer instituição.

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
O SiGLa permite que fornecedores de licenças sejam cadastrados, e dá espaço para representantes destes fornecedores também serem cadastrados (contato direto com um funcionário específico, por exemplo). As licenças de software cadastradas são atreladas a esses fornecedores, e podem ter ou não códigos de ativação (que são cadastrados junto com a licença). Foi utilizado o Quartz para enviar emails para a equipe do laboratório quando a licença do software estiver perto da data de vencimento, o que facilita a renovação delas.

## Especificações Técnicas
### Descrição
A versão de testes do SiGLa está atualmente sendo hospedada na AWS, com uma instância do EC2 com Debian 9 provisionada para ela. Por ora, arquivos estão sendo guardados na própria aplicação, e o servidor de banco de dados também está rodando no mesmo servidor, mas é possível utilizar serviços como CloudFormation, S3 e RDS para "quebrar" o serviço em outras partes. O DNS utilizado é o CloudFlare, que aponta para as instâncias da AWS onde as aplicações estão rodando. A controladora de domínio é uma outra instância do EC2, rodando Windows Server 2016. Os emails são disparados pelo SMTP da ZohoMail, sendo que até o meio de abril de 2018 usei o Migadu como provedor. Em 2017 usei outros serviços, como MailGun, MailJet e MailChimp, além de ter usado um SMTP próprio também. É possível rodar o SiGLa em serviços como Heroku e Bluemix, que também foram utilizados para colocar a versão live em funcionamento. Em 2017 usei muito os serviços do Azure, tendo uma instância com Debian 9 para rodar o SiGLa, e o Azure Active Directory para gerenciar usuários e grupos na nuvem - o que elimnava a necessidade de um servidor Windows.

### Testes unitários
Os testes estão no pacote de testes. No início, a proposta do projeto era não usar frameworks para o aprendizado da linguagem base, mas acabei aderindo ao jUnit em tempos recentes. Portanto, a maior parte dos testes foi feita "na unha", apesar de alguns terem sido feitos usando jUnit.

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
- Microsoft Windows Server 2016
- Amazon Web Servics
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
- Microsoft Azure
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
- Facade
- MVC