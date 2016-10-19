// <editor-fold defaultstate="collapsed" desc="Pacotes & Importações">
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
// </editor-fold>

public class ActiveDirectory {

    // <editor-fold defaultstate="collapsed" desc="Atributos da classe.">
    private static final Logger LOG = Logger.getLogger(ActiveDirectory.class.getName());
    private final String[] returnAttributes = {"sAMAccountName", "givenName", "cn", "mail", "memberOf", "name"};
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos da API: closeLdapConnection().">
    public void closeLdapConnection() {
        try {
            if (dirContext != null) {
                dirContext.close();
            }
        } catch (NamingException e) {
            LOG.severe(e.getMessage());
        }
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: searchUser(Pessoa).">
    public NamingEnumeration<SearchResult> searchUser(Pessoa p) throws NamingException { // busca de usuário
        String filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + p.getUsername() + "))"; // Query LDAP de busca de pessoas

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls); // Define a raiz do domínio do AD
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: searchUser(Pessoa, String).">
    public NamingEnumeration<SearchResult> searchUser(Pessoa p, String group) throws NamingException { //busca de usuário dentro de grupo
        String filter = "(&(objectClass=user)(memberOf=CN=" + group + ",OU=DEPTI,OU=ADM,OU=Grupos,OU=CAMPUS MOGI,OU=ADMINISTRATIVO,OU=OMEC,DC=umc,DC=br)(sAMAccountName=" + p.getUsername() + "))"; // Query do LDAP de busca de usuários dentro do grupo

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls); // Define a raiz do domínio do AD
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: isUser(Pessoa).">
    public boolean isUser(Pessoa p) throws NamingException { // Verifica se o usuário existe
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca o método de busca de usuário
            if (result.hasMoreElements()) { // caso algo seja retornado
                return true; // o usuário existe
            } else {
                return false; // o usuário não existe
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // o usuário não existe
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: isMember(Pessoa, Srting).">
    public boolean isMember(Pessoa p, String group) throws NamingException { // o usuário é membro do grupo
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p, group); // invoca o método de busca
            if (result.hasMoreElements()) { // se algo for retornado
                return true; // o usuário é membro do grupo
            } else {
                return false; // o usuário não é membro do grupo
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // o usuário não é membro do grupo
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getCN(Pessoa).">
    public String getCN(Pessoa p) throws NamingException { // returna nome completo
        String cn = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca pesquisa de usuário
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); // vai para próxima linha da tupla
                Attributes attrs = sr.getAttributes(); // busca atributos
                cn = attrs.get("cn").toString(); // conversão do atributo em string
                cn = cn.substring(cn.indexOf(":") + 1); // atribuição da string plena na variável
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn; // retorna o nome completo
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getGivenName(Pessoa).">
    public String getGivenName(Pessoa p) throws NamingException { // busca primeiro nome
        String givenName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                givenName = attrs.get("givenName").toString(); // conversão do atributo
                givenName = givenName.substring(givenName.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return givenName; // retorno do nome
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getPhysicalDeliveryOfficeName(Pessoa).">
    public String getPhysicalDeliveryOfficeName(Pessoa p) throws NamingException { // busca chapa do funcionário (gravada no atributo escritório do AD)
        String physicalDeliveryOfficeName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); // entra na tupla
                Attributes attrs = sr.getAttributes(); // busca atributos
                physicalDeliveryOfficeName = attrs.get("physicalDeliveryOfficeName").toString(); // covnersão doa tributo
                physicalDeliveryOfficeName = physicalDeliveryOfficeName.substring(physicalDeliveryOfficeName.indexOf(":") + 1); // definição da variável
            }

            physicalDeliveryOfficeName = physicalDeliveryOfficeName.replaceAll("[^\\d.]", ""); // retirada de letras e pontos
        } catch (Exception e) {
            e.printStackTrace();
        }
        return physicalDeliveryOfficeName; // retorno da chapa
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: login(Pessoa).">
    public boolean login(Pessoa p) throws NamingException, AuthenticationException { // método de login
        try {
            properties = new Properties();

            properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // pacote do LDAP
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

            return true; // login efetuado
        } catch (Exception e) {
            e.printStackTrace();
            return false; // login não efetuado
        }
    }// </editor-fold>
}
