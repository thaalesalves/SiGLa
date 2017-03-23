/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */
package model;

import java.util.ArrayList;

public class Solicitacao {

    private int id;    
    private String diaSemana;
    private String turma;
    private String observacao;
    private String modulo;
    private Pessoa pessoa = new Pessoa();
    private Curso curso = new Curso();
    private Software software = new Software();
    
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    
    public String getModulo() {
        return modulo;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public int getId() {
        return id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getTurma() {
        return turma;
    }

    public String getObservacao() {
        return observacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Curso getCurso() {
        return curso;
    }

    public Software getSoftware() {
        return software;
    }
}
