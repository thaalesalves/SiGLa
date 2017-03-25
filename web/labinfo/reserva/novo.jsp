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

<%@page import="model.Reserva"%>
<%@page import="model.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Pessoa"%>
<%@page import="model.Software"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Calendar cal = Calendar.getInstance();
    Pessoa p;
    Reserva r;
    ArrayList<Software> asw;
    ArrayList<Curso> ac;

    if ((p = (Pessoa) session.getAttribute("pessoa")) == null) {
        response.sendRedirect(request.getContextPath() + "/error/401");
    }

    if ((r = (Reserva) session.getAttribute("dados-semestral")) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/AlmightyController?acao=ListarReservaSemestral").forward(request, response);
    }

    String msg = "nada";
    if ((String) session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

    asw = r.getSoftwares();
    ac = r.getCursos();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nova Reserva | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/flat/blue.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/all.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/all.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/timepicker/bootstrap-timepicker.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            var msg = "<%=msg%>";

            $(document).ready(function () {
                if (msg != "nada") {
                    if (msg == "Solicita&ccedil;&atilde;o efetuada com sucesso.") {
                        $("#msg").addClass("alert-success");
                        $("#msg").text(msg);
                        $("#msg").toggle();
                    } else if (msg == "Erro ao efetivar a solicita&ccedil;&atilde;o.") {
                        $("#msg").addClass(".alert-danger");
                        $("#msg").text(msg);
                        $("#msg").toggle();
                    }
                }
            });
        </script>
    </head>
    <body class="hold-transition skin-black-light sidebar-mini">        
        <div class="alert" style="display: none;"></div>
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
                                        <a href="${pageContext.request.contextPath}/controle/listar-solicitacoes">Ver tudo</a>
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
                            <a href="${pageContext.request.contextPath}">
                                <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="header">RESERVAS</li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/controle/listar-solicitacoes">
                                <i class="fa fa-edit"></i> <span>Solicitações</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="#">
                                <i class="fa fa-calendar"></i> <span>Listagem</span>
                                <span class="pull-right-container">
                                    <i class="fa fa-angle-left pull-right"></i>
                                </span>
                            </a>
                            <ul class="treeview-menu">
                                <li><a href="${pageContext.request.contextPath}/controle/listar-reservas-hoje"><i class="fa fa-circle-o"></i> Reservas do Dia</a></li>
                                <li><a href="${pageContext.request.contextPath}/controle/listar-reservas"><i class="fa fa-circle-o"></i> Todas as Reservas</a></li>
                            </ul>
                        </li>
                        <li class="header">CURSOS</li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/curso/novo">
                                <i class="fa fa-edit"></i> <span>Inserção</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/controle/listar-curso">
                                <i class="fa fa-files-o"></i> <span>Listagem</span>
                            </a>
                        </li>
                    </ul>
                </section>
                <!-- /.sidebar -->
            </aside>
            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Reservas
                        <small>nova reserva</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li>Reservas</li>
                        <li class="active">Nova reserva</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Reserva semestral</h3>
                        </div>
                        <div class="box-body">
                            <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                                <div class='form-group'>
                                    <label>Nome</label>
                                    <input disabled type='text' class='form-control pull-right' name='nome' value="<% out.println(p.getNomeCompleto()); %>" placeholder="<% out.println(p.getNomeCompleto()); %>" />
                                </div>
                                <div class='form-group'>
                                    <label>Email</label>
                                    <input disabled type='text' class='form-control pull-right' name='email' value="<% out.println(p.getEmail()); %>" placeholder="<% out.println(p.getEmail()); %>" />
                                </div>
                                <div class='form-group'>
                                    <label>Turma</label>
                                    <input id="turma" required type='text' class='form-control pull-right' name='turma' placeholder="1ºA" />
                                </div>
                                <div class='form-group'>
                                    <label>Curso</label>
                                    <select name="curso" class="select2 form-control" data-placeholder="Selecione um curso" style="width: 100%;" required>
                                        <option selected disabled>Curso</option>
                                        <% for (Curso c : ac) { %>
                                        <option value="<% out.println(ac.get(ac.indexOf(c)).getId()); %>"><% out.println(ac.get(ac.indexOf(c)).getModalidade() + " em " + ac.get(ac.indexOf(c)).getNome()); %></option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class='form-group'>
                                    <label>Qtd. de Alunos</label>
                                    <input name="qtd" required type='number' class='form-control pull-right' name='qtd-alunos' placeholder="50" />
                                </div>
                                <div class="form-group">
                                    <label>Módulo</label>
                                    <select name="modulo" class="select2 form-control" data-placeholder="Módulo" style="width: 100%;" required multiple>
                                        <option value="1º módulo">1º Módulo (8h às 9h30)</option>
                                        <option value="2º módulo">2º Módulo (9h40 às 11h10)</option>
                                        <option value="3º módulo">3º Módulo (11h10 às 12h40)</option>
                                        <option value="4º módulo">4º Módulo (13h às 14h30)</option>
                                        <option value="5º módulo">5º Módulo (14h30 às 17h30)</option>
                                        <option value="6º módulo">6º Módulo (17h30 às 19h)</option>
                                        <option value="7º módulo">7º Módulo (19h às 20h30)</option>
                                        <option value="8º módulo">8º Módulo (20h40 às 22h)</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Dia da Semana</label>
                                    <select name="dia-semana" class="select2 form-control" data-placeholder="Selecione o dia" style="width: 100%;" required>
                                        <option>Segunda-feira</option>
                                        <option>Terça-feira</option>
                                        <option>Quarta-feira</option>
                                        <option>Quinta-feira</option>
                                        <option>Sexta-feira</option>
                                        <option>Sábado</option>
                                    </select>
                                </div>
                                <div class='form-group'>
                                    <label>Softwares</label>
                                    <select name="softwares" class="select2 form-control" data-placeholder="Selecione um software" style="width: 100%;" multiple required>
                                        <% for (Software sw : asw) { %>
                                        <option id="softwares" value="<% out.println(asw.get(asw.indexOf(sw)).getId()); %>"><% out.println(asw.get(asw.indexOf(sw)).getFabricante() + " " + asw.get(asw.indexOf(sw)).getNome()); %></option>
                                        <% } %>
                                    </select>
                                </div>
                                <div id="obs" class='form-group'>
                                    <label>Observação</label>
                                    <textarea class="form-control"></textarea>
                                </div>
                                <div class="box-footer">
                                    <button value="SolicitacaoInsercao" name="acao" type="submit" class="btn btn-info pull-right">Enviar</button>
                                </div>
                            </form>
                        </div>
                    </div>
            </div>
        </section>
    </div>
    <footer class="main-footer">
        <strong>Copyright &copy; <% out.println(cal.get(Calendar.YEAR));%> <a href="http://www.umc.br">Universidade de Mogi das Cruzes</a>.</strong>
    </footer>
    <div class="control-sidebar-bg"></div>

    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script> -->
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/timepicker/bootstrap-timepicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>

    <script>
            $(function () {
                //Initialize Select2 Elements
                $(".select2").select2();

                //Datemask dd/mm/yyyy
                $("#datemask").inputmask("dd/mm/yyyy", {"placeholder": "dd/mm/yyyy"});
                //Datemask2 mm/dd/yyyy
                $("#datemask2").inputmask("mm/dd/yyyy", {"placeholder": "mm/dd/yyyy"});
                //Money Euro
                $("[data-mask]").inputmask();

                //Date range picker
                $('#reservation').daterangepicker();
                //Date range picker with time picker
                $('#reservationtime').daterangepicker({timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A'});

                //Date picker
                $('#datepicker').datepicker({
                    autoclose: true
                });

                //iCheck for checkbox and radio inputs
                $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
                    checkboxClass: 'icheckbox_minimal-blue',
                    radioClass: 'iradio_minimal-blue'
                });
                //Red color scheme for iCheck
                $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
                    checkboxClass: 'icheckbox_minimal-red',
                    radioClass: 'iradio_minimal-red'
                });
                //Flat red color scheme for iCheck
                $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });

                //Colorpicker
                $(".my-colorpicker1").colorpicker();
                //color picker with addon
                $(".my-colorpicker2").colorpicker();

                //Timepicker
                $(".timepicker").timepicker({
                    showInputs: false
                });
            });
    </script>
</body>
</html>