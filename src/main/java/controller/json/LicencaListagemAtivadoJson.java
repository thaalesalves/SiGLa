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
package controller.json;

import dao.DAOFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Erro;
import model.Licenca;
import model.Pessoa;
import util.IO;
import util.Logger;

public class LicencaListagemAtivadoJson implements IJson {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, NamingException, IOException, NullPointerException {
        List<Licenca> licencas = new ArrayList<Licenca>();
        Pessoa p = (Pessoa) request.getSession().getAttribute("pessoa");

        try {
            DAOFactory fac = DAOFactory.getFactory();
            licencas = fac.getLicencaDAO().selectAtivado();
        } catch (Exception e) {
            Logger.logSevere(e, LicencaListagemAtivadoJson.class);
            Logger.logOutput("Houve um erro quando " + p.getNomeCompleto() + "(" + p.getUsername() + ") tentou listar as licenças ativadas");
            Erro err = new Erro();
            err.setErro(e.getMessage());
            return util.Json.toJson(err);
        }

        Logger.logOutput(p.getNomeCompleto() + " (" + p.getUsername() + ") listou as licenças ativadas");
        return util.Json.toJson(licencas);
    }
}
