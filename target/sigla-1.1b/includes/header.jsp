<script>
    contextPath = "${pageContext.request.contextPath}";
</script>
<header class="main-header">                
    <!-- Logo -->
    <a href="${pageContext.request.contextPath}" class="logo">
        <!-- mini logo for sidebar mini 50x50 pixels -->
        <span class="logo-mini"><b>S</b>i<b>GL</b>a</span>
        <!-- logo for regular state and mobile devices -->
        <span class="logo-lg"><b>S</b>i<b>GL</b>a</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>

        <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                <!-- Notifications: style can be found in dropdown.less -->
                <li style="display: none;" id='notif-menu' class="dropdown notifications-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bell-o"></i>
                        <span class="label label-warning">10</span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header">Você tem 10 notificações </li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul class="menu">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="footer"><a href="#">Ver tudo</a></li>
                    </ul>
                </li>
                <!-- Tasks: style can be found in dropdown.less -->
                <li style="display: none;" id='soli-menu' class="dropdown tasks-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-flag-o"></i>
                        <span id="qtd-res" class="label label-danger"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li class="header" id="msg-res"></li>
                        <li>
                            <!-- inner menu: contains the actual data -->
                            <ul id="res-notif" class="menu"></ul>
                        </li>
                        <li class="footer">
                            <a href="${pageContext.request.contextPath}/controle/listar-solicitacoes">Ver tudo</a>
                        </li>
                    </ul>
                </li>
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/img/users/thumbnail.png" class='user-image' alt='User Image'>
                        <span class="hidden-xs"><% out.println(p.getNome()); %></span>
                    </a>
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <%
                                if (p.getPicture() != null) {
                                    out.println("<img src='C:/img/users/" + p.getUsername() + "_pic.jpg' class='img-circle' alt='User Image'>");
                                } else {
                                    out.println("<img src='" + request.getContextPath() + "/img/users/thumbnail.png' class='img-circle' alt='User Image'>");
                                }
                            %>
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