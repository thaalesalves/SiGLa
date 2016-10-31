<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Reserva de Laboratório | SiGLa</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/js/script.js" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/colorpicker.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/datepicker.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/uniform.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/select2.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/matrix-style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/matrix-media.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/bootstrap-wysihtml5.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/old/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' type='text/css' />
        <script type="text/javascript">
            var hideDiv = function (div1, div2) {
                $(div1).show();
                $(div2).hide();
            };
        </script>
    </head>
    <body>
        <%
            Pessoa p = (Pessoa) session.getAttribute("pessoa");
        %>
        <!--Header-part-->
        <div id="header">
            <h1><a href="dashboard.html">Matrix Admin</a></h1>
        </div>
        <!--close-Header-part-->

        <!--top-Header-menu-->
        <div id="user-nav" class="navbar navbar-inverse">
            <ul class="nav">
                <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">Bem-vindo, <% out.println(p.getNome());%></span><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="icon-user"></i> Perfil</a></li>
                        <li class="divider"></li>
                        <li><a href="logout"><i class="icon-key"></i> Logout</a></li>
                    </ul>
                </li>
                <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Opções</span></a></li>
                <li class=""><a title="" href="logout"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
            </ul>
        </div>
        <!--close-top-Header-menu-->
        <!--sidebar-menu-->
        <div id="sidebar"><a href="../pagina/home" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
            <ul>
                <li><a href="../pagina/home"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>
                <li style="display: none;"><a href="buttons.html"><i class="icon icon-th"></i> <span>ITEM ÚNICO</span></a></li>
                <li class="submenu active"> <a href="#"><i class="icon icon-th"></i> <span>Reserva</span></a>
                    <ul>
                        <li><a href="novo">Solicitar</a></li>
                        <li><a href="listar">Listagem</a></li>
                    </ul>
                </li>
                <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>Laboratório</span></a>
                    <ul>
                        <li><a href="#">Listagem</a></li>
                        <li><a href="#">Cadastro</a></li>
                    </ul>
                </li>
                <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>Professor</span></a>
                    <ul>
                        <li><a href="#">Listagem</a></li>
                        <li><a href="#">Cadastro</a></li>
                    </ul>
                </li>
                <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>Software</span></a>
                    <ul>
                        <li><a href="#">Listagem</a></li>
                        <li><a href="#">Cadastro</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <!--end-sidebar-menu-->

        <!--main-container-part-->
        <div id="content">
            <!--breadcrumbs-->
            <div id="content-header">
                <div id="breadcrumb"> <a href="SiGLa/pagina/home" title="Ir para Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Laboratório</a> <a href="#" class="current">Reserva</a> </div>
                <h1>Reserva de Laboratório</h1>
            </div>
            <!--End-breadcrumbs-->
            <div class="container-fluid">
                <hr>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="widget-box">
                            <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                                <h5>Dados Necessários</h5>
                            </div>
                            <div class="widget-content nopadding">
                                <div class="control-group form-horizontal" style="padding-bottom: 2%;">
                                    <label class="control-label">Tipo de Reserva</label>
                                    <div class="controls">
                                        <a href="javascript:hideDiv('#reserva-pontual', '#reserva-semestral')">Pontual</a> | 
                                        <a href="javascript:hideDiv('#reserva-semestral', '#reserva-pontual')">Semestral</a>
                                    </div>
                                </div>
                                <!-- FORM DE RESERVA SEMESTRAL -->
                                <form style='display:none;' id="reserva-semestral" name="frm-reserva" action="AlmightyController" method="post" class="form-horizontal">
                                    <h2 style='padding-left: 6%;'>Reserva Semestral</h2>
                                    <div class="control-group">
                                        <label class="control-label">Nome Completo <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="<% out.println(p.getNomeCompleto()); %>" value="<% out.println(p.getNomeCompleto()); %>" disabled />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Email <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="<% out.println(p.getEmail());%>" value="<% out.println(p.getEmail());%>" disabled />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label id="qtd-alunos-text" class="control-label">Qtde. de Alunos <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="50" required />
                                            <span class="help-inline" style="display: none;">Este campo não pode ficar em branco</span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Módulos <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>1º Módulo (8h às 9h30)</option>
                                                <option>2º Módulo (9h40 às 11h10)</option>
                                                <option>3º Módulo (11h10 às 12h45)</option>
                                                <option>4º Módulo (13h às 15h)</option>
                                                <option>5º Módulo (15h às 17h)</option>
                                                <option>6º Módulo (17h30 às 19h)</option>
                                                <option>7º Módulo (19h às 20h30)</option>
                                                <option>8º Módulo (20h40 às 22h)</option>
                                            </select>
                                        </div>
                                    </div>    
                                    <div class="control-group">
                                        <label class="control-label">Curso <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>Engenharia Civil</option>
                                                <option>Engenharia Elétrica</option>
                                                <option>Sistemas de Informação</option>
                                                <option>Análise e Desenvolvimento de Sistemas</option>
                                                <option>Publicidade e Propaganda</option>
                                                <option>Administração</option>
                                                <option>Relações Internacionais</option>
                                                <option>Contabilidade</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Turma <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required="required">
                                                <option>1ºA</option>
                                                <option>2ºB</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Softwares <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>AltoQi</option>
                                                <option>NetBeans</option>
                                                <option>AutoCAD</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Observações</label>
                                        <div class="controls">
                                            <textarea class="span11" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-success">Enviar</button>
                                    </div>
                                </form>
                                <!-- FIM DO FORM DE RESERVA SEMESTRAL -->






                                <!-- FORM DE RESERVA PONTUAL -->
                                <form style='display:none;' id="reserva-pontual" name="frm-reserva" action="AlmightyController" method="post" class="form-horizontal">
                                    <h2 style='padding-left: 6%;'>Reserva Pontual</h2>
                                    <div class="control-group">
                                        <label class="control-label">Nome Completo <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="<% out.println(p.getNomeCompleto()); %>" value="<% out.println(p.getNomeCompleto()); %>" disabled />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Email <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="<% out.println(p.getEmail());%>" value="<% out.println(p.getEmail());%>" disabled />
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label id="qtd-alunos-text" class="control-label">Qtde. de Alunos <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input type="text" class="span11" placeholder="50" required />
                                            <span class="help-inline" style="display: none;">Este campo não pode ficar em branco</span>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Data <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <input class='span11' type='date' />
                                        </div>
                                    </div> 
                                    <div class="control-group">
                                        <label class="control-label">Módulos <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>1º Módulo (8h às 9h30)</option>
                                                <option>2º Módulo (9h40 às 11h10)</option>
                                                <option>3º Módulo (11h10 às 12h45)</option>
                                                <option>4º Módulo (13h às 15h)</option>
                                                <option>5º Módulo (15h às 17h)</option>
                                                <option>6º Módulo (17h30 às 19h)</option>
                                                <option>7º Módulo (19h às 20h30)</option>
                                                <option>8º Módulo (20h40 às 22h)</option>
                                            </select>
                                        </div>
                                    </div>    
                                    <div class="control-group">
                                        <label class="control-label">Curso <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>Engenharia Civil</option>
                                                <option>Engenharia Elétrica</option>
                                                <option>Sistemas de Informação</option>
                                                <option>Análise e Desenvolvimento de Sistemas</option>
                                                <option>Publicidade e Propaganda</option>
                                                <option>Administração</option>
                                                <option>Relações Internacionais</option>
                                                <option>Contabilidade</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Turma <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required="required">
                                                <option>1ºA</option>
                                                <option>2ºB</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Softwares <strong style="color: red;">*</strong></label>
                                        <div class="controls">
                                            <select multiple class="span11" required>
                                                <option>AltoQi</option>
                                                <option>NetBeans</option>
                                                <option>AutoCAD</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label">Observações</label>
                                        <div class="controls">
                                            <textarea class="span11" ></textarea>
                                        </div>
                                    </div>
                                    <div class="form-actions">
                                        <button type="submit" class="btn btn-success" value="ReservaPontual">Enviar</button>
                                    </div>
                                </form>
                                <!-- FIM DO FORM DE RESERVA PONTUAL -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--end-main-container-part-->

        <!--Footer-part-->

        <div class="row-fluid">
            <div id="footer" class="span12"> 2016 &copy; Universidade de Mogi das Cruzes.</div>
        </div>

        <!--end-Footer-part-->
        <script src="${pageContext.request.contextPath}/old/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/jquery.ui.custom.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/bootstrap-colorpicker.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/jquery.toggle.buttons.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/masked.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/jquery.uniform.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/matrix.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/matrix.form_common.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/wysihtml5-0.3.0.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/jquery.peity.min.js"></script>
        <script src="${pageContext.request.contextPath}/old/js/bootstrap-wysihtml5.js"></script>
        <script>
            $('.textarea_editor').wysihtml5();
        </script>
    </body>
</html>
