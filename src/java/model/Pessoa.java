package model;



public class Pessoa {
    public Pessoa() {}
    
    private String username;
    private String nome;
    private String nomeCompleto;
    private String cargo;
    private String email;
    private String chapa;
    private String senha;

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setChapa(String chapa) {
        this.chapa = chapa;
    }

    public String getChapa() {
        return chapa;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public String getEmail() {
        return email;
    }
    
    
}
