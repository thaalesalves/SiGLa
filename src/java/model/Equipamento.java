package model;

public class Equipamento {

    private String nome;
    private String ip;
    private String mac;
    private int qtd;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String getNome() {
        return nome;
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public int getQtd() {
        return qtd;
    }
}
