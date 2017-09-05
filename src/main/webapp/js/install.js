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
var formInstalacao;

function notify(msg, status, title) {
    if (msg != "null") {
        new PNotify({
            title: title,
            text: msg,
            type: status,
            addclass: 'stack-bottomright',
            animate: {
                animate: true,
                in_class: 'slideInUp',
                out_class: 'slideOutDown'
            }
        });
    }
}

function enviar() {
    formInstalacao = $('#install-form').serialize();

    loadPage();

    $.ajax({
        url: contextPath + 'AlmightyController?acao=Configuration&op=install&' + formInstalacao,
        type: 'POST',
        cache: false,
        error: function (xhr, ajaxOptions, thrownError) {
            throw xhr.status + ': ' + thrownError;
            $('body').waitMe('hide');
            notify("error", "Erro ao configurar o SiGLa", "Erro!");
        },
        success: function (e) {
            $('body').waitMe('hide');
            $('#div-assistente').hide();
            $('#div-sucesso').show();
            swal("SiGLa pronto para usar!", "Você será redirecionado em 5 segundos", "success");
            
            setTimeout(function(){ 
                window.location.reload(); 
            }, 5000);
        }
    });
}
