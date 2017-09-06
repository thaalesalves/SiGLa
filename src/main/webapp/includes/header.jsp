<%@page import="java.io.File"%>
<script>
    contextPath = "${pageContext.request.contextPath}";
</script>
<header class="main-header">                
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}/" class="logo">
        <img src="${pageContext.request.contextPath}/img/icon-w.png" style="width:200%; padding-left: 40%; padding-top: 50%;" class="logo-mini"/>
        <img src="${pageContext.request.contextPath}/img/logo-w.png" style="width:90%; padding-top:1%;" class="logo-lg"/>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <li id='soli-menu' class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell"></i>
                        <span id="qtd-res" class="label label-danger"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header" id="msg-res"></li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul id="res-notif" class="menu"></ul>
                        </li>
                        <li class="footer">
                            <a href="${pageContext.request.contextPath}/reserva/solicitacoes">Ver tudo</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="<%=picPath%>" class='user-image' alt='User Image'>
                        <span class="hidden-xs"><% out.println(p.getNome()); %></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="<%=picPath%>" class='img-circle' alt='User Image'>
                            <p>
                                <% out.println(p.getShownName()); %>
                                <small><% out.println(p.getCargo() + " | " + p.getDepto());%></small>
                            </p>
                        </li>
                        <!-- Menu Body -->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="perfil" class="btn btn-default btn-flat">Perfil</a>
                            </div>
                            <div class="pull-right">
                                <a href="${pageContext.request.contextPath}/pagina/logout" class="btn btn-default btn-flat">Logout</a>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- Control Sidebar Toggle Button -->
            </ul>
        </div>
    </nav>
</header>