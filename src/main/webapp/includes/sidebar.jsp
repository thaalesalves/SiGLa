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
        <ul class="sidebar-menu">
            <li class="header">MENU</li>
            <li class="treeview">
                <a href="${pageContext.request.contextPath}">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-gears"></i> <span>Administração</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="item-admin-ad"><a href="${pageContext.request.contextPath}/admin/activedirectory"><i class="fa fa-circle-o"></i> <span>Active Directory</span></a></li>
                    <li id="item-admin-db"><a href="${pageContext.request.contextPath}/admin/database"><i class="fa fa-circle-o"></i> Banco de Dados</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-calendar"></i> <span>Reservas</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="item-reserva-novo"><a href="${pageContext.request.contextPath}/controle/nova-reserva"><i class="fa fa-circle-o"></i> <span>Solicitar</span></a></li>
                    <li style="display: none;" id='item-solicitacoes'><a href="${pageContext.request.contextPath}/controle/listar-solicitacoes"><i class="fa fa-circle-o"></i> Solicitações</a></li>
                    <li id='item-reservas'><a href="${pageContext.request.contextPath}/controle/listar-reservas"><i class="fa fa-circle-o"></i> Todas as Reservas</a></li>
                    <li id='item-reservas-dia'><a href="${pageContext.request.contextPath}/controle/listar-reservas-hoje"><i class="fa fa-circle-o"></i> Reservas do Dia</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-graduation-cap"></i> <span>Cursos</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li style="display: none;" id='item-novo-curso'><a href="${pageContext.request.contextPath}/curso/novo"><i class="fa fa-circle-o"></i> <span>Inserção</span></a></li>
                    <li id='item-lista-curso'><a href="${pageContext.request.contextPath}/controle/listar-curso"><i class="fa fa-circle-o"></i> <span>Listagem</span></a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-code"></i> <span>Softwares</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li style="display: none;" id='item-novo-software'><a href="${pageContext.request.contextPath}/software/novo"><i class="fa fa-circle-o"></i> <span>Inserção</span></a></li>
                    <li id='item-lista-software'><a href="${pageContext.request.contextPath}/controle/listar-softwares"><i class="fa fa-circle-o"></i> <span>Listagem</span></a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-desktop"></i> <span>Laboratórios</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li style="display: none;" id='item-novo-lab'><a href="${pageContext.request.contextPath}/laboratorio/novo"><i class="fa fa-edit"></i> <span>Inserção</span></a></li>
                    <li id='item-lista-lab'><a href="${pageContext.request.contextPath}/controle/listar-labs"><i class="fa fa-desktop"></i> <span>Listagem</span></a></li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>