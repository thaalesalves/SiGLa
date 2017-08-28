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

public class Laboratorio {

    private Integer id;
    private Integer qtd;
    private Integer capacidade;
    private Integer computadores;
    private String numero;    

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }
    
    public Integer getCapacidade() {
        return capacidade;
    }
    
    public void setComputadores(Integer computadores) {
        this.computadores = computadores;
    }
    
    public Integer getComputadores() {
        return computadores;
    }
    
    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Integer getQtd() {
        return qtd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
