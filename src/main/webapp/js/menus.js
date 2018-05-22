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

$(document).ready(function() {
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
