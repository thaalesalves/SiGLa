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

$(document).ready(function () {
    switch (acesso) {
        case "funcionario":
            acessoFuncionario();
            break;

        case "estagiario":
            acessoEstagiario();
            break;

        case "coordenador":
            acessoCoordenador();
            break;

        case "professor":
            acessoProfessor();
            break;
    }

    $.ajax({
        url: contextPath + '/CounterController?acao=padrao',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            runNotifications(e);
        }
    });

    setInterval(function () {
        $("#res-notif").empty();
        $.ajax({
            url: contextPath + '/CounterController?acao=padrao',
            type: 'POST',
            cache: false,
            dataType: 'JSON',
            complete: function (e) {
                runNotifications(e);
            }
        });
    }, 10000);
});

var contextPath;
var acesso;

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
}

function acessoCoordenador() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
    $('#item-solicitacoes').hide();
    $('#item-novo-software').hide();
    $('#item-novo-lab').hide();
    $('#soli-menu').hide();
}

function acessoProfessor() {
    /* Esconde opções indevidas */
    $('#menu-conf').hide();
    $('#item-solicitacoes').hide();
    $('#item-novo-software').hide();
    $('#item-novo-lab').hide();
    $('#soli-menu').hide();
}

var runNotifications = function (e) {
    var obj = e.responseText;
    obj = JSON.parse(obj);

    if (obj.qtdSolicitacoes !== null) {
        $("#qtd-res").text(obj.qtdSolicitacoes);
        switch (true) {
            case (obj.qtdSolicitacoes === 1):
                $("#msg-res").text("Você tem " + obj.qtdSolicitacoes + " solicitação pendente");
                break;
            case (obj.qtdSolicitacoes > 1):
                $("#msg-res").text("Você tem " + obj.qtdSolicitacoes + " solicitações pendentes");
                break;
            default:
                $("#msg-res").text("Você não tem solicitações pendentes");
                break;
        }
    }

    addNotification(obj);
    jsonObject = obj;
    updateWidgets(obj);
};

var addNotification = function (obj) {
    for (i = 0; i < obj.qtdSolicitacoes; i++) {
        $("#res-notif").append("<li><a href='#'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj.solicitacoes[i].pessoa.shownName + " pendente.</a></li>");
    }
};