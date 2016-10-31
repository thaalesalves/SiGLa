package model;

public class Reserva {
    
    private Pessoa pessoa;
    private Laboratorio lab;
    private Software software;
    private Turma turma;
    private Curso curso;
    private String tipo;
    private int id;
    private int qtd;

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

    public void setTurma(Turma turma) {
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

    public Turma getTurma() {
        return turma;
    }

    public String getTipo() {
        return tipo;
    }

}
