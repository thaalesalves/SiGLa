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
package dao.sgbd;

import dao.DAOFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import model.Incidente;
import model.Equipamento;
import org.apache.commons.lang3.time.DateUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import util.Logger;

public class IncidenteDAOTest {

    DAOFactory dao = DAOFactory.getFactory();

    @Test
    public void assertIncidenteReturns() {
        try {
            List<Incidente> in = new ArrayList<Incidente>();
            in = dao.getIncidenteDAO().select();
            assertTrue(in.size() > 0);
        } catch (Exception e) {
            Logger.logWarning(e, IncidenteDAOTest.class);
        }
    }

    @Test
    public void assertIncidenteReturnsItem() {
        try {
            Incidente in = new Incidente();
            in.setId(1);
            in = dao.getIncidenteDAO().select(in);
            assertEquals("Lento", in.getDescricao());
        } catch (Exception e) {
            Logger.logWarning(e, IncidenteDAOTest.class);
        }
    }
    
    @Test
    public void assertIncidenteRetirada() {
        try {
            Incidente in = new Incidente();
            Equipamento eq = new Equipamento();
            eq.setId(1);
            eq = dao.getEquipamentoDAO().select(eq);
            in.setEquipamento(eq);
            in.setId(1);
            in = dao.getIncidenteDAO().select(in);
            in.setResolucao("Formatar");
            in.setDataDevolucao(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
            dao.getIncidenteDAO().devolver(in);
            in = null;
            in = dao.getIncidenteDAO().select(in);
            assertEquals("Formatar", in.getResolucao());
        } catch (Exception e) {
            Logger.logWarning(e, IncidenteDAOTest.class);
        }
    }
}
