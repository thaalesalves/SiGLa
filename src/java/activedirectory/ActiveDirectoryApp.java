package activedirectory;

import model.*;
import java.util.*;
import java.util.Properties;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.AuthenticationException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class ActiveDirectoryApp {

    public static void main(String[] args) {
        try {
            ActiveDirectory ad = new ActiveDirectory();
            Pessoa p = new Pessoa();

            p.setUsername("thalespereira");
            p.setSenha("Eclip$3");

            ad.login(p);
            
            p.setNome(ad.getGivenName(p));
            p.setNomeCompleto(ad.getCN(p));
            p.setChapa(ad.getPhysicalDeliveryOfficeName(p));

            System.out.println("Nome: " + p.getNome());
            System.out.println("Nome completo: " + p.getNomeCompleto());
            System.out.println("Chapa: " + p.getChapa());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
