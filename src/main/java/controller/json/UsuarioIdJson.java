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

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pessoa;
import util.ActiveDirectory;
import util.Logger;

public class UsuarioIdJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        HttpSession session = request.getSession();
        Pessoa pessoa = (Pessoa) session.getAttribute("pessoa");
        Pessoa prof = new Pessoa();
        ActiveDirectory ad = (ActiveDirectory) session.getAttribute("ad");
        ad.login(pessoa);

        prof.setUsername(request.getParameter("username"));
        prof.setEmail(ad.getMail(prof));
        prof.setCargo(ad.getTitle(prof));
        prof.setEmpresa(ad.getCompany(prof));
        prof.setDepto(ad.getDepartment(prof));
        prof.setNomeCompleto(ad.getCN(prof));
        prof.setNome(ad.getGivenName(prof));
        prof.setShownName(ad.getDisplayName(prof));
         
        Logger.logOutput(pessoa.getNomeCompleto() + "(" + pessoa.getUsername() + ") acaba de buscar "
                + "detalhes sobre " + prof.getNomeCompleto() + "(" + prof.getUsername() + ").");

        return util.Json.toJson(prof);
    }
}
