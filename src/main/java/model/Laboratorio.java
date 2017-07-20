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

    private int id;
    private int qtd;
    private int capacidade;
    private int computadores;
    private String numero;    

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    
    public int getCapacidade() {
        return capacidade;
    }
    
    public void setComputadores(int computadores) {
        this.computadores = computadores;
    }
    
    public int getComputadores() {
        return computadores;
    }
    
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd() {
        return qtd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
