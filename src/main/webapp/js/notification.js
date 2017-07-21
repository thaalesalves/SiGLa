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

var showSolicitacaoModal = function (item) {
    id = item;
    $.ajax({
        url: '/JsonController?acao=solicitacao&id=' + item,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var jsonSolicitacao = JSON.parse(e.responseText);

            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#modalProfessor").val(jsonSolicitacao.pessoa.shownName);
            $("#modalCurso").val(jsonSolicitacao.turma + " de " + jsonSolicitacao.curso.modalidade + " em " + jsonSolicitacao.curso.nome);
            $("#modalDiaSemana").val(jsonSolicitacao.diaSemana);
            $("#modalQtdAlunos").val(jsonSolicitacao.qtdAlunos);
            $("#modalObservacao").val(jsonSolicitacao.observacao);

            $("#modalSoftware").val("");
            $("#modalModulo").val("");
            
            for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                var software = jsonSolicitacao.softwares[i].fabricante + " " + jsonSolicitacao.softwares[i].nome;
                software += (i == jsonSolicitacao.softwares.length - 1) ? "" : "\r\n";
                $("#modalSoftware").val($("#modalSoftware").val() + software);
            }
            
            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                var modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }
        }
    });
};

var showReservaModal = function (item) {
    id = item;
    $.ajax({
        url: '/JsonController?acao=reserva&id=' + item,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var jsonSolicitacao = JSON.parse(e.responseText);

            $("#reserva-modal-titulo").html("Reserva nº" + jsonSolicitacao.id);
            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#modalProfessor").val(jsonSolicitacao.pessoa.shownName);
            $("#modalCurso").val(jsonSolicitacao.turma + " de " + jsonSolicitacao.curso.modalidade + " em " + jsonSolicitacao.curso.nome);
            $("#modalDiaSemana").val(jsonSolicitacao.diaDaSemana);
            $("#modalQtdAlunos").val(jsonSolicitacao.qtdAlunos);
            $("#modalObservacao").val(jsonSolicitacao.observacao);
            $("#modalLaboratorio").val(jsonSolicitacao.lab.numero);
            $("#modalSoftware").val("");
            $("#modalModulo").val("");
            
            for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                var software = jsonSolicitacao.softwares[i].fabricante + " " + jsonSolicitacao.softwares[i].nome;
                software += (i == jsonSolicitacao.softwares.length - 1) ? "" : "\r\n";
                $("#modalSoftware").val($("#modalSoftware").val() + software);
            }
            
            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                var modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }
        }
    });
};

var showLaboratoriosDisponiveis = function () {
    var modulo = $("#modalModulo").val();
    var dia = $("#modalDiaSemana").val();
    $.ajax({
        url: '/JsonController?acao=laboratorios&modulo=' + modulo.replace(/[^0-9\.]/g, '') + '&dia=' + dia,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            $("#modalLabCombo").empty();
            $("#modalLabCombo").append($('<option>', {
                text: 'Selecione um Laboratório'
            }));

            var jsonLaboratorios = JSON.parse(e.responseText);

            for (i = 0; i < jsonLaboratorios.length + 1; i++) {
                $("#modalLabCombo").append($('<option>', {
                    value: jsonLaboratorios[i].id,
                    text: jsonLaboratorios[i].numero
                }));
            }
        }
    });
};

var aprovarReserva = function () {
    parameter = "&solicitacao=" + $("#modalIdSolicitacao").val() + "&laboratorio=" + $("#modalLabCombo").val();
    window.location.href = "/AlmightyController?acao=SolicitacaoAprovacao" + parameter;
};

var reprovarReserva = function () {
    window.location.href = "/AlmightyController?solicitacao_id=" + $("#modalIdSolicitacao").val() + "&acao=SolicitacaoRemocao";
};

var removerReserva = function () {
    window.location.href = "/AlmightyController?reserva_id=" + $("#modalIdSolicitacao").val() + "&acao=ReservaRemocao";
};

var accessControl = function (role) {
    if (role == "professor") {
        $('#form-soli-fixo').show();
    } else if (role == "coordenador") {
        $('#form-soli-coord').show();
    } else {
        $('#soli-menu').show();
        $('#notif-menu').show();
        $('#item-novo-curso').show();
        $('#item-novo-software').show();
        $('#item-novo-lab').show();
        $('#item-solicitacoes').show();
        $('#form-soli-func').show();
        $('#counters').show();
    }
};

$(document).ready(function () {
    $.ajax({
        url: '/CounterController?acao=padrao',
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
        url: '/CounterController?acao=padrao',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            runNotifications(e);
        }
    });
}, 10000);

var availableLabs = function (dia, modulo) {
    $.ajax({
        url: '/JsonController?acao=laboratorios&modulo=' + modulo + '&dia=' + dia,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            $("#laboratorio").empty();
            $("#laboratorio").append($('<option>', {
                text: 'Selecione um Laboratório'
            }));

            var jsonLaboratorios = JSON.parse(e.responseText);

            for (i = 0; i < jsonLaboratorios.length + 1; i++) {
                var labid = jsonLaboratorios[i].id;
                var labnum = jsonLaboratorios[i].numero;

                $("#laboratorio").append($('<option>', {
                    value: labid,
                    text: labnum
                }));
            }
        }
    });
};

$(document).ready(function () {
    $("#modulo").change(function () {
        var modulo = $("#modulo").val();
        var diaSemana = $("#dia-semana").val();

        if (modulo !== null && diaSemana !== null) {
            availableLabs(diaSemana, modulo);
        } else {
            $("#laboratorio").attr("placeholder",
                    "Nenhum resultado");
        }
    });

    $("#dia-semana").change(function () {
        var modulo = $("#modulo").val();
        var diaSemana = $("#dia-semana").val();

        if (modulo !== null && diaSemana !== null) {
            availableLabs(diaSemana, modulo);
        } else {
            $("#laboratorio").attr("placeholder",
                    "Nenhum resultado");
        }
    });
});