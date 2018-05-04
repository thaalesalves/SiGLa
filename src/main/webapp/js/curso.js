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

function carregaCurso() {
    $.ajax({
        url: contextPath + '/JsonController?acao=CursoListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-curso" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th>';
            cont += '<th>Modalidade</th><th>Curso</th><th style="width: 3%;">Opções';
            cont += '</th></tr></thead><tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr class="gradeC">';
                cont += '<td style="width: 1%;" class="center">' + obj[i].id + '</td>';
                cont += '<td class="center">' + obj[i].modalidade + '</td>';
                cont += '<td class="center">' + obj[i].nome + '</td>';
                cont += '<td class="center"><span>&#32; &#32; &#32;</span><a href="' + contextPath + '/AlmightyController?acao=CursoRemocao&curso_id=' + obj[i].id + '" class="btn btn-default fa fa-close"></a></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-curso").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}