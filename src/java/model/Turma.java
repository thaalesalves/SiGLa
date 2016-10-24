package model;

public class Turma {
    private String turma;
    private int semestre;
    private int id;

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurma() {
        return turma;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getId() {
        return id;
    }
}
