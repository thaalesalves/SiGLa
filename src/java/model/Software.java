package model;

public class Software {
    private String fabricante;
    private String nome;
    private int id;

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
