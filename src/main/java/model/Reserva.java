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

import java.util.ArrayList;
import java.util.Calendar;

@lombok.Getter
@lombok.Setter
public class Reserva {

    private Integer id;
    private Integer qtd;
    private Integer qtdDia;
    private Integer qtdAlunos;
    private String tipo;
    private String diaDaSemana;
    private String turma;
    private String observacao;
    private Pessoa pessoa = new Pessoa();
    private Laboratorio lab = new Laboratorio();
    private Software software = new Software();
    private Curso curso = new Curso();
    private ArrayList<Modulo> modulos = new ArrayList<Modulo>();
    private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    private ArrayList<Software> softwares = new ArrayList<Software>();
    private ArrayList<Curso> cursos = new ArrayList<Curso>();
    
    public static String calendarioDia() {
        Calendar calendar = Calendar.getInstance();
        Integer dia = calendar.get(Calendar.DAY_OF_WEEK);
        String diaSemana = "";

        switch (dia) {
            case Calendar.SUNDAY:
                diaSemana = "Domingo";
                break;
            case Calendar.MONDAY:
                diaSemana = "Segunda-feira";
                break;
            case Calendar.TUESDAY:
                diaSemana = "Terça-feira";
                break;
            case Calendar.WEDNESDAY:
                diaSemana = "Quarta-feira";
                break;
            case Calendar.THURSDAY:
                diaSemana = "QuIntegera-feira";
                break;
            case Calendar.FRIDAY:
                diaSemana = "Sexta-feira";
                break;
            case Calendar.SATURDAY:
                diaSemana = "Sábado";
                break;
        }

        return diaSemana;
    }
}
