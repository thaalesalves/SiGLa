<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.*"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Dashboard | SiGLa</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fullcalendar.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matrix-style.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matrix-media.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/font-awesome/css/font-awesome.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.gritter.css" />
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' type='text/css'>
    </head>
    <body>
        <%
            Pessoa p;
            if ((p = (Pessoa) session.getAttribute("pessoa")) == null) {
                response.sendRedirect(request.getContextPath() + "/error/401.jsp");
            }
        %>
        <!--Header-part-->
        <div id="header" class="logo"></div>
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
        <div id="sidebar"><a href="home" class="visible-phone"><i class="icon icon-home"></i> Dashboard</a>
            <ul>
                <li><a href="home"><i class="icon icon-home"></i> <span>Dashboard</span></a> </li>
                <li style="display: none;"><a href="buttons.html"><i class="icon icon-th"></i> <span>ITEM ÚNICO</span></a></li>
                <li class="submenu"> <a href="#"><i class="icon icon-th"></i> <span>Reserva</span></a>
                    <ul>
                        <li><a href="../reserva/novo">Solicitar</a></li>
                        <li><a href="../reserva/listar">Listagem</a></li>
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
                <div id="breadcrumb"> <a href="/SiGLa" title="Ir para Home" class="tip-bottom"><i class="icon-home"></i> Home</a></div>
                <h1>Bem-vindo, <% out.println(p.getNome());%></h1>
            </div>
            <!--End-breadcrumbs-->  
        </div>
        <div class="container-fluid">
            <hr>
        </div>
        <!--end-main-container-part-->

        <!--Footer-part-->

        <div class="row-fluid">
            <div id="footer" class="span12"> 2016 &copy; Universidade de Mogi das Cruzes.</div>
        </div>

        <!--end-Footer-part-->

        <script src="${pageContext.request.contextPath}/js/excanvas.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.ui.custom.js"></script> 
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.flot.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.flot.resize.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.peity.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.dashboard.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.gritter.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.interface.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.chat.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.validate.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.form_validation.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.wizard.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.uniform.js"></script> 
        <script src="${pageContext.request.contextPath}/js/select2.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.popover.js"></script> 
        <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script> 
        <script src="${pageContext.request.contextPath}/js/matrix.tables.js"></script> 

        <script type="text/javascript">
            // This function is called from the pop-up menus to transfer to
            // a different page. Ignore if the value returned is a null string:
            function goPage(newURL) {

                // if url is empty, skip the menu dividers and reset the menu selection to default
                if (newURL != "") {

                    // if url is "-", it is this page -- reset the menu:
                    if (newURL == "-") {
                        resetMenu();
                    }
                    // else, send page to designated URL            
                    else {
                        document.location.href = newURL;
                    }
                }
            }

            // resets the menu selection upon entry to this page:
            function resetMenu() {
                document.gomenu.selector.selectedIndex = 2;
            }
        </script>
    </body>
</html>
