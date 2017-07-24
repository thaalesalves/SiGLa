package testpackage;

import util.ActiveDirectory;
import model.Pessoa;
import dao.GrupoDAO;
import model.Grupo;

public class TesteLoginAD {

    public static void main(String[] args) {
        try {
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();
            GrupoDAO gdao = new GrupoDAO();

            System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\user\\Documents\\NetBeansProjects\\SiGLa\\lib\\security\\cacerts");
            System.setProperty("javax.net.ssl.keyStore", "C:\\Users\\user\\Documents\\NetBeansProjects\\SiGLa\\lib\\security\\cacerts");
            System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
            System.setProperty("com.sun.jndi.ldap.connect.pool.timeout", "1800000");

            p.setUsername("estagiario");
            p.setSenha("estagiario");

            ad.login(p);

            p.setNome(ad.getGivenName(p));
            p.setNomeCompleto(ad.getCN(p));

            for (Grupo g : gdao.select()) {
                if (ad.isMember(p, g)) {
                    System.out.println(p.getNome() + " é um " + g.getRole());
                } else {
                    System.out.println(p.getNome() + " não um " + g.getRole());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
