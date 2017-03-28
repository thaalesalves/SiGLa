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

<%@page import="java.util.ArrayList"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/session.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <%
            Reserva reserva = new Reserva();

            ArrayList<Reserva> arrayRes;
            if ((arrayRes = (ArrayList<Reserva>) session.getAttribute("reserva")) == null) {
                request.getRequestDispatcher(request.getContextPath() + "/AlmightyController?acao=ReservaDiaListagem").forward(request, response);
            }
        %>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Listagem de Reservas de Hoje | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/skins/_all-skins.min.css">
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            $(document).ready(function () {
                accessControl("<%=p.getRole()%>");
            });
        </script>    
    </head>
    <body class="hold-transition skin-black-light sidebar-mini">        
        <div class="wrapper">
            <%@include file="/includes/header.jsp" %>
            <%@include file="/includes/sidebar.jsp"%>
            <div class="content-wrapper">
                <section class="content-header">
                    <h1>
                        Reservas de <% out.println(reserva.getDiaSemana()); %>
                        <small>lista geral</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                        <li><a href="#">Reservas</a></li>
                        <li class="active">Reservas de <% out.println(reserva.getDiaSemana()); %></li>
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
                                        <th>Professor</th>
                                        <th>Turma</th>
                                        <th>Laboratório</th>
                                        <th>Software</th>                                        
                                        <th style="width: 3%;">Opções</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (Reserva r : arrayRes) {
                                    %>
                                    <tr class="gradeC">
                                        <td class="center"><% out.println(r.getPessoa().getShownName()); %></td>
                                        <td class="center"><% out.println(r.getTurma() + " de " + r.getCurso().getModalidade() + " em " + r.getCurso().getNome()); %></td>
                                        <td class="center"><% out.println(r.getLab().getNumero()); %></td>
                                        <td class="center"><% out.println(r.getSoftware().getFabricante() + " " + r.getSoftware().getNome()); %></td>
                                        <td class="center"><a href="" class="fa fa-wrench"></a><span>&#32; &#32; &#32;</span><a href="${pageContext.request.contextPath}/AlmightyController?acao=ReservaDiaRemocao&reserva_id=<% out.println(arrayRes.get(arrayRes.indexOf(r)).getId()); %>" class="fa fa-close"></a></td>
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
