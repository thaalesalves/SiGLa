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

<%@page import="model.Modulo"%>
<%@page import="model.Software"%>
<%@page import="model.Solicitacao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Reserva"%>
<%@page import="model.Pessoa"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/includes/session.jsp" %>
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
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/pnotify.custom.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/notification.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/pnotify.custom.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/js/reserva.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/laboratorio.js" type="text/javascript"></script>
        
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script>
            $(document).ready(function () {
                acesso = "<%=p.getRole()%>";
                notify("<%=msg%>", "<%=status%>");
                carregaSolicitacoes();
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
                                        <th style='width: 1%;'>#</th>
                                        <th style='width: 10%;'>Professor</th>
                                        <th style='width: 1%;'>Módulos</th>
                                        <th>Turma</th>
                                        <th style='width: 11%;'>Dia da Semana</th>
                                        <th style='width: 15%;'>Softwares</th>
                                        <th style="width: 5%;">Opções</th>
                                    </tr>
                                </thead>
                                <tbody style='display: none;'></tbody>
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

        <!-- ========== JANELA MODAL ========== -->
        <form action="${pageContext.request.contextPath}/AlmightyController" method="post">
            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h5 class="modal-title" id="solicitacao-modal-titulo">Solicitação</h5>                                                        
                        </div>     
                        <div class="modal-body">
                            <table cellpadding="0" cellspacing="0" border="0">
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label>Solicitação nº</label>
                                        </div>
                                    </td>
                                    <td style="width:100%;">
                                        <div class="form-group">
                                            <input style="width: 80%;" disabled type="text" class="form-control pull-right" name="modalIdSolicitacao" id="modalIdSolicitacao" placeholder="Número da Solicitação" />
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Professor</label>                                            
                                        </div>
                                    </td>
                                    <td>
                                        <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalProfessor" id="modalProfessor" placeholder="Nome do Professor" /
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Turma</label>                                            
                                        </div>
                                    </td>
                                    <td>
                                        <input style="width: 80%;" disabled type='text' class='form-control pull-right' name="modalCurso" id="modalCurso" placeholder="Nome do Curso" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Software</label>
                                        </div>
                                    </td>
                                    <td>
                                        <textarea style="width: 80%;" disabled class='form-control pull-right' name="modalSoftware" id="modalSoftware" placeholder="Nome do Software"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Módulo</label>
                                        </div>
                                    </td>
                                    <td>
                                        <textarea style="width: 80%;" disabled class='form-control pull-right' name="modalModulo" id="modalModulo" placeholder="Módulo"></textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Dia da Semana</label>
                                        </div>
                                    </td>
                                    <td>
                                        <input style="width: 80%;" disabled type='text' class='form-control pull-right' id="modalDiaSemana" name="modalDiaSemana" id="modalDiaSemana" placeholder="Dia da Semana" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Qtd. de Alunos</label>
                                        </div>   
                                    </td>
                                    <td>
                                        <input style="width: 80%;" disabled type='text' class='form-control pull-right' nome="modalQtdAlunos" id="modalQtdAlunos" placeholder="Quantidade de Alunos" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class='form-group'>
                                            <label>Observações</label>
                                        </div>   
                                    </td>
                                    <td>
                                        <input style="width: 80%;" disabled type='textarea' class='form-control pull-right' nome="modalObservacao" id="modalObservacao" placeholder="Observações" />
                                    </td>
                                </tr>
                            </table>
                        </div>                        
                        <div id="modal-footer" class="modal-footer">
                            <button data-toggle="modal" data-target="#labModal" type="button" class="btn btn-success" onclick="modalLabs()">Aprovar</button>
                            <button id="btnModalReprovar" type="button" class="btn btn-danger" onclick="reprovarReserva()">Reprovar</button><br/>
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
                            <!--<button id='btnModalAprovar' value="SolicitacaoAprovacao" type="submit" name="acao" class="btn btn-success">Aprovar</button>-->
                            <button id="btnModalAprovar" type="button" class="btn btn-success" onclick="aprovarReserva()">Aprovar</button><br/>
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
