/*
 * Copyright (C) 2017 thaal
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
package controller.json;

import dao.SolicitacaoDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Solicitacao;
import util.ActiveDirectory;

public class SolicitacaoJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, NullPointerException, NamingException, IOException {
        HttpSession session = request.getSession();

        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        SolicitacaoDAO dao = new SolicitacaoDAO();
        ArrayList<Solicitacao> solicitacao = dao.selectSolicitacao();

        for (Solicitacao s : solicitacao) {
            s.getPessoa().setNome(ad.getGivenName(s.getPessoa()));
            s.getPessoa().setNomeCompleto(ad.getCN(s.getPessoa()));
            s.getPessoa().setShownName(s.getPessoa().getNome() + " " + s.getPessoa().getNomeCompleto().substring(s.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
        }
        
        return util.Json.toCuteJson(solicitacao);
    }
}