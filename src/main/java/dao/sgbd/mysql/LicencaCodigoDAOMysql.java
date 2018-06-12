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
package dao.sgbd.mysql;

import dao.sgbd.LicencaCodigoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Licenca;
import model.LicencaCodigo;
import util.DatabaseConnection;
import util.Logger;

public class LicencaCodigoDAOMysql implements LicencaCodigoDAO {

    @Override
    public List<LicencaCodigo> select() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LicencaCodigo select(LicencaCodigo codigo) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(LicencaCodigo codigo) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tb_licenca_codigo VALUES(DEFAULT, ?, ?, ?)");
            pstmt.setString(1, codigo.getCodigoTipo());
            pstmt.setString(2, codigo.getCodigo());
            pstmt.setInt(3, codigo.getLicenca().getId());
            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaCodigoDAOMysql.class);
        }
    }

    @Override
    public LicencaCodigo selectLicenca(Licenca licenca) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LicencaCodigo> select(Licenca licenca) throws SQLException, ClassNotFoundException {
        List<LicencaCodigo> codigos = new ArrayList<LicencaCodigo>();

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_licenca_codigo WHERE licenca = ?");
            pstmt.setInt(1, licenca.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                LicencaCodigo codigo = new LicencaCodigo();

                codigo.setId(rs.getInt("id"));
                 codigo.setCodigoTipo(rs.getString("codigo_tipo"));
                codigo.setCodigo(rs.getString("codigo"));

                codigos.add(codigo);
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaCodigoDAOMysql.class);
        }

        return codigos;
    }

}
