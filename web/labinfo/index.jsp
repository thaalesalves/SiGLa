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

<%@page import="java.io.File"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%
    Calendar cal = Calendar.getInstance();

    Pessoa p;
    if ((p = (Pessoa) session.getAttribute("pessoa")) == null) {
        response.sendRedirect(request.getContextPath() + "/error/401");
    }

    File f = new File(request.getContextPath() + "/img/users/" + p.getUsername() + "_pic.jpg");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Dashboard | SiGLa</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/notification.css" type="text/css"/>
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body class="hold-transition skin-black-light sidebar-mini">
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
                                        if (f.exists()) {
                                            out.println("<img src='../img/users/" + p.getUsername() + "_pic.jpg' class='user-image' alt='User Image'>");
                                        } else {
                                            out.println("<img src='" + request.getContextPath() + "/img/users/thumbnail.png' class='user-image' alt='User Image'>");
                                        }
                                    %>
                                    <span class="hidden-xs"><% out.println(p.getNomeCompleto().substring(0, p.getNomeCompleto().indexOf(" "))); %></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- User image -->
                                    <li class="user-header">
                                        <%
                                            if (p.getPicture() != null) {
                                                out.println("<img src='C:/img/users/" + p.getUsername() + "_pic.jpg' class='img-circle' alt='User Image'>");
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
                                            <a href="perfil" class="btn btn-default btn-flat">Perfil</a>
                                        </div>
                                        <div class="pull-right">
                                            <a href="logout" class="btn btn-default btn-flat">Logout</a>
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
                            <a href="${pageContext.request.contextPath}/info/nova-reserva">
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
                                <li><a href="${pageContext.request.contextPath}/controle/listar-solicitacoes"><i class="fa fa-circle-o"></i> Solicitações</a></li>
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
                        <li class="header">SOFTWARES</li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/software/novo">
                                <i class="fa fa-edit"></i> <span>Inserção</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/controle/listar-softwares">
                                <i class="fa fa-files-o"></i> <span>Listagem</span>
                            </a>
                        </li>
                        <li class="header">LABORATÓRIOS</li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/laboratorio/novo">
                                <i class="fa fa-edit"></i> <span>Inserção</span>
                            </a>
                        </li>
                        <li class="treeview">
                            <a href="${pageContext.request.contextPath}/controle/listar-labs">
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
                        Dashboard
                        <small>painel de controle</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li class="active">Dashboard</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <!-- Small boxes (Stat box) -->
                    <div class="row">
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon bg-aqua"><i class="fa fa-calendar"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Reservas</span>
                                    <span class="info-box-number" id="qtd-reservas"></span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>                        
                        <!-- ./col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon bg-green"><i class="fa fa-calendar-times-o"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Reservas Hoje</span>
                                    <span class="info-box-number" id="qtd-reservas-hoje"></span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div> 
                        <!-- ./col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon bg-red"><i class="fa fa-desktop"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Laboratórios</span>
                                    <span class="info-box-number" id="qtd-labs"></span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div>                         
                        <!-- ./col -->
                        <div class="col-md-3 col-sm-6 col-xs-12">
                            <div class="info-box">
                                <span class="info-box-icon bg-yellow"><i class="fa fa-laptop"></i></span>

                                <div class="info-box-content">
                                    <span class="info-box-text">Computadores</span>
                                    <span class="info-box-number" id="qtd-computadores"></span>
                                </div>
                                <!-- /.info-box-content -->
                            </div>
                            <!-- /.info-box -->
                        </div> 
                        <!-- ./col -->
                    </div>
                    <!-- /.row -->
                    <!-- Main row -->
                    <div class="row">
                        <!-- Left col -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                    <!-- quick email widget -->
                    <div class="box box-info">
                        <div class="box-header">
                            <i class="fa fa-envelope"></i>

                            <h3 class="box-title">Email Rápido</h3>
                        </div>
                        <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                            <div class="box-body">                            
                                <div class="form-group">
                                    <input required type="email" class="form-control" name="emailto" placeholder="Destinatário">
                                </div>
                                <div class="form-group">
                                    <input required type="text" class="form-control" name="subject" placeholder="Assunto">
                                </div>
                                <div>
                                    <textarea required name="message" class="textarea" placeholder="Mensagem" style="width: 100%; height: 125px; font-size: 14px; line-height: 18px; border: 1px solid #dddddd; padding: 10px;"></textarea>
                                </div>

                            </div>
                            <div class="box-footer clearfix">
                                <button value="EnviarEmail" name="acao" type="submit" class="pull-right btn btn-default" id="sendEmail">Enviar
                                    <i class="fa fa-arrow-circle-right"></i></button>
                            </div>
                        </form>
                    </div>

                </section>
                <!-- /.Left col -->
                <!-- right col (We are only adding the ID to make the widgets sortable)-->
            </div>
        </div>
        <!-- /.content-wrapper -->
        <footer class="main-footer">
            <strong>Copyright &copy; <% out.println(cal.get(Calendar.YEAR));%> <a href="http://www.umc.br">Universidade de Mogi das Cruzes</a>.</strong>
        </footer>
        <div class="control-sidebar-bg"></div>
        <!-- ./wrapper -->

        <!-- jQuery 2.2.3 -->
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <!-- jQuery UI 1.11.4 -->
        <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
        <script>
            $.widget.bridge('uibutton', $.ui.button);
        </script>
        <!-- Bootstrap 3.3.6 -->
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <!-- Morris.js charts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
        <!-- Sparkline -->
        <script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
        <!-- jvectormap -->
        <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
        <!-- jQuery Knob Chart -->
        <script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
        <!-- daterangepicker -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
        <!-- datepicker -->
        <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
        <!-- Bootstrap WYSIHTML5 -->
        <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
        <!-- Slimscroll -->
        <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
        <!-- FastClick -->
        <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
        <!-- AdminLTE App -->
        <script src="${pageContext.request.contextPath}/dist/js/app.min.js"></script>
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
        <script src="${pageContext.request.contextPath}/dist/js/pages/dashboard.js"></script>
        <!-- AdminLTE for demo purposes -->
        <script src="${pageContext.request.contextPath}/dist/js/demo.js"></script>
    </body>
</html>
