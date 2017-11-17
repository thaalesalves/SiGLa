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
public class Solicitacao implements Serializable {

    private Integer id;    
    private Integer qtdAlunos;
    private String diaSemana;
    private String turma;
    private String observacao;
    private String modulo;
    private ArrayList<Modulo> modulos = new ArrayList<Modulo>();
    private ArrayList<Software> softwares = new ArrayList<Software>();
    private Pessoa pessoa = new Pessoa();
    private Curso curso = new Curso();
}
