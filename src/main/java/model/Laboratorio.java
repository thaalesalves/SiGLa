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
*   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
**/

package model;

import java.io.Serializable;
import java.util.List;

@lombok.Getter
@lombok.Setter
public class Laboratorio implements Serializable {

    private Integer id;
    private Integer qtd;
    private Integer capacidade;
    private Integer computadores;
    private String numero;
    private String processador;
    private String memoria;
    private String modelo;
    private String prefixoIp;
    private List<Equipamento> equipamentos;
    private List<Software> softwares;

    @Override
    public boolean equals(Object obj) {
        try {      
            boolean mesmoId = this.getId() == ((Laboratorio) obj).getId();
            return mesmoId;
            
        } catch (Exception e) {
            return false;
        }
    }
}
