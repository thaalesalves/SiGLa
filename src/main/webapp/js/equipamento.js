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
function carregaLabs() {
    $.ajax({
        url: contextPath + '/JsonController?acao=LaboratorioListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            $.each(obj, function (i, item) {
                $("#laboratorio").append($('<option>', {
                    value: obj[i].id,
                    text: obj[i].numero
                }));
            });
        }
    });
}

function carregaEquip() {
    $.ajax({
        url: contextPath + '/JsonController?acao=EquipamentoListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var cont = '<table id="tb-res" class="table table-bordered table-hover">';
            cont += '<thead><tr><th style="width: 1%;">#</th><th style="width: 1%;">NetBIOS</th>';
            cont += '<th style="width: 1%;">Laboratório</th><th style="width: 1%;">IP</th>';
            cont += '<th style="width: 2%;">MAC</th><th style="width: 2%;">Configuração</th>';
            cont += '<th style="width: 1%;">Status</th><th style="width: 1%;">Opções</th></tr></thead><tbody>';
            $.each(obj, function (i, item) {
                var status;
                if (obj[i].status == 1) {
                    status = "Em ordem";
                } else {
                    status = "Retirado";
                }

                cont += '<tr>';
                cont += '<td>' + obj[i].id + '</td>';
                cont += '<td>' + obj[i].nome + '</td>';
                cont += '<td>' + obj[i].lab.numero + '</td>';
                cont += '<td>' + obj[i].ip + '</td>';
                cont += '<td>' + obj[i].mac + '</td>';
                cont += '<td>' + obj[i].config + '</td>';
                cont += '<td>' + status + '</td>';
                cont += '<td class="center"><center><button type="button" class="btn btn-default fa fa-wrench" data-toggle="modal" data-target="#myModal" onclick="modalEquipamento(' + obj[i].id + ')"></button></center></td>';
                cont += '</tr>';
            });
            cont += '</tbody></table>';
            cont += '<script>$("#tb-res").DataTable();</script>';
            $('#tb-div').append(cont);
        }
    });
}

function equipLabs() {
    $.ajax({
        url: contextPath + '/JsonController?acao=LaboratorioListagem',
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            console.log('Laboratório: ' + obj[0].numero);
            $.each(obj, function (i, item) {
                $("#laboratorio").append($('<option>', {
                    value: obj[i].id,
                    text: obj[i].numero
                }));
            });
        }
    });
}

function modalEquipamento(id) {
    $.ajax({
        url: contextPath + '/JsonController?acao=EquipamentoId&id=' + id,
        type: 'POST',
        cache: false,
        dataType: 'JSON',
        complete: function (e) {
            var obj = JSON.parse(e.responseText);
            var status;

            if (obj.status == 1) {
                status = "Em ordem.";
                $("#modal-footer").empty();
                $("#modal-footer").append('<button id="btnModalRetirar" type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalRetirar">Retirar</button>');
                $("#motivo-retirar").show();
                $("#motivo-retirado").hide();
                $("#data-retirado-div").hide();
            } else {
                status = "Retirado";
                $("#modal-footer").empty();
                $("#modal-footer").append('<button name="acao" value="EquipamentoDevolucao" id="btnModalDevolver" type="submit" class="btn btn-success">Devolver</button>');
                $("#motivo-retirado").show();
                $("#motivo-retirar").hide();
                $("data-retirado-div").show();
            }

            $("#equip-netbios").val(obj.nome);
            $("#equip-netbios-retirar").val(obj.nome);
            $("#equip-status").val(status);
            $("#equip-ip").val(obj.ip);
            $("#equip-mac").val(obj.mac);
            $("#motivo-retirado-campo").val(obj.motivo);
            $("#equip-id-retirar").val(obj.id);
            $("#equip-id").val(obj.id);
            $("#data-retirado-campo").val(obj.dataRetirada);
        }
    });
}
