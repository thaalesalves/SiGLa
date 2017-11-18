/* 
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.
 
 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 
 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 
 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */

var jsonObject;
var id;
var dados;
var parameter;
var contextPath;

function updateWidgets(obj) {
    $("#qtd-reservas").text(obj.qtdReservas);
    $("#qtd-reservas-hoje").text(obj.qtdReservasHoje);
    $("#qtd-labs").text(obj.qtdLaboratorios);
    $("#qtd-computadores").text(obj.qtdComputadores);
}

function notify(msg, status, title) {
    if (msg != "null") {
        switch (status) {
            case "error":
                title = "Erro!";
                break;
            case "success":
                title = "Sucesso!";
                break;
            case "info":
                title = "Aviso!";
                break;
        }

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