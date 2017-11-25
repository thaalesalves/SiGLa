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
import dao.dao.IncidenteDAO;
import dao.dao.ReservaDAO;

public class DAOFactoryMysql extends DAOFactory {

    @Override
    public CursoDAO getCursoDAO() {
        return new dao.dao.mysql.CursoDAOMysql();
    }
    
    @Override
    public EquipamentoDAO getEquipamentoDAO() {
        return new dao.dao.mysql.EquipamentoDAOMysql();
    }

    @Override
    public GrupoDAO getGrupoDAO() {
        return new dao.dao.mysql.GrupoDAOMysql();
    }

    @Override
    public LaboratorioDAO getLaboratorioDAO() {
        return new dao.dao.mysql.LaboratorioDAOMysql();
    }

    @Override
    public ModuloDAO getModuloDAO() {
        return new dao.dao.mysql.ModuloDAOMysql();
    }

    @Override
    public ReservaDAO getReservaDAO() {
        return new dao.dao.mysql.ReservaDAOMysql();
    }

    @Override
    public SoftwareDAO getSoftwareDAO() {
        return new dao.dao.mysql.SoftwareDAOMysql();
    }

    @Override
    public SolicitacaoDAO getSolicitacaoDAO() {
        return new dao.dao.mysql.SolicitacaoDAOMysql();
    }

    @Override
    public IncidenteDAO getIncidenteDAO() {
        return new dao.dao.mysql.IncidenteDAOMysql();
    }
}
