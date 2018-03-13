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

import dao.sgbd.EquipamentoDAO;
import dao.sgbd.GrupoDAO;
import dao.sgbd.LaboratorioDAO;
import dao.sgbd.ModuloDAO;
import dao.sgbd.SoftwareDAO;
import dao.sgbd.SolicitacaoDAO;
import dao.sgbd.CursoDAO;
import dao.sgbd.FornecedorDAO;
import dao.sgbd.IncidenteDAO;
import dao.sgbd.LicencaDAO;
import dao.sgbd.RepresentanteDAO;
import dao.sgbd.ReservaDAO;

public class DAOFactoryMysql extends DAOFactory {

    @Override
    public CursoDAO getCursoDAO() {
        return new dao.sgbd.mysql.CursoDAOMysql();
    }
    
    @Override
    public EquipamentoDAO getEquipamentoDAO() {
        return new dao.sgbd.mysql.EquipamentoDAOMysql();
    }

    @Override
    public GrupoDAO getGrupoDAO() {
        return new dao.sgbd.mysql.GrupoDAOMysql();
    }

    @Override
    public LaboratorioDAO getLaboratorioDAO() {
        return new dao.sgbd.mysql.LaboratorioDAOMysql();
    }

    @Override
    public ModuloDAO getModuloDAO() {
        return new dao.sgbd.mysql.ModuloDAOMysql();
    }

    @Override
    public ReservaDAO getReservaDAO() {
        return new dao.sgbd.mysql.ReservaDAOMysql();
    }

    @Override
    public SoftwareDAO getSoftwareDAO() {
        return new dao.sgbd.mysql.SoftwareDAOMysql();
    }

    @Override
    public SolicitacaoDAO getSolicitacaoDAO() {
        return new dao.sgbd.mysql.SolicitacaoDAOMysql();
    }

    @Override
    public IncidenteDAO getIncidenteDAO() {
        return new dao.sgbd.mysql.IncidenteDAOMysql();
    }

    @Override
    public FornecedorDAO getFornecedorDAO() {
        return new dao.sgbd.mysql.FornecedorDAOMysql();
    }

    @Override
    public LicencaDAO getLicencaDAO() {
        return new dao.sgbd.mysql.LicencaDAOMysql();
    }

    @Override
    public RepresentanteDAO getRepresentanteDAO() {
        return new dao.sgbd.mysql.RepresentanteDAOMysql();
    }
}
