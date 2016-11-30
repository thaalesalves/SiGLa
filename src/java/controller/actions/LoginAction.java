/**
* Copyright (C) 2016 Thales Alves Pereira
* 
*  This file is part of SiGla.

*   SiGla is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.

*   SiGla is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.

*   You should have received a copy of the GNU General Public License
*   along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
**/

package controller.actions;

import util.ActiveDirectory;
import dao.EquipamentoDAO;
import dao.LaboratorioDAO;
import dao.ReservaDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Equipamento;
import model.Grupo;
import model.Laboratorio;
import model.Pessoa;
import model.Reserva;

public class LoginAction implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ConnectException, IOException, NamingException, ServletException {
        HttpSession session = request.getSession();
        try {
            // <editor-fold defaultstate="collapsed" desc="Atributos do método.">
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            Grupo groups = new Grupo();
            Reserva r = new Reserva();
            Equipamento e = new Equipamento();
            Laboratorio l = new Laboratorio();

            p.setUsername(request.getParameter("username")); // passa o atributo de usuário
            p.setSenha(request.getParameter("password")); // passa o atributo de senha

            String path = "//admlab001/c$/img/users/" + p.getUsername() + "_pic.jpg";
            FileOutputStream pic = new FileOutputStream(new File(path));
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Invocação do login.">
            if (ad.login(p)) { // faz o login
                LaboratorioDAO daolab = new LaboratorioDAO();
                ReservaDAO resdao = new ReservaDAO();
                EquipamentoDAO equipdao = new EquipamentoDAO();

                e.setQtd(equipdao.qtdEquip());
                r.setQtd(resdao.qtdReservas());
                r.setQtdDia(resdao.qtdReservasDia());
                l.setQtd(daolab.qtdLabs());
                p.setNome(ad.getGivenName(p)); // passa o atributo de nome
                p.setNomeCompleto(ad.getCN(p)); // passa o atributo de nome completo
                p.setCargo(ad.getTitle(p)); // passa o atributo de cargo
                p.setDepto(ad.getDepartment(p)); // passa o atributo de cargo
                p.setChapa(ad.getOffice(p)); // passa o atributo da chapa             
                p.setEmail(p.getUsername() + "@umc.br"); // passa o atributo de email   

                boolean acesso = false;
                for (String g : groups.getOu()) {
                    groups.setGrupo(g + groups.getDc());
                    if (ad.isMember(p, groups)) {
                        acesso = true;
                    }
                }

                if (acesso) {
                    session.setAttribute("pessoa", p); // salva dados do login na sessão
                    session.setAttribute("laboratorio", l); // salva dados dos labs na sessão
                    session.setAttribute("reserva-qtd", r); // salva dados das reservas na sessão
                    session.setAttribute("equipamento", e); // salva dados das equipamentos na sessão
                    return request.getContextPath() + "/pagina/home";
                } else {
                    session.setAttribute("login", "acesso");
                    return request.getContextPath(); // chama de volta a página de login
                }
            } // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="Login sem sucesso.">
            else { // caso o usuário não exista
                session.setAttribute("login", "false");
                return request.getContextPath(); // chama de volta a página de login
            } // </editor-fold>
        } catch (AuthenticationException e) {
            util.Logger.logSevere(e, e.getClass());
            session.setAttribute("auth", "false");
            return request.getContextPath(); // chama de volta a página de login
        } catch (FileNotFoundException e) {
            util.Logger.logSevere(e, e.getClass());
            session.setAttribute("exception", e);
            return request.getContextPath() + "/error/error";
        } catch (Exception e) {
            util.Logger.logSevere(e, e.getClass());
        }
        return null;
    }
}
