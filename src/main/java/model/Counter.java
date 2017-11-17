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

import java.io.Serializable;
import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class Counter implements Serializable {
    private int qtdSolicitacoes;
    private int qtdReservas;
    private int qtdReservasHoje;
    private int qtdLaboratorios;
    private int qtdComputadores;
    private Solicitacao solicitacao;
    private ArrayList<Laboratorio> laboratorios;
    private ArrayList<Solicitacao> solicitacoes;
    private ArrayList<Curso> cursos;
    private ArrayList<Software> softwares;
}
