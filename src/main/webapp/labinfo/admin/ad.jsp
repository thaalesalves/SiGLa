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
        <title>Active Directory | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/icon.png">
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
                        <small>active directory</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-dashboard"></i> Administração</a></li>
                        <li>Dashboard</li>
                        <li class="active">Active Directory</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">Configurações de Domínio</h3>
                        </div>
                        <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label>Método de Autenticação</label>
                                    <select id="auth" name="auth" class="select2 form-control" data-placeholder="Protocolo de autenticação" style="width: 100%;" required>
                                        <option value="ldaps">LDAPS (LDAP seguro)</option>
                                        <option value="ldap">LDAP (LDAP inseguro)</option>
                                        <option value="kerberos">Kerberos</option>
                                    </select>
                                </div>
                                <div class='form-group'>
                                    <label>Domínio</label>
                                    <input value="<%=util.SiGLa.getDomain()%>" name="dominio" id="dominio" type='text' class='form-control pull-right' placeholder="contoso.com.br" required/>
                                </div>
                                <div class="form-group">
                                    <label>Nome NetBIOS</label>
                                    <input value="<%=util.SiGLa.getNetbios()%>" name="netbios" id="netbios" type='text' class='form-control pull-right' placeholder="CONTOSO" required/>
                                </div>
                                <div class="form-group">
                                    <label>Controladora</label>
                                    <input value="<%=util.SiGLa.getDomainHost()%>" name="host" id="host" type='text' class='form-control pull-right' placeholder="dc.contoso.com.br" required/>
                                </div>                                
                                <div style="display:none;" class="form-group">
                                    <input value="ad" name="op" id="op" type='text' class='form-control pull-right' required/>
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
            $(document).ready(function () {
                $(function () {
                    $("#dominio").keyup(function () {
                        // Configurações de domínio
                        $("#host").val($(this).val());
                        $("#netbios").val($(this).val().toUpperCase().split(".")[0]);

                        // Grupo de acesso
                        $("#ldap-admin").val("memberOf=CN=sigla_admin,OU=sigla,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-func").val("memberOf=CN=sigla_func,OU=sigla,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-est").val("memberOf=CN=sigla_est,OU=sigla,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-coord").val("memberOf=CN=sigla_coord,OU=sigla,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-prof").val("memberOf=CN=sigla_prof,OU=sigla,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                    });
                });
            });
        </script>
    </body>
</html>
