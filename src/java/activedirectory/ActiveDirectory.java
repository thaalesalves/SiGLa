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

public class ActiveDirectory {

    private static final Logger LOG = Logger.getLogger(ActiveDirectory.class.getName());
    private final String[] returnAttributes = {"sAMAccountName", "givenName", "cn", "mail", "memberOf", "name"};
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    
    /* Métodos da API */
    public void closeLdapConnection() {
        try {
            if (dirContext != null) {
                dirContext.close();
            }
        } catch (NamingException e) {
            LOG.severe(e.getMessage());
        }
    }

    /* Métodos Próprios */
    public NamingEnumeration<SearchResult> searchUser(Pessoa p) throws NamingException {
        String filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + p.getUsername() + "))";

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls);
    }

    public NamingEnumeration<SearchResult> searchUser(Pessoa p, String group) throws NamingException {
        //String filter = "(&(objectClass=user)(memberOf=CN=" + group + ",OU=INTERNET,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br)(sAMAccountName=" + p.getUsername() + "))";
        String filter = "(&(objectClass=user)(memberOf=CN=" + group + ",OU=DEPTI,OU=ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br)(sAMAccountName=" + p.getUsername() + "))";

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls);
    }

    public boolean isUser(Pessoa p) throws NamingException {
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            if (result.hasMoreElements()) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean isMember(Pessoa p, String group) throws NamingException {
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p, group);
            if (result.hasMoreElements()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getCN(Pessoa p) throws NamingException {
        String cn = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                cn = attrs.get("cn").toString();
                cn = cn.substring(cn.indexOf(":") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;
    }

    public String getGivenName(Pessoa p) throws NamingException {
        String givenName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                givenName = attrs.get("givenName").toString();
                givenName = givenName.substring(givenName.indexOf(":") + 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return givenName;
    }

    public String getPhysicalDeliveryOfficeName(Pessoa p) throws NamingException {
        String physicalDeliveryOfficeName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                physicalDeliveryOfficeName = attrs.get("physicalDeliveryOfficeName").toString();
                physicalDeliveryOfficeName = physicalDeliveryOfficeName.substring(physicalDeliveryOfficeName.indexOf(":") + 1);
            }

            physicalDeliveryOfficeName = physicalDeliveryOfficeName.replaceAll("[^\\d.]", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return physicalDeliveryOfficeName;
    }
    
    public boolean login(Pessoa p) throws NamingException, AuthenticationException {
        try {
            properties = new Properties();

            properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            properties.put(Context.PROVIDER_URL, "LDAP://umc.br"); // conecta com o AD DC
            properties.put(Context.SECURITY_PRINCIPAL, p.getUsername() + "@umc.br"); // valida credencial de usuário
            properties.put(Context.SECURITY_CREDENTIALS, p.getSenha()); // valida credencial de senha

            try {
                dirContext = new InitialDirContext(properties); // cria o contexto do AD passando as credenciais
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            searchCtls.setReturningAttributes(returnAttributes);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
