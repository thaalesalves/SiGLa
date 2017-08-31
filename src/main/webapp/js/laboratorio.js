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

function showLaboratoriosDisponiveis() {
    var modulo = $("#modalModulo").val();
    var dia = $("#modalDiaSemana").val();
    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=LaboratoriosDisponiveis&modulo=' + modulo.replace(/[^0-9\.]/g, '') + '&dia=' + dia,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);

            $("#modalLabCombo").empty();
            $("#modalLabCombo").append($('<option>', {
                text: 'Selecione um Laboratório'
            }));

            for (i = 0; i < obj.length + 1; i++) {
                $("#modalLabCombo").append($('<option>', {
                    value: obj[i].id,
                    text: obj[i].numero
                }));
            }
        }
    });
}

function availableLabs(dia, modulo) {
    $.ajax({
        url: contextPath + '/JsonControllerTest?acao=LaboratoriosDisponiveis&modulo=' + modulo + '&dia=' + dia,
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

            if (obj[0].length == 0) {
                $("#modalLabCombo").append($('<option>', {
                    text: 'Não há laboratórios disponíveis'
                }));
            } else {                
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

                for (i = 0; i < obj.length + 1; i++) {
                    $("#modalLabCombo").append($('<option>', {
                        value: obj[i].id,
                        text: obj[i].numero
                    }));
                }
            }
        }
    });
}