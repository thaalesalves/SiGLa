package model;



public class Pessoa {
    public Pessoa() {}
    
    private String username;
    private String nome;
    private String nomeCompleto;
    private String email;
    private String cargo;
    private String senha;
    private String depto;

    public void setDepto(String depto) {
        this.depto = depto;
    }
    
    public String getDepto() {
        return depto;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getEmail() {
        return email;
    }
    
    
}
