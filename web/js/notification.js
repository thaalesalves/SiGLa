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
    $("#res-notif").append("<li><a href='#'><i class='fa fa-users text-aqua'></i>  Solicitação de " + obj[counter].prof + " pendente.</a></li>");
};

var convertToValid = function (data) {
    data.articleList.forEach(function (article) {
        article.introduction = String(article.introduction)
                .replace(/<\/?[^>]+>/gi, '');
    });
    return data;
}

setInterval(function () {
    $("#res-notif").empty();
    $.ajax({
        url: '/SiGLa/CounterController?acao=reserva',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = e.responseText;
            obj = "[" + obj + "]";
            obj = JSON.parse(obj);
            if (obj[0].count !== null) {
                $("#qtd-res").text(obj[0].count);
                switch (true) {
                    case (obj[0].count === 1):
                        $("#msg-res").text("Você tem " + obj[0].count + " solicitação pendente");
                        break;
                    case (obj[0].count > 1):
                        $("#msg-res").text("Você tem " + obj[0].count + " solicitações pendentes");
                        break;
                    default:
                        $("#msg-res").text("Você não tem solicitações pendentes");
                        break;
                }
            }

            for (var i = 1; i < obj.length; i++) {
                addNotification(i, obj);
            }
        }
    });
}, 100);
$(document).ready(function () {
    $.ajax({
        url: '/SiGLa/CounterController?acao=reserva',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = e.responseText;
            obj = "[" + obj + "]";
            obj = JSON.parse(obj);
            for (var i = 1; i < obj.length; i++) {
                addNotification(i, obj);
            }
        }
    });
});

$(document).ready(function() {
    $.ajax({
        url: '/SiGLa/CounterController?acao=teste3',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = e.responseText;
            obj = JSON.parse(obj);
            console.log("Solicitações: " + obj.counter[0].solicitacoes);
            console.log("Reservas: " + obj.counter[0].reservas);
            console.log("Reservas hoje: " + obj.counter[0].reservas_hoje);
            console.log("Laboratórios: " + obj.counter[0].laboratorios);
            console.log("Computadores: "  + obj.counter[0].computadores);
        }
    });
});