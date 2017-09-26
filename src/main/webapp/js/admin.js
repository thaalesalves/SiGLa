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

function toggleFieldAd(id) {
    $('#' + id).prop("readonly", false);

    $('#' + id).on("keypress", function (e) {
        if (e.keyCode == 13) {
            e.preventDefault();
            updateAd(id);
            $('#' + id).prop("readonly", true);
        }
    });
}

function updateAd(input) {
    var val = $('#' + input).val();
    var netbios = $('#netbios').val();
    loadPage();

    $.ajax({
        url: contextPath + '/AlmightyController?acao=ActiveDirectory&op=' + input + '&val=' + val + '&netbios=' + netbios,
        type: 'POST',
        cache: false,
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            $('body').waitMe('hide');
            notify("Erro ao salvar as configurações!", "success");
        },
        success: function (e) {
            $('body').waitMe('hide');
            notify("Configurações atualizadas!", "success");
        }
    });
}