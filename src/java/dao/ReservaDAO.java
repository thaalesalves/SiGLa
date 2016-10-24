package dao;

import model.*;
import java.sql.*;
import java.util.*;
import util.*;
import activedirectory.*;

public class ReservaDAO {

    private final String SELECT_ALL = "SELECT curso.modalidade AS modalidade, curso.nome AS curso, reserva.id AS reserva, reserva.tipo AS tipo, laboratorio.numero AS laboratorio, software.fabricante AS fabricante, software.nome AS software, reserva.professor AS professor, turma.semestre AS semestre, turma.turma AS turma FROM reserva, laboratorio, software, turma, curso WHERE reserva.turma = turma.id AND laboratorio.id = reserva.laboratorio AND reserva.softwares = software.id AND curso.id = reserva.curso";

    public ArrayList<Reserva> selectReserva(Pessoa pessoa) throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> arrayRes = new ArrayList<Reserva>();

        try (Connection connString = DatabaseConnection.getConnection()) {
            PreparedStatement pstmt = connString.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Pessoa p = new Pessoa();
                Laboratorio l = new Laboratorio();
                Software sw = new Software();
                Turma t = new Turma();
                Reserva r = new Reserva();
                Curso c = new Curso();
                ActiveDirectory ad = new ActiveDirectory();
                
                ad.login(pessoa);
                
                p.setUsername(rs.getString("professor"));
                l.setNumero(rs.getString("laboratorio"));
                sw.setFabricante(rs.getString("fabricante"));
                sw.setNome(rs.getString("software"));
                t.setSemestre(rs.getInt("semestre"));
                t.setTurma(rs.getString("turma"));
                c.setModalidade("modalidade");
                c.setNome("curso");
                r.setId(rs.getInt("reserva"));
                
                if (rs.getInt("tipo") == 1) {
                    r.setTipo("Semestral");
                } else {
                    r.setTipo("Pontual");
                }
                
                p.setNomeCompleto(ad.getCN(p));
                p.setNome(ad.getGivenName(p));
                
                r.setCurso(c);
                r.setPessoa(p);
                r.setLab(l);
                r.setSoftware(sw);
                r.setTurma(t);
                
                arrayRes.add(r);
            }
            
            connString.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayRes;
    }
}
