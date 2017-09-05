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

function aprovarReserva() {
    parameter = "&solicitacao=" + $("#modalIdSolicitacao").val() + "&laboratorio=" + $("#modalLabCombo").val();
    window.location.href = contextPath + "/AlmightyController?acao=SolicitacaoAprovacao" + parameter;
}

function reprovarReserva() {
    window.location.href = contextPath + "/AlmightyController?solicitacao_id=" + $("#modalIdSolicitacao").val() + "&acao=SolicitacaoRemocao";
}

function removerReserva() {
    window.location.href = contextPath + "/AlmightyController?reserva_id=" + $("#modalIdSolicitacao").val() + "&acao=ReservaRemocao";
}

function carregaReservas() {
    $.ajax({
        url: contextPath + '/JsonController?acao=Reserva',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-res" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 10%;">Professor</th>';
            cont += '<th style="width: 1%;">Módulos</th><th>Turma</th><th style="width: 1%;">Laboratório</th>';
            cont += '<th style="width: 11%;">Dia da Semana</th><th style="width: 15%;">Softwares</th>';
            cont += '<th style="width: 3%;">Opções</th></tr></thead><tbody>';

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
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-res").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function carregaReservasProfessor() {
    $.ajax({
        url: contextPath + '/JsonController?acao=ReservaProfessor',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-res" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 10%;">Professor</th>';
            cont += '<th style="width: 1%;">Módulos</th><th>Turma</th><th style="width: 1%;">Laboratório</th>';
            cont += '<th style="width: 11%;">Dia da Semana</th><th style="width: 15%;">Softwares</th>';
            cont += '<th style="width: 3%;">Opções</th></tr></thead><tbody>';

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
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-res").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function carregaReservasDia() {
    $.ajax({
        url: contextPath + '/JsonController?acao=ReservaDia',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-res-dia" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 10%;">Professor</th>';
            cont += '<th style="width: 1%;">Módulos</th><th>Turma</th><th style="width: 15%;">Softwares</th>';
            cont += '<th style="width: 15%;">Softwares</th><th style="width: 3%;">Opções</th>';
            cont += '</tr></thead><tbody>';

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
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-res-dia").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function carregaReservasDiaProfessor() {
    $.ajax({
        url: contextPath + '/JsonController?acao=ReservaProfessorDia',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-res-dia" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 10%;">Professor</th>';
            cont += '<th style="width: 1%;">Módulos</th><th>Turma</th><th style="width: 15%;">Softwares</th>';
            cont += '<th style="width: 15%;">Softwares</th><th style="width: 3%;">Opções</th>';
            cont += '</tr></thead><tbody>';

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
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-res-dia").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function carregaSolicitacoes() {
    $.ajax({
        url: contextPath + '/JsonController?acao=Solicitacao',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            notify("error", "Erro ao carregar as reservas", "Erro!");
        },
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-solicitacao" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 10%;">Professor</th>';
            cont += '<th style="width: 1%;">Módulos</th><th>Turma</th><th style="width: 11%;">Dia da Semana</th>';
            cont += '<th style="width: 15%;">Softwares</th><th style="width: 3%;">Opções</th>';
            cont += '</tr></thead><tbody>';

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
                cont += '<td>' + obj[i].diaSemana + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome;
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalSolicitacao(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-solicitacao").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function modalSolicitacao(id) {
    var modulo;
    var dia;

    $.ajax({
        url: contextPath + '/JsonController?acao=SolicitacaoId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var jsonSolicitacao = JSON.parse(e.responseText);
            dia = jsonSolicitacao.diaSemana;

            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#modalProfessor").val(jsonSolicitacao.pessoa.shownName);
            $("#modalCurso").val(jsonSolicitacao.turma + " de " + jsonSolicitacao.curso.modalidade + " em " + jsonSolicitacao.curso.nome);
            $("#modalDiaSemana").val(dia);
            $("#modalQtdAlunos").val(jsonSolicitacao.qtdAlunos);
            $("#modalObservacao").val(jsonSolicitacao.observacao);
            $("#modalSoftware").val("");
            $("#modalModulo").val("");
            for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                var software = jsonSolicitacao.softwares[i].fabricante + " " + jsonSolicitacao.softwares[i].nome;
                software += (i == jsonSolicitacao.softwares.length - 1) ? "" : "\r\n";
                $("#modalSoftware").val($("#modalSoftware").val() + software);
            }

            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }

            $.ajax({
                url: contextPath + '/JsonController?acao=LaboratoriosDisponiveis&modulo=' + modulo.toString().replace(/[^0-9\.]/g, '').split('') + '&dia=' + dia,
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    var obj = JSON.parse(e.responseText);

                    $("#modalLabCombo").empty();

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
            });
        }
    });
}

function modalReserva(id) {
    $.ajax({
        url: contextPath + '/JsonController?acao=ReservaId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var jsonSolicitacao = JSON.parse(e.responseText);
            $("#reserva-modal-titulo").html("Reserva nº" + jsonSolicitacao.id);
            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#modalProfessor").val(jsonSolicitacao.pessoa.shownName);
            $("#modalCurso").val(jsonSolicitacao.turma + " de " + jsonSolicitacao.curso.modalidade + " em " + jsonSolicitacao.curso.nome);
            $("#modalDiaSemana").val(jsonSolicitacao.diaDaSemana);
            $("#modalQtdAlunos").val(jsonSolicitacao.qtdAlunos);
            $("#modalObservacao").val(jsonSolicitacao.observacao);
            $("#modalLaboratorio").val(jsonSolicitacao.lab.numero);
            $("#modalSoftware").val("");
            $("#modalModulo").val("");
            for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                var software = jsonSolicitacao.softwares[i].fabricante + " " + jsonSolicitacao.softwares[i].nome;
                software += (i == jsonSolicitacao.softwares.length - 1) ? "" : "\r\n";
                $("#modalSoftware").val($("#modalSoftware").val() + software);
            }

            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                var modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }
        }
    });
}