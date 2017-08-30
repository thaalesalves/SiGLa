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

var acesso;
var contextPath;

function formFuncionario() {
    $('#lab-field').show();
    $('#user-select').show();
}

function formProfessor() {
    $('#user-fixo').show();
}

function formCoordenador() {
    $('#user-select').show();
}

function carregaReservas() {
    $.ajax({
        url: contextPath + '/ReservaController?acao=lista-tudo',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr>';
                cont += '<td>' + obj[i].id + '</td>';
                cont += '<td>' + obj[i].pessoa.shownName + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].modulos.length; j++) {
                    cont += obj[i].modulos[j].id + 'º módulo';
                    cont += (i == obj[i].modulos.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td>' + obj[i].turma + ' de ' + obj[i].curso.modalidade + ' em ' + obj[i].curso.nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>' + obj[i].diaDaSemana + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="showReservaModal(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody>';
            $('#example1').append(cont);
        }
    });
}

function carregaReservasProfessor() {
    $.ajax({
        url: contextPath + '/ReservaController?acao=prof-tudo',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr>';
                cont += '<td>' + obj[i].id + '</td>';
                cont += '<td>' + obj[i].pessoa.shownName + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].modulos.length; j++) {
                    cont += obj[i].modulos[j].id + 'º módulo';
                    cont += (i == obj[i].modulos.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td>' + obj[i].turma + ' de ' + obj[i].curso.modalidade + ' em ' + obj[i].curso.nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>' + obj[i].diaDaSemana + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="showReservaModal(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody>';
            $('#example1').append(cont);
        }
    });
}

function carregaReservasDia() {
    $.ajax({
        url: contextPath + '/ReservaController?acao=lista-hoje',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr>';
                cont += '<td>' + obj[i].id + '</td>';
                cont += '<td>' + obj[i].pessoa.shownName + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].modulos.length; j++) {
                    cont += obj[i].modulos[j].id + 'º módulo';
                    cont += (i == obj[i].modulos.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td>' + obj[i].turma + ' de ' + obj[i].curso.modalidade + ' em ' + obj[i].curso.nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="showReservaModal(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody>';
            $('#example1').append(cont);
        }
    });
}

function carregaReservasDiaProfessor() {
    $.ajax({
        url: contextPath + '/ReservaController?acao=prof-hoje',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<tbody>';

            $.each(obj, function (i, item) {
                cont += '<tr>';
                cont += '<td>' + obj[i].id + '</td>';
                cont += '<td>' + obj[i].pessoa.shownName + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].modulos.length; j++) {
                    cont += obj[i].modulos[j].id + 'º módulo';
                    cont += (i == obj[i].modulos.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td>' + obj[i].turma + ' de ' + obj[i].curso.modalidade + ' em ' + obj[i].curso.nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="showReservaModal(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody>';
            $('#example1').append(cont);
        }
    });
}