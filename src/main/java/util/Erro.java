/*
 * Copyright (C) 2018 thales
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
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Erro {

    private String erro;
    private String data;
    
    public Erro() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Date date = new Date();
        data = dateFormat.format(date);
    }
    
    public void valorIlegal() {
        this.erro = "Tentativa ilegal de passar valores.";
    }
    
    public void erroProcesso() {
        this.erro = "Houve um erro ao processar as informações";
    }
}
