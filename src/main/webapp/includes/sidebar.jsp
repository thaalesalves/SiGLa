<script>caminho = "<%=request.getContextPath()%>";</script>

<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=picPath%>" class='img-circle' alt='User Image'>
            </div>
            <div class="pull-left info">
                <p><% out.println(p.getShownName());%></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
                
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul id class="sidebar-menu">
            <li class="header">MENU</li>
            <li class="treeview">
                <a href="${pageContext.request.contextPath}/">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li id="menu-conf" class="treeview"></li>
            <li id="menu-reserva" class="treeview">
                <a href="#">
                    <i class="fa fa-calendar"></i> <span>Reservas</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu" id='items-menu-reserva'>
                    <li id="item-reserva-novo"><a href="${pageContext.request.contextPath}/controle/nova-reserva"><i class="fa fa-circle-o"></i> <span>Reservar</span></a></li>
                    <li id='item-reservas'><a href="${pageContext.request.contextPath}/reserva/lista"><i class="fa fa-circle-o"></i> Reservas</a></li>
                    <li id='item-reservas-dia'><a href="${pageContext.request.contextPath}/reserva/hoje"><i class="fa fa-circle-o"></i> Reservas do Dia</a></li>
                    <li id="item-solicitacoes"><a href="${pageContext.request.contextPath}/reserva/solicitacoes"><i class="fa fa-circle-o"></i> Solicitações</a></li>
                </ul>
            </li>
            <li id="menu-curso" class="treeview">
                <a href="#">
                    <i class="fa fa-graduation-cap"></i> <span>Cursos</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu" id='items-menu-cursos'>
                    <li id='item-lista-curso'><a href="${pageContext.request.contextPath}/curso/lista"><i class="fa fa-circle-o"></i> <span>Listagem</span></a></li>
                </ul>
            </li>
            <li id="menu-software" class="treeview">
                <a href="#">
                    <i class="fa fa-code"></i> <span>Softwares</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu" id='menu-list-software'>
                    <li>
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>Softwares</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu" id='items-menu-soft'>
                            <li id="item-lista-sw" class="treeview menu-open"><a href="${pageContext.request.contextPath}/software/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li id="menu-lab" class="treeview">
                <a href="#">
                    <i class="fa fa-desktop"></i> <span>Laboratórios</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>Laboratório</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu" id='items-menu-lab'>
                            <li id="item-lista-lab" class="treeview menu-open"><a href="${pageContext.request.contextPath}/laboratorio/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
                        </ul>
                    </li>
                    <li id='item-lista-equip'>
                        <a href="#">
                            <i class="fa fa-desktop"></i> <span>Equipamento</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu" id='items-menu-equip'>
                            <li class="treeview menu-open"><a href="${pageContext.request.contextPath}/equip/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>