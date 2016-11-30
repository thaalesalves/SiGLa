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

public class Curso {

    private String nome;
    private String modalidade;
    private int id;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public int getId() {
        return id;
    }
}
