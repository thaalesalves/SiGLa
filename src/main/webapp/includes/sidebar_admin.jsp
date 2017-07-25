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
            <li class="header">PRINCIPAL</li>
            <li>
                <a href="${pageContext.request.contextPath}">
                    <i class="fa fa-dashboard"></i> <span>Dashboard</span>
                </a>
            </li>
            <li class="active treeview">
                <a href="${pageContext.request.contextPath}/admin">
                    <i class="fa fa-gears"></i> <span>Administração</span>
                </a>
            </li>            
            <li class="header">CONFIGURAÇÕES</li>
            <li class="treeview">
                <a href="${pageContext.request.contextPath}/admin/activedirectory">
                    <i class="fa fa-windows"></i> <span>Active Directory</span>
                </a>
            </li> 
            <li id='item-novo-curso' class="treeview">
                <a href="${pageContext.request.contextPath}/admin/database">
                    <i class="fa fa-database"></i> <span>Banco de Dados</span>
                </a>
            </li> 
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>