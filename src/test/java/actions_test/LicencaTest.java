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
package actions_test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Licenca;
import model.LicencaCodigo;
import model.Software;
import util.DatabaseConnection;
import util.IO;
import util.Logger;

/**
 *
 * @author thales
 */
public class LicencaTest {
    public static class LicencaDAO {

        public static Software selectLicenca(Software software) throws SQLException, ClassNotFoundException {
            Licenca licenca = new Licenca();
            LicencaCodigo licencaCodigo = new LicencaCodigo();

            try (Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT l.id AS licenca, sw.nome AS software, "
                        + "sw.fabricante AS fabricante, l.aquisicao AS data_aquisicao, l.vencimento AS data_vencimento "
                        + "FROM tb_software sw, tb_licenca l, tb_licenca_codigo lc "
                        + "WHERE l.software = sw.id AND sw.id = ?;");
                pstmt.setInt(1, software.getId());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    licenca.setId(rs.getInt("licenca"));
                    licenca.setDataAquisicao(IO.getData(rs.getString("data_aquisicao")));
                    licenca.setDataVencimento(IO.getData(rs.getString("data_vencimento")));
                    licenca.setCodigos(new ArrayList<LicencaCodigo>());
                    software.setFabricante(rs.getString("fabricante"));
                    software.setNome(rs.getString("software"));
                }

                pstmt = conn.prepareStatement("SELECT lc.* FROM tb_licenca_codigo lc, tb_licenca l "
                        + "WHERE lc.licenca = l.id AND l.id = ?");
                pstmt.setInt(1, licenca.getId());
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    licencaCodigo.setId(rs.getInt("id"));
                    licencaCodigo.setCodigo(rs.getString("codigo"));
                    licencaCodigo.setCodigoTipo(rs.getString("nome"));
                    licenca.getCodigos().add(licencaCodigo);
                }

                software.setLicenca(licenca);

                conn.close();
            } catch (Exception e) {
                Logger.logSevere(e, LicencaDAO.class);
            }

            return software;
        }
    }
}
