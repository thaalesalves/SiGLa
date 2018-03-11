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
package dao;

import dao.dao.EquipamentoDAO;
import dao.dao.GrupoDAO;
import dao.dao.LaboratorioDAO;
import dao.dao.ModuloDAO;
import dao.dao.SoftwareDAO;
import dao.dao.SolicitacaoDAO;
import dao.dao.CursoDAO;
import dao.dao.FornecedorDAO;
import dao.dao.IncidenteDAO;
import dao.dao.LicencaDAO;
import dao.dao.RepresentanteDAO;
import dao.dao.ReservaDAO;
import util.SiGLa;

public abstract class DAOFactory {

    public abstract CursoDAO getCursoDAO();
    public abstract EquipamentoDAO getEquipamentoDAO();
    public abstract GrupoDAO getGrupoDAO();
    public abstract LaboratorioDAO getLaboratorioDAO();
    public abstract ModuloDAO getModuloDAO();
    public abstract ReservaDAO getReservaDAO();
    public abstract SoftwareDAO getSoftwareDAO();
    public abstract SolicitacaoDAO getSolicitacaoDAO();
    public abstract IncidenteDAO getIncidenteDAO();
    public abstract FornecedorDAO getFornecedorDAO();
    public abstract LicencaDAO getLicencaDAO();
    public abstract RepresentanteDAO getRepresentanteDAO();
    
    public static DAOFactory getFactory() {
        if (SiGLa.getDbms().equalsIgnoreCase("mysql")) {
            return new DAOFactoryMysql();
        } else if (SiGLa.getDbms().equalsIgnoreCase("psql")) {
            return new DAOFactoryPsql();
        }
        
        return null;
    }
}
