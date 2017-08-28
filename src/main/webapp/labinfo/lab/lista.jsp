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

<%@page import="model.Laboratorio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/session.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <%            ArrayList<Laboratorio> labs;
            if ((labs = (ArrayList<Laboratorio>) session.getAttribute("laboratorios")) == null) {
                request.getRequestDispatcher(request.getContextPath() + "/AlmightyController?acao=LaboratorioListagem").forward(request, response);
                session.removeAttribute("laboratorios");
            }
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Listagem de Laboratórios | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/pnotify.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/pnotify.custom.js" type="text/javascript"></script> 

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            $(document).ready(function () {
                acesso = "<%=p.getRole()%>";
                notify("<%=msg%>", "<%=status%>");
            });
        </script>  
        <script src="${pageContext.request.contextPath}/js/menus.js" type="text/javascript"></script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini sidebar-collapse">        
        <div class="wrapper">
            <%@include file="/includes/header.jsp" %>
            <%@include file="/includes/sidebar.jsp"%>
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Laboratório
                        <small>lista geral</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Laboratórios</a></li>
                        <li class="active">Listagem de laboratório</li>
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
                                        <th>Número</th>
                                        <th>Qtd. de Computadores</th>
                                        <th>Capacidade de Alunos</th>
                                        <th style="width: 3%;">Opções</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (Laboratorio l : labs) {
                                    %>
                                    <tr class="gradeC">
                                        <td class="center"><% out.println(l.getNumero()); %></td>
                                        <td class="center"><% out.println(l.getComputadores()); %></td>
                                        <td class="center"><% out.println(l.getCapacidade()); %></td>
                                        <td class="center"><a href="" class="fa fa-wrench"></a><span>&#32; &#32; &#32;</span><a href="${pageContext.request.contextPath}/AlmightyController?acao=LaboratorioRemocao&curso_id=<% out.println(l.getId()); %>" class="fa fa-close"></a></td>
                                    </tr>
                                    <% } %>
                                </tbody>
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
