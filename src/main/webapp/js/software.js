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

function carregaSoftware() {
    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=SoftwareListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-software" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th>';
            cont += '<th>Fabricante</th><th>Software</th><th style="width: 3%;">Opções';
            cont += '</th></tr></thead><tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr class="gradeC">';
                cont += '<td class="center">' + obj[i].id + '</td>';
                cont += '<td class="center">' + obj[i].fabricante + '</td>';
                cont += '<td class="center">' + obj[i].nome + '</td>';
                cont += '<td class="center"><a href="" class="fa fa-wrench"></a><span>&#32; &#32; &#32;</span><a href="' + contextPath + '/AlmightyController?acao=CursoRemocao&curso_id=' + obj[i].id + '" class="fa fa-close"></a></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-software").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}