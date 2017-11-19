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

function atualizarReserva() {
    loadPage();

    $.ajax({
        url: contextPath + '/AlmightyController?acao=ReservaAtualizacao&professor=' + $("#modalProfessoresCombo").val() + '&dia=' + $("#modalDiaCombo").val() + '&modulo=' + $("#modalModuloCombo").val() + '&lab=' + $("#modalLabCombo").val(),
        type: 'POST',
        cache: false,
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            $('body').waitMe('hide');
            notify("Erro ao salvar as configurações!", "success");
        },
        success: function (e) {
            $('body').waitMe('hide');
            notify("Os dados da reserva foram atualizados!", "success");
        }
    });
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
                cont += '<td><a href="#" onclick="getUser(\'' + obj[i].pessoa.username + '\'); return false;"  data-toggle="modal" data-target="#modalPerfil">' + obj[i].pessoa.shownName + '</a></td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].modulos.length; j++) {
                    cont += obj[i].modulos[j].id + 'º módulo<br>';
                    cont += (i == obj[i].modulos.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td>' + obj[i].turma + ' de ' + obj[i].curso.modalidade + ' em ' + obj[i].curso.nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>' + obj[i].diaDaSemana + '</td>';
                cont += '<td>';
                for (var j = 0; j < obj[i].softwares.length; j++) {
                    cont += obj[i].softwares[j].fabricante + " " + obj[i].softwares[j].nome + '<br>';
                    cont += (i == obj[i].softwares.length - 1) ? "" : "<br>";
                }
                cont += '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
                //cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target=".modalReserva" onclick="modalReserva(' + obj[i].id + ')"></button></center></td>';
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
                cont += '<td><a href="#" onclick="getUser(\'' + obj[i].pessoa.username + '\'); return false;"  data-toggle="modal" data-target="#modalPerfil">' + obj[i].pessoa.shownName + '</a></td>';
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
                cont += '<td><a href="#" onclick="getUser(\'' + obj[i].pessoa.username + '\'); return false;"  data-toggle="modal" data-target="#modalPerfil">' + obj[i].pessoa.shownName + '</a></td>';
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
                cont += '<td><a href="#" onclick="getUser(\'' + obj[i].pessoa.username + '\'); return false;"  data-toggle="modal" data-target="#modalPerfil">' + obj[i].pessoa.shownName + '</a></td>';
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

function modalLaboratorio(dia, modulo, softwares) {
    $.ajax({
        url: contextPath + '/JsonController?acao=LaboratoriosDisponiveis&modulo=' + modulo.toString().replace(/[^0-9\.]/g, '').split('') + '&dia=' + dia + '&softwares=' + softwares.toString().replace(/[^0-9\.]/g, '').split(''),
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);

            $("#modalLabCombo").empty();
            $("#modalLabCombo").append($('<option>', {
                value: labId,
                text: labAtual
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
            $("#td_soli_mod").show();
            $("#td_res_mod").hide();
            $("#btnModalAprovar").show();
            $("#btnModalAtualizar").hide();
            $("#btnModalReprovar").show();
            $("#btnModalExcluir").hide();
            $(".soli-tit").show();
            $(".res-tit").hide();
            $("#modalLabCombo").hide();
            $("#modalLaboratorio").hide();
            $('#td_res_dia').hide();
            $('#td_soli_dia').show();
            $("#td-prof-combo").hide();
            $("#td-prof-txt").show();
            $("#td_res_sw").hide();
            $("#td_soli_sw").show();

            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#modalProfessor").val(jsonSolicitacao.pessoa.shownName);
            $("#modalCurso").val(jsonSolicitacao.turma + " de " + jsonSolicitacao.curso.modalidade + " em " + jsonSolicitacao.curso.nome);
            $("#modalDiaSemana").val(dia);
            $("#modalQtdAlunos").val(jsonSolicitacao.qtdAlunos);
            $("#modalObservacao").val(jsonSolicitacao.observacao);
            $("#modalSoftware").val("");
            $("#modalModulo").val("");

            var softwares = "";
            var modulos = "";
            for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                softwares += jsonSolicitacao.softwares[i].id;
                var software = jsonSolicitacao.softwares[i].fabricante + " " + jsonSolicitacao.softwares[i].nome;
                software += (i == jsonSolicitacao.softwares.length - 1) ? "" : "\r\n";
                $("#modalSoftware").val($("#modalSoftware").val() + software);
            }

            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                modulos += jsonSolicitacao.modulos[i].id;
                modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }

            softwares = softwares.trim();
            softwares = softwares.split('');
            modulos = modulos.trim();
            modulos = modulos.split('');

            $.ajax({
                url: contextPath + '/JsonController?acao=LaboratoriosDisponiveis&modulo=' + modulos + '&dia=' + dia + '&softwares=' + softwares,
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    var obj = JSON.parse(e.responseText);

                    $("#modalLabCombo").empty();
                    $("#modalLabCombo").append($('<option>', {
                        value: labId,
                        text: labAtual
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

var labAtual;
var labId;
function modalReserva(id) {
    $.ajax({
        url: contextPath + '/JsonController?acao=ReservaId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var jsonSolicitacao = JSON.parse(e.responseText);
            labId = jsonSolicitacao.lab.id;
            labAtual = jsonSolicitacao.lab.numero + ' (atual)';

            $("#modalLabCombo").empty();
            $("#modalDiaCombo").empty();

            $("#modalLabCombo").append($('<option>', {
                value: labId,
                text: labAtual
            }));

            $("#td_res_sw").show();
            $("#td_soli_sw").hide();
            $("#td_res_mod").show();
            $("#td_soli_mod").hide();
            $("#btnModalAtualizar").show();
            $("#btnModalAprovar").hide();
            $("#btnModalReprovar").hide();
            $("#btnModalExcluir").show();
            $(".soli-tit").hide();
            $(".res-tit").show();
            $('#td_res_dia').show();
            $('#td_soli_dia').hide();
            $("#td-prof-combo").show();
            $("#td-prof-txt").hide();

            $("#reserva-modal-titulo").html("Reserva nº" + jsonSolicitacao.id);
            $("#modalIdSolicitacao").val(jsonSolicitacao.id);
            $("#reserva-id").val($("#modalIdSolicitacao").val());
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

            $.ajax({
                url: contextPath + '/JsonController?acao=SoftwareListagem',
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    var obj = JSON.parse(e.responseText);
                    for (i = 0; i < obj.length; i++) {
                        $("#modalSoftwares").prepend($('<option>', {
                            value: obj[i].id,
                            text: obj[i].fabricante + " " + obj[i].nome
                        }));
                    }
                }
            });

            $("#modalSoftwares option").each(function () {
                for (i = 0; i < jsonSolicitacao.softwares.length; i++) {
                    var swId = jsonSolicitacao.softwares[i].id;
                    if ($(this).val().trim() === swId.toString()) {
                        $(this).attr("selected", "selected");
                    }
                }
            });

            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                var modulo = jsonSolicitacao.modulos[i].id + "º módulo";
                modulo += (i == jsonSolicitacao.modulos.length - 1) ? "" : "\r\n";
                $("#modalModulo").val($("#modalModulo").val() + modulo);
            }

            for (i = 0; i < jsonSolicitacao.modulos.length; i++) {
                $("#modalModuloCombo option").each(function () {
                    if ($(this).val() == jsonSolicitacao.modulos[i].id) {
                        $(this).attr("selected", "selected");
                    }
                });
            }

            var items = {
                option1: {value: 'Segunda-feira', text: 'Segunda-feira'},
                option2: {value: 'Terça-feira', text: 'Terça-feira'},
                option3: {value: 'Quarta-feira', text: 'Quarta-feira'},
                option4: {value: 'Quinta-feira', text: 'Quinta-feira'},
                option5: {value: 'Sexta-feira', text: 'Sexta-feira'},
                option6: {value: 'Sábado', text: 'Sábado'}
            };

            $.each(items, function (i, item) {
                $('#modalDiaCombo').append($('<option>', {
                    value: item.value,
                    text: item.text
                }));
            });

            $("#modalDiaCombo option").each(function () {
                if ($(this).val().trim() === jsonSolicitacao.diaDaSemana.trim()) {
                    $(this).remove();
                    $(this).val().replace(' (atual)', '');
                }
            });

            $("#modalDiaCombo").prepend($('<option>', {
                value: jsonSolicitacao.diaDaSemana,
                text: jsonSolicitacao.diaDaSemana + ' (atual)'
            }));

            $("#modalDiaCombo option").each(function () {
                if ($(this).val() == jsonSolicitacao.diaDaSemana) {
                    $(this).attr("selected", "selected");
                }
            });

            $("#modalProfessoresCombo option").each(function () {
                if ($(this).val().trim() === jsonSolicitacao.pessoa.username.trim()) {
                    $(this).remove();
                }
            });

            $("#modalProfessoresCombo").prepend($('<option>', {
                value: jsonSolicitacao.pessoa.username,
                text: jsonSolicitacao.pessoa.shownName + ' (' + jsonSolicitacao.pessoa.email + ')'
            }));

            $("#modalProfessoresCombo option").each(function () {
                if ($(this).val().trim() === jsonSolicitacao.pessoa.username.trim()) {
                    $(this).attr("selected", "selected");
                }
            });

            $.ajax({
                url: contextPath + '/JsonController?acao=LaboratoriosDisponiveis&modulo=' + $("#modalModulo").val().toString().replace(/[^0-9\.]/g, '').split('') + '&dia=' + jsonSolicitacao.diaDaSemana.replace('%C3%A7', 'ç') + '&softwares=' + $("#modalSoftwares").val().toString().replace(/[^0-9\.]/g, '').split(''),
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    var obj = JSON.parse(e.responseText);

                    $("#modalLabCombo").empty();
                    $("#modalLabCombo").append($('<option>', {
                        value: labId,
                        text: labAtual
                    }));

                    for (i = 0; i < obj.length; i++) {
                        $("#modalLabCombo").append($('<option>', {
                            value: obj[i].id,
                            text: obj[i].numero + ' (disponível)'
                        }));
                    }
                }
            });
        }
    });
}
