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

var addNotification = function (counter, obj) {
    $("#res-notif").append("<li><a href='#'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj.solicitacoes[counter].professor + " pendente.</a></li>");
};

var updateWidgets = function (obj) {
    $("#qtd-reservas").text(obj.counter[0].reservas);
    $("#qtd-reservas-hoje").text(obj.counter[0].reservas_hoje);
    $("#qtd-labs").text(obj.counter[0].laboratorios);
    $("#qtd-computadores").text(obj.counter[0].computadores);
};

var runNotifications = function (e) {
    var obj = e.responseText;
    obj = JSON.parse(obj);

    if (obj.counter[0].solicitacoes !== null) {
        $("#qtd-res").text(obj.counter[0].solicitacoes);
        switch (true) {
            case (obj.counter[0].solicitacoes === 1):
                $("#msg-res").text("Você tem " + obj.counter[0].solicitacoes + " solicitação pendente");
                break;
            case (obj.counter[0].solicitacoes > 1):
                $("#msg-res").text("Você tem " + obj.counter[0].solicitacoes + " solicitações pendentes");
                break;
            default:
                $("#msg-res").text("Você não tem solicitações pendentes");
                break;
        }
    }

    for (var i = 0; i < 5; i++) {
        addNotification(i, obj);
    }

    updateWidgets(obj);
};

$(document).ready(function() {
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
