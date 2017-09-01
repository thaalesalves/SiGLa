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
function carregaLabs() {
    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=LaboratorioListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-lab" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th>';
            cont += '<th>Número</th><th>Qtd. de Computadores</th><th>Capacidade de Alunos</th><th style="width: 3%;">Opções';
            cont += '</th></tr></thead><tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr class="gradeC">';
                cont += '<td class="center">' + obj[i].id + '</td>';
                cont += '<td class="center">' + obj[i].numero + '</td>';
                cont += '<td class="center">' + obj[i].computadores + '</td>';
                cont += '<td class="center">' + obj[i].capacidade + '</td>';
                cont += '<td class="center"><a href="" class="fa fa-wrench"></a><span>&#32; &#32; &#32;</span><a href="' + contextPath + '/AlmightyController?acao=LaboratorioRemocao&curso_id=' + obj[i].id + '" class="fa fa-close"></a></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-lab").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function solicitacaoLabs(modulo, dia) {
    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=LaboratoriosDisponiveis&modulo=' + modulo + '&dia=' + dia,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);

            $("#laboratorio").empty();

            $("#laboratorio").append($('<option>', {
                text: 'Selecione um Laboratório'
            }));

            for (i = 0; i < obj.length; i++) {
                var labid = obj[i].id;
                var labnum = obj[i].numero;

                $("#laboratorio").append($('<option>', {
                    value: labid,
                    text: labnum
                }));
            }
        }
    });
}

function modalLabs() {
    var modulo = $("#modalModulo").val().replace(/[^0-9\.]/g, '').split('');
    var dia = $("#modalDiaSemana").val();

    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=LaboratoriosDisponiveis&modulo=' + modulo + '&dia=' + dia,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);

            $("#modalLabCombo").empty();

            if (obj[0].length == 0) {
                $("#modalLabCombo").append($('<option>', {
                    text: 'Não há laboratórios disponíveis'
                }));
            } else {
                $("#modalLabCombo").append($('<option>', {
                    text: 'Selecione um Laboratório'
                }));

                for (i = 0; i < obj.length; i++) {
                    $("#modalLabCombo").append($('<option>', {
                        value: obj[i].id,
                        text: obj[i].numero
                    }));
                }
            }
        }
    });
}