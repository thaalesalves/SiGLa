<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Listagem de Reservas | SiGLa</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="css/uniform.css" />
        <link rel="stylesheet" href="css/select2.css" />
        <link rel="stylesheet" href="css/matrix-style.css" />
        <link rel="stylesheet" href="css/matrix-media.css" />
        <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
    </head>

    <body>
        <%
            Pessoa p = (Pessoa) session.getAttribute("pessoa");
            ArrayList<Reserva> arrayRes;
            if (session.getAttribute("reserva") != null) {
                arrayRes = (ArrayList<Reserva>) session.getAttribute("reserva");
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
                        <li><a href="AlmightyController?acao=Logout"><i class="icon-key"></i> Logout</a></li>
                    </ul>
                </li>
                <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Opções</span></a></li>
                <li class=""><a title="" href="AlmightyController?acao=Logout"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
            </ul>
        </div>
        <!--close-top-Header-menu-->
        <!--sidebar-menu-->
        <div id="sidebar"><a href="/" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
            <ul>
                <li><a href="/"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>
                <li style="display: none;"><a href="buttons.html"><i class="icon icon-th"></i> <span>ITEM ÚNICO</span></a></li>
                <li class="submenu active"> <a href="#"><i class="icon icon-th"></i> <span>Reserva</span></a>
                    <ul>
                        <li><a href="RedirectController?acao=solicitar-reserva">Solicitar</a></li>
                        <li><a href="RedirectController?acao=listar-reserva">Listagem</a></li>
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
                <div id="breadcrumb"> <a href="/SiGLa" title="Ir para Home" class="tip-bottom"><i class="icon-home"></i> Home</a> <a href="#" class="tip-bottom">Laboratório</a> <a href="#" class="current">Reserva</a> </div>
                <h1>Listagem de Reservas</h1>
            </div>
            <!--End-breadcrumbs-->
            <div class="container-fluid">
                <hr>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="widget-box">
                            <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
                                <h5>Data table</h5>
                            </div>
                            <div class="widget-content nopadding">
                                <table class="table table-bordered data-table">
                                    <thead>
                                        <tr>
                                            <th>Tipo</th>
                                            <th>Curso</th>
                                            <th>Turma</th>
                                            <th>Laboratório</th>
                                            <th>Software</th>
                                            <th>Professor</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            for (Reserva r : arrayRes) {
                                        %>
                                        <tr class="gradeC">
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getTipo()); %></td>
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getCurso().getModalidade() + " em " + arrayRes.get(arrayRes.indexOf(r)).getCurso().getNome()); %></td>
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getTurma().getSemestre() + "º" + arrayRes.get(arrayRes.indexOf(r)).getTurma().getTurma()); %></td>
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getLab().getNumero()); %></td>
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getSoftware().getFabricante() + " " + arrayRes.get(arrayRes.indexOf(r)).getSoftware().getNome()); %></td>
                                            <td class="center"><% out.println(arrayRes.get(arrayRes.indexOf(r)).getPessoa().getNome() + " " + arrayRes.get(arrayRes.indexOf(r)).getPessoa().getNomeCompleto().substring(arrayRes.get(arrayRes.indexOf(r)).getPessoa().getNomeCompleto().lastIndexOf(" ") + 1)); %></td>
                                        </tr>
                                        <% } %>
                                    </tbody>
                                </table>
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
        <script src="js/jquery.min.js"></script> 
        <script src="js/jquery.ui.custom.js"></script> 
        <script src="js/bootstrap.min.js"></script> 
        <script src="js/jquery.uniform.js"></script> 
        <script src="js/select2.min.js"></script> 
        <script src="js/jquery.dataTables.min.js"></script> 
        <script src="js/matrix.js"></script> 
        <script src="js/matrix.tables.js"></script>
        <%
            } else {
                request.getRequestDispatcher("/AlmightyController?acao=Reserva").forward(request, response);
            }
        %>
    </body>
</html>
