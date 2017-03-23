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

public class Counter {
    private int qtdSolicitacoes;
    private int qtdReservas;
    private int qtdReservasHoje;
    private int qtdLaboratorios;
    private int qtdComputadores;
    private Solicitacao solicitacao;
    private ArrayList<Solicitacao> solicitacoes;

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public void setSolicitacoes(ArrayList<Solicitacao> solicitacoes) {
        this.solicitacoes = solicitacoes;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public ArrayList<Solicitacao> getSolicitacoes() {
        return solicitacoes;
    }
    
    public void setQtdSolicitacoes(int qtdSolicitacoes) {
        this.qtdSolicitacoes = qtdSolicitacoes;
    }

    public void setQtdReservas(int qtdReservas) {
        this.qtdReservas = qtdReservas;
    }

    public void setQtdReservasHoje(int qtdReservasHoje) {
        this.qtdReservasHoje = qtdReservasHoje;
    }

    public void setQtdLaboratorios(int qtdLaboratorios) {
        this.qtdLaboratorios = qtdLaboratorios;
    }

    public void setQtdComputadores(int qtdComputadores) {
        this.qtdComputadores = qtdComputadores;
    }

    public int getQtdSolicitacoes() {
        return qtdSolicitacoes;
    }

    public int getQtdReservas() {
        return qtdReservas;
    }

    public int getQtdReservasHoje() {
        return qtdReservasHoje;
    }

    public int getQtdLaboratorios() {
        return qtdLaboratorios;
    }

    public int getQtdComputadores() {
        return qtdComputadores;
    }
}
