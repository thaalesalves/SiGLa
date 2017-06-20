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
package regra;

import dao.LaboratorioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Laboratorio;
import model.Modulo;
import model.Reserva;

/**
 *
 * @author thalespereira
 */
public class TesteLogica {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NullPointerException {
        Reserva r = new Reserva();
        LaboratorioDAO ldao = new LaboratorioDAO();
        String[] modulos = {"1", "2", "3"};
        r.setDiaDaSemana("Segunda-feira");
        
        for (String i : modulos) {
            Modulo mod = new Modulo();
            mod.setId(Integer.parseInt(i));

            r.getModulos().add(mod);
        }

        ArrayList<Laboratorio> al = ldao.selectLaboratorios();
        ArrayList<Laboratorio> aa = ldao.selectReservedLabs(r);

        for (Laboratorio i : al) {     
            boolean isReserved = false;
            
            for (Laboratorio j : aa) {
                if (j.getId() == (i.getId())) {
                    isReserved = true;
                }
            }
            
            if (isReserved) { al.remove(al.indexOf(i)); }
        }                

        System.out.println("\n\n====================\n\n");
        
        for (Laboratorio i : al) {            
            System.out.println("O índice da " + i.getNumero() + " é " + al.indexOf(i) + ", e nós temos " + al.size() + " índices neste arranjo.");
        }
    }  
}
