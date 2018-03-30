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
                <a href="${pageContext.request.contextPath}/">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li id="menu-conf" class="treeview">
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
            <li id="menu-reserva" class="treeview">
                <a href="#">
                    <i class="fa fa-calendar"></i> <span>Reservas</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id="item-reserva-novo"><a href="${pageContext.request.contextPath}/controle/nova-reserva"><i class="fa fa-circle-o"></i> <span>Reservar</span></a></li>
                    <li id='item-solicitacoes'><a href="${pageContext.request.contextPath}/reserva/solicitacoes"><i class="fa fa-circle-o"></i> Solicitações</a></li>
                    <li id='item-reservas'><a href="${pageContext.request.contextPath}/reserva/lista"><i class="fa fa-circle-o"></i> Reservas</a></li>
                    <li id='item-reservas-dia'><a href="${pageContext.request.contextPath}/reserva/hoje"><i class="fa fa-circle-o"></i> Reservas do Dia</a></li>
                </ul>
            </li>
            <li id="menu-curso" class="treeview">
                <a href="#">
                    <i class="fa fa-graduation-cap"></i> <span>Cursos</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li id='item-novo-curso'><a href="${pageContext.request.contextPath}/curso/novo"><i class="fa fa-circle-o"></i> <span>Inserção</span></a></li>
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
                <ul class="treeview-menu">
                    <li id='item-novo-software'><a href="${pageContext.request.contextPath}/software/novo"><i class="fa fa-circle-o"></i> <span>Inserção</span></a></li>
                    <li id='item-lista-software'><a href="${pageContext.request.contextPath}/software/lista"><i class="fa fa-circle-o"></i> <span>Listagem</span></a></li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#">
                            <i class="fa fa-edit"></i> <span>Licenças</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="item-novo-lab" class="treeview menu-open"><a href="${pageContext.request.contextPath}/licenca/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>
                            <li id="item-lista-lab" class="treeview menu-open"><a href="${pageContext.request.contextPath}/licenca/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
                        </ul>
                    </li>
                    <li id='item-lista-equip'>
                        <a href="#">
                            <i class="fa fa-desktop"></i> <span>Fornecedores</span>
                            <span class="pull-right-container">
                                <i class="fa fa-angle-left pull-right"></i>
                            </span>
                        </a>
                        <ul class="treeview-menu">
                            <li id="item-novo-equip" class="treeview menu-open"><a href="${pageContext.request.contextPath}/fornecedor/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>
                            <li class="treeview menu-open"><a href="${pageContext.request.contextPath}/fornecedor/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
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
                        <ul class="treeview-menu">
                            <li id="item-novo-lab" class="treeview menu-open"><a href="${pageContext.request.contextPath}/laboratorio/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>
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
                        <ul class="treeview-menu">
                            <li id="item-novo-equip" class="treeview menu-open"><a href="${pageContext.request.contextPath}/equip/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>
                            <li class="treeview menu-open"><a href="${pageContext.request.contextPath}/equip/lista"><i class="fa fa-circle-o"></i>Listagem</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>