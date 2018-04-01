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

import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Erro;
import model.Pessoa;
import model.Solicitacao;
import util.ActiveDirectory;
import util.Logger;

public class SolicitacaoIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, NullPointerException, ClassNotFoundException, IOException, NamingException {
        HttpSession session = request.getSession();
        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        Pessoa u = (Pessoa) session.getAttribute("pessoa");
        DAOFactory fac = DAOFactory.getFactory();
        Solicitacao s = new Solicitacao();

        s.setId(Integer.parseInt(request.getParameter("id")));
        s = fac.getSolicitacaoDAO().selectSolicitacao(s);

        if (s.getId() < 1) {
            Erro err = new Erro();
            err.setErro("Tentativa ilegal de passar valores.");
            Logger.logOutput("Parece que " + u.getNomeCompleto() + "(" + u.getUsername() + ") tentou passar valores ilegais. Valor de ID passado: " + s.getId());
            return util.Json.toJson(err);
        }
        
        s.getPessoa().setNomeCompleto(ad.getCN(s.getPessoa()));
        s.getPessoa().setNome(ad.getGivenName(s.getPessoa()));
        s.getPessoa().setShownName(s.getPessoa().getNome() + " " + s.getPessoa().getNomeCompleto().substring(s.getPessoa().getNomeCompleto().lastIndexOf(" ") + 1));
        ad.closeLdapConnection();
        Logger.logOutput(u.getNomeCompleto() + "(" + u.getUsername() + ") buscou detalhes sobre a solicitação #" + s.getId());
        
        return util.Json.toJson(s);
    }
}
