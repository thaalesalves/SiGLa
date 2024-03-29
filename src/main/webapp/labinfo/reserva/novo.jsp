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
    ArrayList<Pessoa> ps;

    r = (Reserva) request.getAttribute("reserva");

    if ((r = (Reserva) session.getAttribute("reserva")) == null) {
        request.getRequestDispatcher(request.getContextPath() + "/InfoController?acao=nova-reserva").forward(request, response);
    }

    asw = r.getSoftwares();
    ac = r.getCursos();

    ps = (ArrayList<Pessoa>) session.getAttribute("todos-usuarios");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Nova Reserva | SiGLa</title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/img/icon.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/flat/blue.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
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
        <script src="${pageContext.request.contextPath}/js/reserva.new.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>

        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            var mods = "vazio";
            var dia = "vazio";
            var sws = "vazio";
            var alunos = "vazio";

            $(document).ready(function () {
                acesso = "<%=p.getRole()%>";
                notify("<%=msg%>", "<%=status%>");

                switch (acesso) {
                    case "admin":
                        formFuncionario();
                        break;

                    case "funcionario":
                        formFuncionario();
                        break;

                    case "coordenador":
                        formCoordenador();
                        break;

                    case "professor":
                        formProfessor();
                        break;
                }
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
                            <h3 class="box-title">Reserva</h3>
                        </div>
                        <div id="form-soli-func" class="box-body">                                                                
                            <form action="${pageContext.request.contextPath}/AlmightyController" method="post" onsubmit="secureInjection()">
                                <div class='form-group' style="display:none;" >
                                    <input value="<% out.println(p.getUsername()); %>" type="text" id="professor" name="professor"/>
                                    <input value="<% out.println(p.getRole()); %>" type="text" id="role" name="role"/>
                                </div>
                                <div id="user-fixo" class='form-group' style="display:none;">
                                    <label>Usuário</label>
                                    <input type='text' class='form-control pull-right' value="<% out.println(p.getNomeCompleto() + " (" + p.getEmail().trim() + ")"); %>" readonly/>
                                </div>
                                <% if (!p.getRole().equals("professor")) { %>
                                <div id="user-select" class='form-group' style="display:none;">
                                    <label>Usuário</label>
                                    <select id="usuario" class="select2 form-control" data-placeholder="Selecione um professor" style="width: 100%;">
                                        <option selected disabled>Usuário</option>
                                        <% for (Pessoa pessoa : ps) { %>
                                        <option value="<% out.println(pessoa.getUsername()); %>"><% out.println(pessoa.getNomeCompleto() + " (" + pessoa.getEmail() + ")"); %></option>
                                        <% } %>
                                    </select>
                                </div>
                                    <% } %>
                                <div class='form-group'>
                                    <label>Turma</label>
                                    <input id="turma" required type='text' class='form-control pull-right' name='turma' placeholder="1ºA" autocomplete="off" />
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
                                    <input min="1" required type='number' class='form-control pull-right' name='qtd-alunos' id='qtd-alunos' placeholder="50" autocomplete="off"/>
                                </div>
                                <div class="form-group">
                                    <label>Módulo</label>
                                    <select id="modulo" name="modulo" class="select2 form-control" data-placeholder="Módulo" style="width: 100%;" required multiple>
                                        <option value="1">1º Módulo (8h às 9h30)</option>
                                        <option value="2">2º Módulo (9h40 às 11h10)</option>
                                        <option value="3">3º Módulo (11h10 às 12h40)</option>
                                        <option value="4">4º Módulo (13h às 14h30)</option>
                                        <option value="5">5º Módulo (14h30 às 17h30)</option>
                                        <option value="6">6º Módulo (17h30 às 19h)</option>
                                        <option value="7">7º Módulo (19h às 20h30)</option>
                                        <option value="8">8º Módulo (20h40 às 22h)</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Dia da Semana</label>
                                    <select id="dia-semana" name="dia-semana" id="dia-semana" class="select2 form-control" data-placeholder="Selecione o dia" style="width: 100%;" required>
                                        <option selected disabled>Selecione um dia</option>
                                        <option value="Segunda-feira">Segunda-feira</option>
                                        <option value="Terça-feira">Terça-feira</option>
                                        <option value="Quarta-feira">Quarta-feira</option>
                                        <option value="Quinta-feira">Quinta-feira</option>
                                        <option value="Sexta-feira">Sexta-feira</option>
                                        <option value="Sábado">Sábado</option>
                                    </select>
                                </div>
                                <div class='form-group'>
                                    <label>Softwares</label>
                                    <select id="softwares" name="softwares" class="select2 form-control" data-placeholder="Selecione um software" style="width: 100%;" multiple required>
                                        <% for (Software sw : asw) { %>
                                        <option value="<% out.println(asw.get(asw.indexOf(sw)).getId()); %>"><% out.println(asw.get(asw.indexOf(sw)).getFabricante() + " " + asw.get(asw.indexOf(sw)).getNome()); %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class='form-group'>
                                    <label>Observação</label>
                                    <textarea id="obs" name="obs" class="form-control"></textarea>
                                </div>
                                <div id="lab-field" class='form-group' style="display:none;">
                                    <label>Laboratório</label>
                                    <select id="laboratorio" name="laboratorio" id="modalLabCombo" class="select2 form-control" style="width: 100%;" required>
                                        <option selected></option>
                                    </select>
                                </div>
                                <div class="box-footer">
                                    <button value="SolicitacaoInsercao" name="acao" type="submit" class="btn btn-info pull-right">Enviar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </section>
            </div>
        </div>
        <%@include file="/includes/footer.jsp" %>
        <div class="control-sidebar-bg"></div>
        
        <script>
            $(document).on('change', '#modulo', function () {
                console.log('Módulo alterado');
                mods = $('#modulo').val().toString().replace(/[^0-9\.]/g, '').split('');
                dia = $('#dia-semana').val();
                sws = $('#softwares').val().toString().replace(/[^0-9\.]/g, '').split('');
                alunos = $('#qtd-alunos').val();
                populaSolicitacaoLabs(mods, dia, sws, alunos);
            });

            $(document).on('change', '#dia-semana', function () {
                console.log('Dia alterado');
                mods = $('#modulo').val().toString().replace(/[^0-9\.]/g, '').split('');
                dia = $('#dia-semana').val();
                sws = $('#softwares').val().toString().replace(/[^0-9\.]/g, '').split('');
                alunos = $('#qtd-alunos').val();
                populaSolicitacaoLabs(mods, dia, sws, alunos);
            });

            $(document).on('change', '#softwares', function () {
                console.log('Software alterado');
                mods = $('#modulo').val().toString().replace(/[^0-9\.]/g, '').split('');
                dia = $('#dia-semana').val();
                sws = $('#softwares').val().toString().replace(/[^0-9\.]/g, '').split('');
                alunos = $('#qtd-alunos').val();
                populaSolicitacaoLabs(mods, dia, sws, alunos);
            });
            
            $(document).on('change', '#qtd-alunos', function () {
                console.log('Quantidade de alunos alterada');
                mods = $('#modulo').val().toString().replace(/[^0-9\.]/g, '').split('');
                dia = $('#dia-semana').val();
                sws = $('#softwares').val().toString().replace(/[^0-9\.]/g, '').split('');
                alunos = $('#qtd-alunos').val();
                populaSolicitacaoLabs(mods, dia, sws, alunos);
            });

            $(document).on('change', '#usuario', function () {
                var opt = $('#usuario :selected').val();
                $('#professor').val(opt);
            });
        </script>
        
        <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script> -->
        <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
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
    </body>
</html>
