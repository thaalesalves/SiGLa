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
package dao.sgbd.psql;

import dao.sgbd.LicencaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Licenca;
import model.LicencaCodigo;
import model.Software;
import util.DatabaseConnection;
import util.Logger;

public class LicencaDAOPsql implements LicencaDAO {

    @Override
    public Licenca select(Software software) throws SQLException, ClassNotFoundException {
        Licenca licenca = new Licenca();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_licenca WHERE software = ?");
            pstmt.setInt(1, software.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                licenca.setDataAquisicao(rs.getString("aquisicao"));
                licenca.setDataVencimento(rs.getString("vencimento"));
                software.setLicenca(licenca);
            }

            pstmt = conn.prepareStatement("SELECT * FROM tb_licenca_codigo WHERE licenca = ?");
            pstmt.setInt(1, software.getLicenca().getId());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LicencaCodigo codigo = new LicencaCodigo();
                software.getLicenca().setCodigos(new ArrayList<LicencaCodigo>());

                codigo.setId(rs.getInt("id"));
                codigo.setCodigoTipo(rs.getString("nome"));
                codigo.setCodigo(rs.getString("codigo"));
                software.getLicenca().getCodigos().add(codigo);
            }

            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaDAOPsql.class);
        }
        
        return licenca;
    }

    @Override
    public Licenca select(Licenca licenca) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Licenca> select() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Licenca> selectVencimento() throws SQLException, ClassNotFoundException {
        List<Licenca> licencas = new ArrayList<Licenca>();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_licenca WHERE vencimento = ?");
            pstmt.setString(1, new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Licenca licenca = new Licenca();
                licenca.setSoftware(new Software());
                licenca.setId(rs.getInt("id"));
                licenca.setDataVencimento(rs.getString("vencimento"));
                licenca.setDataAquisicao(rs.getString("aquisicao"));
                licenca.getSoftware().setId(rs.getInt("software"));
                licencas.add(licenca);
            }
            
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaDAOPsql.class);
        }
        
        return licencas;
    }

    @Override
    public List<Licenca> selectVencimento(Licenca l) throws SQLException, ClassNotFoundException {
        List<Licenca> licencas = new ArrayList<Licenca>();
        
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM tb_licenca WHERE vencimento = ?");
            pstmt.setString(1, l.getDataVencimento());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Licenca licenca = new Licenca();
                licenca.setSoftware(new Software());
                licenca.setId(rs.getInt("id"));
                licenca.setDataVencimento(rs.getString("vencimento"));
                licenca.setDataAquisicao(rs.getString("aquisicao"));
                licenca.getSoftware().setId(rs.getInt("software"));
                licencas.add(licenca);
            }
            
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaDAOPsql.class);
        }
        
        return licencas;
    }
    
    @Override
    public void ativa(Licenca licenca) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tb_licenca SET status = 1 WHERE id = ?");
            pstmt.setInt(1, licenca.getId());
            pstmt.executeUpdate();                
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaDAOPsql.class);
        }
    }

    @Override
    public void desativa(Licenca licenca) throws SQLException, ClassNotFoundException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement("UPDATE tb_licenca SET status = 0 WHERE id = ?");
            pstmt.setInt(1, licenca.getId());
            pstmt.executeUpdate();                
            conn.close();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaDAOPsql.class);
        }
    }
}
