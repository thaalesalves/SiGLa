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
<%@include file="/includes/session.jsp" %>
<%    Reserva r;
    ArrayList<Software> asw;
    ArrayList<Curso> ac;

    r = (Reserva) request.getAttribute("reserva");

    if ((r = (Reserva) session.getAttribute("reserva")) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/InfoController?acao=nova-reserva").forward(request, response);
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

        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/msgPop.css" rel="stylesheet" type="text/css"/>        
        <script src="${pageContext.request.contextPath}/js/msgPop.js" type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                accessControl("<%=p.getRole()%>");

                var msg = "<%=msg%>";
                var status = "<%=status%>";

                if (msg != "null") {
                    MsgPop.closeAll();
                    MsgPop.open({
                        Type: status,
                        Content: msg
                    });
                }
            });
        </script>
    </head>
    <body class="hold-transition skin-black-light sidebar-mini">
        <div class="wrapper">
            <%@include file="/includes/header.jsp" %>
            <%@include file="/includes/sidebar.jsp"%>
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
