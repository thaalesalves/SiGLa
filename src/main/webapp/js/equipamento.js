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

var reservas;
var contextPath;

function carrega() {
    $.ajax({
        url: contextPath + '/JsonController?acao=padrao',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            reservas = JSON.parse(e.responseText);
            var trHTML = '';
            $.each(reservas.softwares, function (i, item) {
                trHTML += '<tr><td>' + reservas.softwares[i].fabricante + '</td><td>' + reservas.softwares[i].nome + '</td>';
                trHTML += "<td>TESTE</tr></td>";
            });

            $('#teste').append(trHTML);
        }
    });
}
