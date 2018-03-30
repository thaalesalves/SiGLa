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
        url: contextPath + '/JsonController?acao=SoftwareListagem',
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

function carregaLicencas() {
    console.log('Carregando licenças');
    $.ajax({
        url: contextPath + '/JsonController?acao=LicencaListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-licencas" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th>';
            cont += '<th>Software</th><th>Aquisição</th><th>Vencimento</th><th style="width: 3%;">Opções';
            cont += '</th></tr></thead><tbody>';
            console.log("Montada tabela");

            $.each(obj, function (i, item) {
                cont += '<tr class="gradeC">';
                cont += '<td class="center">' + obj[i].id + '</td>';
                cont += '<td class="center">' + obj[i].software.fabricante + ' ' + obj[i].software.nome + '</td>';
                cont += '<td class="center">' + DateFormat.format.date(new Date(obj[i].dataAquisicao), "dd/MM/yyyy") + '</td>';
                cont += '<td class="center">' + DateFormat.format.date(new Date(obj[i].dataVencimento), "dd/MM/yyyy") + '</td>';
                //cont += '<td class="center"><a href="" class="fa fa-wrench"></a><span>&#32; &#32; &#32;</span><a href="' + contextPath + '/AlmightyController?acao=CursoRemocao&curso_id=' + obj[i].id + '" class="fa fa-close"></a></td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#modal-licenca" onclick="modalLicenca(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
                console.log("Adicionada licença #" + obj[i].id);
            });

            cont += '</tbody></table>';
            cont += '<script>$("#tb-licencas").DataTable();</script>';
            $('#tb-div').append(cont);
            console.log('Objeto montado na div');
        }
    });
}

function modalLicenca(id) {
    $.ajax({
        url: contextPath + '/JsonController?acao=LicencaListagemId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            $('#lista-codigos').html("");
            var json = JSON.parse(e.responseText);
            var table = '<tr><td><div class="form-group"><label class="soli-tit">Códigos <a data-toggle="modal" data-target="#modal-codigo" style="font-size:8px;">(adicionar)</a></label></div></td></tr>';

            $("#licenca-id").val(json.id);
            $("#codigo-licenca").val(json.id);
            $("#data-aquisicao").val(DateFormat.format.date(new Date(json.dataAquisicao), "dd/MM/yyyy"));
            $("#data-vencimento").val(DateFormat.format.date(new Date(json.dataAquisicao), "dd/MM/yyyy"));
            $("#licenca-software").val(json.software.fabricante + " " + json.software.nome);
            
            if (json.status == '1') {
                $('#btn-desativar').show();
            } else {
                $('#btn-ativar').show();
            }

            var codigos = "";
            for (i = 0; i < json.codigos.length; i++) {
                codigos += '<tr><td><div class="form-group">' +
                        '<label class="soli-tit">' + json.codigos[i].codigoTipo + '</label></div></td><td style="width:100%;"><div class="form-group">' +
                        '<input readonly value="' + json.codigos[i].codigo + '" style="width: 80%;" type="text" class="form-control pull-right" name="licenca-software" id="licenca-software" placeholder="Código" />' +
                        '</div></td></tr>';
            }
            $('#lista-codigos').append(table + codigos);
        }
    });
}

function removerLicenca() {
    window.location.href = contextPath + "/AlmightyController?acao=LicencaRemocao&id=" + $("#licenca-id").val();
}

function ativarLicenca() {
    window.location.href = contextPath + "/AlmightyController?acao=LicencaAtivacao&id=" + $("#licenca-id").val();
}

function desativarLicenca() {
    window.location.href = contextPath + "/AlmightyController?acao=LicencaDesativacao&id=" + $("#licenca-id").val();
}

function adicionarLicenca() {
    $.ajax({
                url: contextPath + '/JsonController?acao=SoftwareListagem',
                type: 'POST',
                cache: false,
                dataType: 'JSON',
                complete: function (e) {
                    var obj = JSON.parse(e.responseText);

                    for (i = 0; i < obj.length; i++) {
                        $("#licenca-software").append($('<option>', {
                            value: obj[i].id,
                            text: obj[i].fabricante + ' ' + obj[i].nome
                        }));
                    }
                }
            });
}

function adicionarCodigo() {
    window.location.href = contextPath + "/AlmightyController?acao=CodigoAdicao&id=" + $("#licenca-id").val();
}