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

package model;

public class Grupo {

    public Grupo() {
    }

    private String grupo;
    private String dc = ",OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br";
    private String[] ou = {
        "memberOf=CN=professores-mg,OU=PRPPE,OU=Predio 5 - ADM", // grupo de professores
        "memberOf=CN=DEPTI_Estagio,OU=DEPTI,OU=Predio 5 - ADM", // grupo de estagiários
        "memberOf=CN=DEPTI,OU=DEPTI,OU=Predio 5 - ADM", // grupo de funcionário
        "memberOf=CN=COORDENADORES" // grupo de coodenadores
    };

    public void setDc(String dc) {
        this.dc = dc;
    }

    public void setOu(String[] ou) {
        this.ou = ou;
    }

    public String getDc() {
        return dc;
    }

    public String[] getOu() {
        return ou;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }
}
