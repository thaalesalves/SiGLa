package model;

public class Grupo {

    public Grupo() {
    }

    private String grupo;
    private String dc = ",OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br";
    private String[] ou = {
        "memberOf=CN=professores-mg,OU=PRPPE,OU=ADM", // grupo de professores
        "memberOf=CN=DEPTI_Estagio,OU=DEPTI,OU=ADM", // grupo de estagiários
        "memberOf=CN=DEPTI,OU=DEPTI,OU=ADM", // grupo de funcionário
        "memberOf=CN=COORDENADORES" // grupo de coodenadores
    };

    public void setDc(String dc) {
        this.dc = dc;
    }

    public void setOu(String[] ou) {
        this.ou = ou;
    }

    public String getDc() {
        return dc;
    }

    public String[] getOu() {
        return ou;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() {
        return grupo;
    }
}
