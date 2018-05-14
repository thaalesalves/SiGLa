/* 
 * Copyright (C) 2017 thaal
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

var caminho;
//var contextPath;
var acesso;

console.log('ESTOU NO JS: ' + contextPath);

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
    if (acesso == "admin" || acesso == "funcionario" || acesso == "estagiario") {
        $.ajax({
            url: contextPath + '/JsonController?acao=Contador',
            type: 'POST',
            cache: false,
            dataType: 'JSON',
            complete: function (e) {
                notification(e);
            }
        });
    }

    setInterval(function () {
        if (acesso == "admin" || acesso == "funcionario" || acesso == "estagiario") {
            $("#res-notif").empty();
            $.ajax({
                url: contextPath + '/JsonController?acao=Contador',
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    notification(e);
                }
            });
        }
    }, 10000);
});

function acessoFuncionario() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
}

function acessoEstagiario() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
    $('#item-reserva-novo').hide();
    $('#item-novo-software').hide();
    $('#item-novo-lab').hide();
    $('#item-novo-equip').hide();
}

function acessoCoordenador() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
    $('#item-solicitacoes').hide();
    $('#item-novo-curso').hide();
    $('#item-novo-software').hide();
    $('#item-novo-lab').hide();
    $('#soli-menu').hide();
    $('#item-lista-equip').hide();
}

function acessoProfessor() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
    $('#item-novo-curso').hide();
    $('#item-solicitacoes').hide();
    $('#item-novo-software').hide();
    $('#item-novo-lab').hide();
    $('#soli-menu').hide();
    $('#item-lista-equip').hide();
}

function notification(e) {
    obj = JSON.parse(e.responseText);

    if (obj.qtdSolicitacoes !== null) {
        $("#qtd-res").text(obj.qtdSolicitacoes);
        switch (true) {
            case (obj.qtdSolicitacoes === 1):
                $("#msg-res").text("Você tem " + obj.qtdSolicitacoes + " solicitação pendente");
                for (i = 0; i < obj.qtdSolicitacoes; i++) {
                    $("#res-notif").append("<li><a data-toggle='modal' data-target='#myModal' onclick='modalSolicitacao(" + obj.solicitacoes[i].id + ");'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj.solicitacoes[i].pessoa.shownName + " pendente.</a></li>");
                }
                break;
            case (obj.qtdSolicitacoes > 1):
                $("#msg-res").text("Você tem " + obj.qtdSolicitacoes + " solicitações pendentes");
                for (i = 0; i < obj.qtdSolicitacoes; i++) {
                    $("#res-notif").append("<li><a data-toggle='modal' data-target='#myModal' onclick='modalSolicitacao(" + obj.solicitacoes[i].id + ");'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj.solicitacoes[i].pessoa.shownName + " pendente.</a></li>");
                }
                break;
            default:
                $("#msg-res").text("Você não tem solicitações pendentes");
                break;
        }
    }

    updateWidgets(obj);
}
