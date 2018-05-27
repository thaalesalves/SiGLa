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

var contextPath;

function getUser(username) {
    $.ajax({
        url: contextPath + '/api?acao=UsuarioId&username=' + username,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            $("#modal-perfil-nome-completo").html(obj.nomeCompleto);
            $("#modal-perfil-cargo-depto").html(obj.cargo + ' no ' + obj.depto);
            $("#modal-perfil-email").html(obj.email);
            $("#modal-perfil-depto").html(obj.depto);
            $("#modal-perfil-cargo").html(obj.cargo);
            $("#modal-perfil-empresa").html(obj.empresa);
            $("#modal-email-titulo").html("Contato direto com " + obj.nome);
            $("#modal-email-dest").val(obj.email);
            $("#modal-email-pag").val(window.location.href);
        }
    });
}