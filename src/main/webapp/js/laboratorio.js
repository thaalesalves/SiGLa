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

function removeLab(id) {
    window.location.href = contextPath + '/controller?acao=LaboratorioRemocao&id=' + id;
}

function populaLabs() {
    $.ajax({
        url: contextPath + '/api?acao=LaboratorioListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-lab" class="table table-bordered table-hover">';

            if (acesso === 'admin' || acesso === 'funcionario') {
                cont += '<thead><tr><th style="width: 1%;">#</th>';
                cont += '<th>Número</th><th>Qtd. de Computadores</th><th>Capacidade de Alunos</th><th>Softwares</th><th style="width: 7%;">Opções';
                cont += '</th></tr></thead><tbody>';

                $.each(obj, function (i, item) {
                    cont += '<tr class="gradeC">';
                    cont += '<td class="center">' + obj[i].id + '</td>';
                    cont += '<td class="center">' + obj[i].numero + '</td>';
                    cont += '<td class="center">' + obj[i].computadores + '</td>';
                    cont += '<td class="center">' + obj[i].capacidade + '</td>';
                    cont += '<td class="center">';
                    for (var j = 0; j < obj[i].softwares.length; j++) {
                        cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                        cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                    }
                    cont += '</td>';
                    cont += '<td class="center"><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#modalLab" onclick="modalLab(' + obj[i].id + ')"></button><span>&#32; &#32; &#32;' +
                            '</span><button type="submit" class="btn btn-default fa fa-close" onclick="removeLab(' + obj[i].id + ')"></button></td>';
                    cont += '</tr>';
                });
            } else {
                cont += '<thead><tr><th style="width: 1%;">#</th>';
                cont += '<th>Número</th><th>Qtd. de Computadores</th><th>Capacidade de Alunos</th><th>Softwares</th></tr></thead><tbody>';

                $.each(obj, function (i, item) {
                    cont += '<tr class="gradeC">';
                    cont += '<td class="center">' + obj[i].id + '</td>';
                    cont += '<td class="center">' + obj[i].numero + '</td>';
                    cont += '<td class="center">' + obj[i].computadores + '</td>';
                    cont += '<td class="center">' + obj[i].capacidade + '</td>';
                    cont += '<td class="center">';
                    for (var j = 0; j < obj[i].softwares.length; j++) {
                        cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                        cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                    }
                    cont += '</td></tr>';
                });
            }

            cont += '</tbody></table>';
            cont += '<script>$("#tb-lab").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function populaSolicitacaoLabs(modulo, dia, software, qtd) {
    $.ajax({
        url: contextPath + '/api?acao=LaboratoriosDisponiveis&modulo=' + modulo + '&dia=' + dia + '&softwares=' + software + '&qtd-alunos=' + qtd,
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
                $("#laboratorio").append($('<option>', {
                    value: obj[i].id,
                    text: obj[i].numero
                }));
            }
        }
    });
}

function modalLab(id) {
    $.ajax({
        url: contextPath + '/api?acao=SoftwareListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            $("#modal_sw_lab").empty();
            var obj = JSON.parse(e.responseText);
            for (i = 0; i < obj.length; i++) {
                $("#modal_sw_lab").append($('<option>', {
                    value: obj[i].id,
                    text: obj[i].fabricante + " " + obj[i].nome
                }));
            }
        }
    });

    $.ajax({
        url: contextPath + '/api?acao=LaboratorioId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            $("#modal_id_lab").val(obj.id);
            $("#modal_num_lab").val(obj.numero);
            $("#modal_cap_lab").val(obj.capacidade);
            $("#modal_comps_lab").val(obj.computadores);
            for (i = 0; i < obj.softwares.length + 1; i++) {
                $("#modal_sw_lab > option").each(function () {
                    if (this.value.trim() == obj.softwares[i].id) {
                        $(this).attr("selected", "selected");
                    }
                });
            }
        }
    });
}