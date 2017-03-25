<!--
Copyright (C) 2016 Thales Alves Pereira

  This file is part of SiGla.

   SiGla is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   SiGla is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
-->

<%@page import="model.Solicitacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Solicitações de Reserva | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <link href="${pageContext.request.contextPath}/css/estilo.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body class="hold-transition skin-black-light sidebar-mini">
        <%
            Calendar cal = Calendar.getInstance();
            Pessoa p;

            if ((p = (Pessoa) session.getAttribute("pessoa")) == null) {
                response.sendRedirect(request.getContextPath() + "/error/401");
            }

            ArrayList<Solicitacao> ares;
            if ((ares = (ArrayList<Solicitacao>) session.getAttribute("dados-solicitacoes")) == null) {
                response.sendRedirect(request.getContextPath() + "/AlmightyController?acao=SolicitacaoListagem");
            }
        %>
        <div class="wrapper">
            <header class="main-header">
                <!-- Logo -->
                <a href="${pageContext.request.contextPath}" class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><b>S</b>i<b>GL</b>a</span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><b>S</b>i<b>GL</b>a</span>
                </a>
                <!-- Header Navbar: style can be found in header.less -->
                <nav class="navbar navbar-static-top">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                        <span class="sr-only">Toggle navigation</span>
                    </a>

                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- Notifications: style can be found in dropdown.less -->
                            <li class="dropdown notifications-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-bell-o"></i>
                                    <span class="label label-warning">10</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header">Você tem 10 notificações </li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul class="menu">
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                                                    page and may cause design problems
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#">
                                                    <i class="fa fa-users text-red"></i> 5 new members joined
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li class="footer"><a href="#">Ver tudo</a></li>
                                </ul>
                            </li>
                            <!-- Tasks: style can be found in dropdown.less -->
                            <li class="dropdown tasks-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-flag-o"></i>
                                    <span id="qtd-res" class="label label-danger"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="header" id="msg-res"></li>
                                    <li>
                                        <!-- inner menu: contains the actual data -->
                                        <ul id="res-notif" class="menu"></ul>
                                    </li>
                                    <li class="footer">
                                        <a href="#">Ver tudo</a>
                                    </li>
                                </ul>
                            </li>
                            <!-- User Account: style can be found in dropdown.less -->
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <%
                                        if (p.getPicture() != null) {
                                            out.println("<img src='" + request.getContextPath() + "/img/users/" + p.getUsername() + "_pic.jpg' class='user-image' alt='User Image'>");
                                        } else {
                                            out.println("<img src='" + request.getContextPath() + "/img/users/thumbnail.png' class='user-image' alt='User Image'>");
                                        }
                                    %>
                                    <span class="hidden-xs"><% out.println(p.getNome());%></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <%
                                            if (p.getPicture() != null) {
                                                out.println("<img src='" + request.getContextPath() + "/img/users/" + p.getUsername() + "_pic.jpg' class='img-circle' alt='User Image'>");
                                            } else {
                                                out.println("<img src='" + request.getContextPath() + "/img/users/thumbnail.png' class='img-circle' alt='User Image'>");
                                            }
                                        %>
                                        <p>
                                            <% out.println(p.getShownName()); %>
                                            <small><% out.println(p.getCargo() + " | " + p.getDepto()); %></small>
                                        </p>
                                    </li>
                                    <!-- Menu Body -->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                            <a href="../pagina/perfil" class="btn btn-default btn-flat">Perfil</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="../pagina/logout" class="btn btn-default btn-flat">Logout</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <!-- Control Sidebar Toggle Button -->
                        </ul>
                    </div>
                </nav>
            </header>
            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">
                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">
                    <!-- Sidebar user panel -->
                    <div class="user-panel">
                        <div class="pull-left image">
                            <%
                                if (p.getPicture() != null) {
                                    out.println("<img src='" + request.getContextPath() + "/img/users/" + p.getUsername() + "_pic.jpg' class='img-circle' alt='User Image'>");
                                } else {
                                    out.println("<img src='" + request.getContextPath() + "/img/users/thumbnail.png' class='img-circle' alt='User Image'>");
                                }
                            %>
                        </div>
                        <div class="pull-left info">
                            <p><% out.println(p.getShownName());%></p>
                            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                        </div>
                    </div>
                    <!-- sidebar menu: : style can be found in sidebar.less -->
                    <ul class="sidebar-menu">
                        <li class="header">PRINCIPAL</li>
                        <li class="active treeview">
                            <a href="../pagina/home">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="header">RESERVAS</li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-edit"></i> <span>Solicitação</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="../reserva/listar-semestral"><i class="fa fa-circle-o"></i> Reserva Semestral</a></li>
                                <li><a href="../reserva/listar-pontual"><i class="fa fa-circle-o"></i> Reserva Pontual</a></li>
                            </ul>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-calendar"></i> <span>Listagem</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="../reserva/listar-hoje"><i class="fa fa-circle-o"></i> Reservas do Dia</a></li>
                                <li><a href="../reserva/listar"><i class="fa fa-circle-o"></i> Todas as Reservas</a></li>
                            </ul>
                        </li>
                        <li class="header">CURSOS</li>
                        <li class="treeview">
                            <a href="../curso/novo">
                                <i class="fa fa-edit"></i> <span>Inserção</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="../curso/listar">
                                <i class="fa fa-files-o"></i> <span>Listagem</span>
                            </a>
                        </li>
                    </ul>
                </section>
            </aside>
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Solicitações de Reserva
                        <small>lista geral</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Reservas</a></li>
                        <li class="active">Reservas Gerais</li>
                    </ol>
                </section>

                <section class="content">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Lista geral</h3>
                        </div>
                        <div class="box-body">
                            <table id="example1" class="table table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Turma</th>
                                        <th>Curso</th>
                                        <th>Software</th>
                                        <th>Professor</th>
                                        <th>Módulo</th>
                                        <th>Observação</th>
                                        <th style="width: 5%;">Opções</th>
                                    </tr>
                                </thead>
                                <%
                                    for (Solicitacao r : ares) {
                                %>
                                <tbody>
                                    <tr class="gradeC">
                                        <td class="center"><% out.println(r.getTurma()); %></td>
                                        <td class="center"><% out.println(r.getCurso().getModalidade() + " em " + r.getCurso().getNome()); %></td>
                                        <td class="center"><% out.println(r.getSoftware().getFabricante() + " " + r.getSoftware().getNome()); %></td>
                                        <td class="center"><% out.println(r.getPessoa().getShownName()); %></td>
                                        <td class="center"><% out.println(r.getModulo()); %></td>
                                        <td class="center"><% out.println(r.getObservacao()); %></td>
                                        <td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="showSolicitacaoModal(<% out.println(ares.indexOf(r)); %>)"></button></center></td>
                                </tr>                                
                                </tbody>
                                <% } %>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
            <footer class="main-footer">
                <strong>Copyright &copy; <% out.println(cal.get(Calendar.YEAR));%> <a href="http://www.umc.br">Universidade de Mogi das Cruzes</a>.</strong>
            </footer>
            <div class="control-sidebar-bg"></div>
        </div>

        <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h5 class="modal-title" id="exampleModalLabel">Solicitação</h5>                                                        
                        </div>                    
                        <div class="modal-body">
                            <div class='form-group'>
                                <label>Professor</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalProfessor" id="modalProfessor" placeholder="Nome do Professor" />
                            </div>
                            <div class='form-group'>
                                <label>Turma</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalCurso" id="modalCurso" placeholder="Nome do Curso" />
                            </div>
                            <div class='form-group'>
                                <label>Software</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalSoftware" id="modalSoftware" placeholder="Nome do Software" />
                            </div>
                            <div class='form-group'>
                                <label>Módulo</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalModulo" id="modalModulo" placeholder="Módulo" />
                            </div>
                            <div class='form-group'>
                                <label>Dia da Semana</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalDiaSemana" id="modalDiaSemana" placeholder="Dia da Semana" />
                            </div>
                            <div class='form-group'>
                                <label>Qtd. de Alunos</label>
                                <input style="width: 80%;" disabled type='text' class='form-control pull-right' nome="modalQtdAlunos" id="modalQtdAlunos" placeholder="Quantidade de Alunos" />
                            </div>
                            <div class='form-group'>
                                    <label>Laboratório</label>
                                    <select style="width: 80%;" class='form-control pull-right' name="modalLabCombo" id="modalLabCombo">
                                        <option default>Selecionar</option>
                                    </select>
                                </div>
                        </div>
                        <div id="modal-footer" class="modal-footer">
                            <button data-toggle="modal" data-target="#labModal" type="button" class="btn btn-success">Aprovar</button>
                            <button id="btnModalReprovar" type="button" class="btn btn-danger" onclick="modalRemover()">Reprovar</button><br/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="labModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h5 class="modal-title" id="exampleModalLabel">Selecionar Laboratório</h5>                                                        
                        </div>                    
                        <div class="modal-body">
                            <div class='form-group'>
                                <label>Laboratório</label>
                                <select style="width: 80%;" class='form-control pull-right' name="modalLabCombo" id="modalLabCombo">
                                    <option default>Selecionar</option>
                                </select>
                            </div>
                        </div>
                        <div id="modal-footer" class="modal-footer">
                            <button value="SolicitacaoAprovacao" type="submit" name="acao" class="btn btn-success">Aprovar</button>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
        <script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
        <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
        <script>
                                $(function () {
                                    $("#example1").DataTable();
                                    $('#example2').DataTable({
                                        "paging": true,
                                        "lengthChange": false,
                                        "searching": false,
                                        "ordering": true,
                                        "info": true,
                                        "autoWidth": false
                                    });
                                });
        </script>
    </body>
</html>
