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
<%
    if (!util.SiGLa.getDomain().equals("null")) {
        response.sendRedirect(request.getContextPath());
    }

    Calendar cal = Calendar.getInstance();

    Pessoa p = new Pessoa();

    String msg = "null";
    String status = null;
    if ((msg = (String) session.getAttribute("msg")) != null) {
        msg = (String) session.getAttribute("msg");
        status = (String) session.getAttribute("status");
        session.removeAttribute("msg");
        session.removeAttribute("status");
    }

    if (session.getAttribute("pessoa") != null) {
        p = (Pessoa) session.getAttribute("pessoa");
        if (!p.getRole().equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/error/403");
        }
    } else if (SiGLa.getDomain().equals("null")) {
        p.setNomeCompleto("Administrador Local");
        p.setNome("Administrador");
        p.setShownName("Administrador Local");
        p.setCargo("Administrador");
        p.setDepto("Departamento de TI");
    } else {
        response.sendRedirect(request.getContextPath() + "/error/403");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Instalação | SiGLa</title>
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
        <link href="${pageContext.request.contextPath}/css/pnotify.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>        
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/pnotify.custom.js" type="text/javascript"></script> 
        <link href="${pageContext.request.contextPath}/css/wizard.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/wizard.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/css/waitMe.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/js/install.js" type="text/javascript"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->       

        <script>
            $(document).ready(function () {
                acesso = "<%=p.getRole()%>";

                if ("<%=msg%>" != "null") {
                    notify("<%=msg%>", "<%=status%>", "Aviso!");
                }
            });
        </script>
        <script src="${pageContext.request.contextPath}/js/menus.js" type="text/javascript"></script>
    </head>
    <body class="hold-transition skin-blue sidebar-mini sidebar-collapse">
        <div id="corpo" class="wrapper">           
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Administração
                        <small>instalação do sigla</small>
                    </h1>                   
                </section>

                <!-- Main content -->
                <section class="content">
                    <div id="div-assistente" class="box box-primary">
                        <div class="wizard">
                            <div class="wizard-inner">
                                <div class="connecting-line"></div>
                                <ul class="nav nav-tabs" role="tablist">

                                    <li role="presentation" class="active">
                                        <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="Banco de Dados">
                                            <span class="round-tab">
                                                <i class="fa fa-database"></i>
                                            </span>
                                        </a>
                                    </li>

                                    <li role="presentation" class="disabled">
                                        <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Active Directory">
                                            <span class="round-tab">
                                                <i class="fa fa-windows"></i>
                                            </span>
                                        </a>
                                    </li>
                                    <li role="presentation" class="disabled">
                                        <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Grupos de Acesso">
                                            <span class="round-tab">
                                                <i class="fa fa-users"></i>
                                            </span>
                                        </a>
                                    </li>

                                    <li role="presentation" class="disabled">
                                        <a href="#complete" data-toggle="tab" aria-controls="complete" role="tab" title="Conclusão">
                                            <span class="round-tab">
                                                <i class="glyphicon glyphicon-ok"></i>
                                            </span>
                                        </a>
                                    </li>
                                </ul>
                            </div>

                            <form role="form" class="form-horizontal" id="install-form">
                                <div class="tab-content">
                                    <div class="tab-pane active" role="tabpanel" id="step1">
                                        <div class="box-body" style="margin-left: 2%; margin-right: 2%;">
                                            <h3>Banco de Dados</h3>
                                            <p>Configuração do Banco de Dados</p><br/>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">SGBD</label>
                                                <div class="col-sm-10">
                                                    <select readonly id="db-dbms" name="db-dbms" class="select2 form-control" data-placeholder="Selecione uma opção" style="width: 100%;" required>
                                                        <option>Selecione uma opção</option>
                                                        <option selected value="psql">PostgreSQL</option>
                                                        <option value="mysql">MySQL / MariaDB</option>
                                                    </select>
                                                    <span class="help-block">Sistema de gerenciamento do banco de dados (SGBD)</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Host</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="db-host" name="db-host" placeholder="10.10.10.10:5432">
                                                    <span class="help-block">Endereço do banco de dados (i.e., 10.10.10.10:5432)</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Banco</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="db-name" name="db-name" placeholder="sigladb">
                                                    <span class="help-block">Nome do banco de dados</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Usuário</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="db-user" name="db-user" placeholder="siglauser">
                                                    <span class="help-block">Usuário do banco de dados</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Senha</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="password" class="form-control" id="db-passwd" name="db-passwd" placeholder="siglapasswd">
                                                    <span class="help-block">Senha do usuário</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box-footer">
                                            <ul class="list-inline pull-right">
                                                <li><button type="button" class="btn btn-primary next-step">Avançar</button></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="tab-pane" role="tabpanel" id="step2">
                                        <div class="box-body" style="margin-left: 2%; margin-right: 2%;">
                                            <h3>Active Directory</h3>
                                            <p>Configuração do Diretório</p><br/>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Autenticação</label>
                                                <div class="col-sm-10">
                                                    <select readonly id="ad-auth" name="ad-auth" class="select2 form-control" data-placeholder="Selecione uma opção" style="width: 100%;" required>
                                                        <option selected disabled>Selecione uma opção</option>
                                                        <option value="ldap">LDAP (LDAP inseguro)</option>
                                                        <option value="ldaps">LDAPS (LDAP seguro)</option>
                                                    </select>
                                                    <span class="help-block">Protocolo de conexão com o diretório</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Domínio</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ad-domain" name="ad-domain" placeholder="contoso.com">
                                                    <span class="help-block">Domínio do Active Directory (i.e., contoso.com)</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">NetBIOS</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ad-netbios" name="ad-netbios" placeholder="CONTOSO">
                                                    <span class="help-block">Nome NetBIOS do domínio</span>
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Controladora</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ad-controller" name="ad-controller" placeholder="dc.contoso.com">
                                                    <span class="help-block">Controladora de domínio (para wildcard, mantenha do mesmo valor do domínio)</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box-footer">
                                            <ul class="list-inline pull-right">
                                                <li><button type="button" class="btn btn-primary next-step">Avançar</button></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="tab-pane" role="tabpanel" id="step3">
                                        <div class="box-body" style="margin-left: 2%; margin-right: 2%;">
                                            <h3>Controle de Acesso</h3>
                                            <p>Configuração de Controle de Acesso (os grupos devem estar em formato LDAP)</p><br/>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Administrador</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ldap-admin" name="ldap-admin" placeholder="memberOf=CN=grupo,OU=grupo,DC=contoso,DC=com">
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Funcionário</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ldap-func" name="ldap-func" placeholder="memberOf=CN=grupo,OU=grupo,DC=contoso,DC=com">
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Estagiário</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ldap-est" name="ldap-est" placeholder="memberOf=CN=grupo,OU=grupo,DC=contoso,DC=com">
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Coordenador</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ldap-coord" name="ldap-coord" placeholder="memberOf=CN=grupo,OU=grupo,DC=contoso,DC=com">
                                                </div>
                                            </div>
                                            <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                <label class="col-sm-2 control-label">Professores</label>
                                                <div class="col-sm-10">
                                                    <input autocomplete="off" type="text" class="form-control" id="ldap-prof" name="ldap-prof" placeholder="memberOf=CN=grupo,OU=grupo,DC=contoso,DC=com">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="box-footer">
                                            <ul class="list-inline pull-right">
                                                <li><button type="button" class="btn btn-primary next-step">Avançar</button></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="tab-pane" role="tabpanel" id="complete">
                                        <div class="box-body" style="margin-left: 2%; margin-right: 2%;">
                                            <h3>Concluído!</h3>
                                            <p id="conclusao">Todos os dados necessários foram preenchidos. Clique aqui para revisar os dados</p>
                                            <div id="data" style="display:none;">
                                                <br/><h4>Banco de Dados</h4>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">SGBD</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-dbms">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Banco</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-dbname">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Usuário</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-dbuser">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Host</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-dbhost">
                                                    </div>
                                                </div>
                                                <br/><h4>Active Directory</h4>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Autenticação</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-auth">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Domínio</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-domain">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">Controladora</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-controller">
                                                    </div>
                                                </div>
                                                <div class='form-group' style="margin-left: -10%; margin-right: 2%;">
                                                    <label class="col-sm-2 control-label">NetBIOS</label>
                                                    <div class="col-sm-10">
                                                        <input readonly type="text" class="form-control" id="dt-netbios">
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="box-footer">
                                                <ul class="list-inline pull-right">
                                                    <li><button type="button" class="btn btn-primary btn-info-full next-step" onclick="enviar()">Completar</button></li>
                                                </ul>
                                            </div>
                                        </div>                                    
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>  
                    <div style="display:none;" class="box box-primary" id="div-sucesso">
                        <div class="box-body" style="margin-left: 2%; margin-right: 2%;">
                            <h3>Concluído!</h3>
                            <p>O SiGLa foi configurado! Clique <a href="${pageContext.request.contextPath}/">aqui</a> para prosseguir.</p><br/>
                        </div>
                    </div>
                </section>
            </div>
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

        <script src="${pageContext.request.contextPath}/js/waitMe.js" type="text/javascript"></script>

        <script>
            $(document).ready(function () {
                contextPath = "<%=request.getContextPath()%>" + "/";

                $("#conclusao").click(function () {
                    $("#data").toggle();
                });

                $(function () {
                    $("#db-name").keyup(function () {
                        $('#dt-dbname').val($('#db-name').val());
                    });

                    $("#db-user").keyup(function () {
                        $('#dt-dbuser').val($('#db-user').val());
                    });

                    $("#db-host").keyup(function () {
                        $('#dt-dbhost').val($('#db-host').val());
                    });

                    $("#ad-domain").keyup(function () {
                        $('#dt-domain').val($('#ad-domain').val());
                    });

                    $("#ad-controller").keyup(function () {
                        $('#dt-controller').val($('#ad-controller').val());
                    });

                    $("#ad-netbios").keyup(function () {
                        $('#dt-netbios').val($('#ad-netbios').val());
                    });

                    $("#ad-domain").keyup(function () {
                        // Configurações de domínio
                        $("#ad-controller").val($(this).val());
                        $("#ad-netbios").val($(this).val().toUpperCase().split(".")[0]);

                        // Grupo de acesso
                        $("#ldap-admin").val("memberOf=CN=sigla_admin,OU=GRUPOS,OU=SiGLa,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-func").val("memberOf=CN=sigla_func,OU=GRUPOS,OU=SiGLa,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-est").val("memberOf=CN=sigla_est,OU=GRUPOS,OU=SiGLa,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-coord").val("memberOf=CN=sigla_coord,OU=GRUPOS,OU=SiGLa,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);
                        $("#ldap-prof").val("memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=" + $(this).val().split(".")[0] + ",DC=" + $(this).val().split(".")[1]);

                        // Valores finais
                        $('#dt-netbios').val($('#ad-netbios').val());
                        $('#dt-controller').val($('#ad-controller').val());
                        $('#dt-dbms').val($('#db-dbms :selected').text());
                        $('#dt-auth').val($('#ad-auth :selected').text());
                    });
                });
            });
        </script>
    </body>
</html>
