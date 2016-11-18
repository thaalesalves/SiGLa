package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Reserva {

    private int id;
    private int qtd;
    private int qtdDia;
    private String tipo;
    private String diaDaSemana;
    private String modulo;
    private String turma;
    private String dataDeInicio;
    private String dataDeTermino;
    private String observacao;
    private Pessoa pessoa = new Pessoa();
    private Laboratorio lab = new Laboratorio();
    private Software software = new Software();
    private Curso curso = new Curso();
    private ArrayList<Software> softwares = new ArrayList<Software>();
    private ArrayList<Curso> cursos = new ArrayList<Curso>();

    public void setQtdDia(int qtdDia) {
        this.qtdDia = qtdDia;
    }

    public int getQtdDia() {
        return qtdDia;
    }

    public String getDiaSemana() {
        Calendar calendar = Calendar.getInstance();
        int dia = calendar.get(Calendar.DAY_OF_WEEK);
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
                diaSemana = "Quinta-feira";
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

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public void setDataDeInicio(String dataDeInicio) {
        this.dataDeInicio = dataDeInicio;
    }

    public void setDataDeTermino(String dataDeTermino) {
        this.dataDeTermino = dataDeTermino;
    }

    public String getModulo() {
        return modulo;
    }

    public String getDataDeInicio() {
        return dataDeInicio;
    }

    public String getDataDeTermino() {
        return dataDeTermino;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public ArrayList<Software> getSoftwares() {
        return softwares;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setSoftwares(ArrayList<Software> softwares) {
        this.softwares = softwares;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd() {
        return qtd;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setLab(Laboratorio lab) {
        this.lab = lab;
    }

    public void setSoftware(Software software) {
        this.software = software;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Laboratorio getLab() {
        return lab;
    }

    public Software getSoftware() {
        return software;
    }

    public String getTurma() {
        return turma;
    }

    public String getTipo() {
        return tipo;
    }

}
