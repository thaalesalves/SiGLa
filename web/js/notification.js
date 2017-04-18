/* 
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.
 
 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 
 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 
 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */

var jsonObject;
var id;
var dados;
var parameter;

var addNotification = function (obj) {
    for (i = 0; i < obj.qtdSolicitacoes; i++) {
        $("#res-notif").append("<li><a href='#'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj.solicitacoes[i].pessoa.shownName + " pendente.</a></li>");
    }
};

var updateWidgets = function (obj) {
    $("#qtd-reservas").text(obj.qtdReservas);
    $("#qtd-reservas-hoje").text(obj.qtdReservasHoje);
    $("#qtd-labs").text(obj.qtdLaboratorios);
    $("#qtd-computadores").text(obj.qtdComputadores);
};

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

var showSolicitacaoModal = function (counter) {
    $("#modalIdSolicitacao").val(jsonObject.solicitacoes[counter].id);
    $("#modalProfessor").val(jsonObject.solicitacoes[counter].pessoa.shownName);
    $("#modalCurso").val(jsonObject.solicitacoes[counter].turma + " de " + jsonObject.solicitacoes[counter].curso.modalidade + " em " + jsonObject.solicitacoes[counter].curso.nome);
    $("#modalSoftware").val(jsonObject.solicitacoes[counter].softwares.fabricante + " " + jsonObject.solicitacoes[counter].softwares.nome);
    $("#modalModulo").val(jsonObject.solicitacoes[counter].modulo);
    $("#modalDiaSemana").val(jsonObject.solicitacoes[counter].diaSemana);
    $("#modalQtdAlunos").val(jsonObject.solicitacoes[counter].qtdAlunos);
    $("#modalObservacao").val(jsonObject.solicitacoes[counter].observacao);

    for (i = 0; i < jsonObject.laboratorios.length + 1; i++) {
        $("#modalLabCombo").append($('<option>', {
            value: jsonObject.laboratorios[i].id,
            text: jsonObject.laboratorios[i].numero
        }));
    }
};

var aprovarReserva = function () {
    parameter = "&solicitacao=" + $("#modalIdSolicitacao").val() + "&laboratorio=" + $("#modalLabCombo").val();
    window.location.href = "http://localhost:8084/SiGLa/AlmightyController?acao=SolicitacaoAprovacao" + parameter;
};

var reprovarReserva = function () {
    window.location.href = "http://localhost:8084/SiGLa/AlmightyController?solicitacao_id=" + id + "&acao=SolicitacaoRemocao";
};

var accessControl = function (role) {
    if (role == "professor") {
        $('#form-soli-fixo').show();
    } else if (role == "coordenador") {
        $('#form-soli').show();
    } else {
        $('#soli-menu').show();
        $('#notif-menu').show();
        $('#item-novo-curso').show();
        $('#item-novo-software').show();
        $('#item-novo-lab').show();
        $('#item-solicitacoes').show();
        $('#form-soli').show();
        $('#counters').show();
    }
};

$(document).ready(function () {
    $.ajax({
        url: '/SiGLa/CounterController',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            runNotifications(e);
        }
    });
});

setInterval(function () {
    $("#res-notif").empty();
    $.ajax({
        url: '/SiGLa/CounterController',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            runNotifications(e);
        }
    });
}, 10000);

