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

<%@page import="model.Curso"%>
<%@page import="model.Software"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="util.SiGLa"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/session.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Banco de Dados | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/favicon.png">
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
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Administração
                        <small>banco de dados</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Administração</a></li>
                        <li>Dashboard</li>
                        <li class="active">Banco de Dados</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Configurações de Banco de Dados</h3>
                        </div>
                        <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                            <div class="box-body">    
                                <div class="form-group">
                                    <label>SGBD</label>
                                    <select id="ad-auth" name="ad-auth" class="select2 form-control" data-placeholder="Selecione uma opção" style="width: 100%;" required>
                                        <option disabled selected>Selecione uma opção</option>
                                        <option value="mysql">MySQL / MariaDB</option>
                                        <option value="psql">PostgreSQL</option>
                                    </select>
                                    <span class="help-block">Protocolo de conexão com o diretório</span>
                                </div>
                                <div class='form-group'>
                                    <label>Base de dados</label>
                                    <input value="<%=util.SiGLa.getDbName()%>" autocomplete="off" id="db-name" name="db-name" type='text' class='form-control pull-right' placeholder="sigladb" />
                                </div>
                                <div class="form-group">
                                    <label>Usuário</label>
                                    <input value="<%=util.SiGLa.getDbUser()%>" autocomplete="off" id="db-user" name="db-user" type='text' class='form-control pull-right' placeholder="siglauser" />
                                </div>
                                <div class="form-group">
                                    <label>Senha</label>
                                    <input value="******" autocomplete="off" id="db-passwd" name="db-passwd" type='password' class='form-control pull-right' placeholder="siglapasswd" />
                                </div>
                                <div class="form-group">
                                    <label>Servidor</label>
                                    <input value="<%=util.SiGLa.getDbAddr()%>" autocomplete="off" id="db-host" name="db-host" type='text' class='form-control pull-right' placeholder="contoso.com:5432" />
                                </div>
                                <div style="display:none;" class="form-group">
                                    <input value="db" name="op" id="op" type='text' class='form-control pull-right' required/>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button value="Configuration" name="acao" type="submit" class="btn btn-info pull-right">Enviar</button>
                            </div>
                        </form>
                    </div>
                </section>
            </div>
            <%@include file="/includes/footer.jsp" %>
            <div class="control-sidebar-bg"></div>
        </div>

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
                //Date range as a button
                $('#daterange-btn').daterangepicker(
                        {
                            ranges: {
                                'Today': [moment(), moment()],
                                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                                'This Month': [moment().startOf('month'), moment().endOf('month')],
                                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
                            },
                            startDate: moment().subtract(29, 'days'),
                            endDate: moment()
                        },
                        function (start, end) {
                            $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
                        }
                );

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
