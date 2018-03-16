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
package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import util.Logger;

@lombok.Getter
@lombok.Setter
public class Licenca {

    private Integer id;
    private String dataAquisicao;
    private String dataVencimento;
    private Software software;
    private List<LicencaCodigo> codigos;

    public boolean venceHoje(Date data) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataHoje = df.parse(df.format(cal.getTime()));
            
            if (data.equals(dataHoje)) {
                return true;
            }
        } catch (Exception e) {
            Logger.logSevere(e, Licenca.class);
        }
        
        return false;
    }
}
