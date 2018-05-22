/* 
 * Copyright (C) 2018 thales
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


var menuCadastroEquipamento = '<li id="item-novo-equip" class="treeview menu-open"><a href="' + contextPath + '/equip/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>';
var menuCadastroLaboratorio = '<li id="item-novo-lab" class="treeview menu-open"><a href="' + contextPath + '/laboratorio/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li>';
var menuCadastroSoftware = '<li id="item-novo-sw" class="treeview menu-open"><a href="' + contextPath + '/software/novo"><i class="fa fa-circle-o"></i>Inserção</a></li>';
var menuCadastroCurso = '<li id="item-novo-curso"><a href="' + contextPath + '/curso/novo"><i class="fa fa-circle-o"></i> <span>Inserção</span></a></li>';
var menuFornecedor = '<li id="item-lista-equip"> <a href="#"> <i class="fa fa-desktop"></i> <span>Fornecedores</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a> <ul class="treeview-menu"> <li id="item-novo-fornecedor" class="treeview menu-open"><a href="' + contextPath + '/fornecedor/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li><li id="item-lista-fornecedor" class="treeview menu-open"><a href="' + contextPath + '/fornecedor/lista"><i class="fa fa-circle-o"></i>Listagem</a></li></ul> </li>';
var menuLicenca = '<li> <a href="#"> <i class="fa fa-edit"></i> <span>Licenças</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a> <ul class="treeview-menu"> <li id="item-novo-licenca" class="treeview menu-open"><a href="' + contextPath + '/licenca/novo"><i class="fa fa-circle-o"></i>Cadastro</a></li><li id="item-lista-licenca" class="treeview menu-open"><a href="' + contextPath + '/licenca/lista"><i class="fa fa-circle-o"></i>Listagem</a></li></ul> </li>';
var menuAdministracao = '<a href="#"> <i class="fa fa-gears"></i> <span>Administração</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i> </span> </a> <ul class="treeview-menu"> <li id="item-admin-ad"><a href="' + contextPath + '/admin/activedirectory"><i class="fa fa-circle-o"></i> <span>Active Directory</span></a></li><li id="item-admin-db"><a href="' + contextPath + '/admin/database"><i class="fa fa-circle-o"></i> Banco de Dados</a></li></ul>';

$(document).ready(function () {
    switch (acesso) {
        case "funcionario":
            $('#items-menu-lab').prepend(menuCadastroLaboratorio);
            $('#items-menu-equip').prepend(menuCadastroEquipamento);
            $('#items-menu-soft').prepend(menuCadastroSoftware);
            $('#items-menu-cursos').prepend(menuCadastroCurso);
            $('#menu-conf').append(menuAdministracao);
            $('#menu-list-software').append(menuLicenca);
            $('#menu-list-software').append(menuFornecedor);
            break;
        case "admin":
            $('#items-menu-lab').prepend(menuCadastroLaboratorio);
            $('#items-menu-equip').prepend(menuCadastroEquipamento);
            $('#items-menu-soft').prepend(menuCadastroSoftware);
            $('#items-menu-cursos').prepend(menuCadastroCurso);
            $('#menu-conf').append(menuAdministracao);
            $('#menu-list-software').append(menuLicenca);
            $('#menu-list-software').append(menuFornecedor);
            break;
    }
});