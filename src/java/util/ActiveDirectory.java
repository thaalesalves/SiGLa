/**
 * Copyright (C) 2016 Thales Alves Pereira
 *
 *  This file is part of SiGla.
 *
 *   SiGla is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   SiGla is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package util;

import java.io.FileNotFoundException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.AuthenticationException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import model.Grupo;
import model.Pessoa;
import model.Reserva;

public class ActiveDirectory {

    // <editor-fold defaultstate="collapsed" desc="Atributos da classe.">
    private final String[] returnAttributes = {"jpegPhoto", "thumbnailPhoto", "sAMAccountName", "givenName", "cn", "memberOf", "title", "department", "physicalDeliveryOfficeName"};
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Construtor.">
    public ActiveDirectory() {
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnAttributes);
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: login(Pessoa).">
    public boolean login(Pessoa p) throws NamingException, AuthenticationException { // método de login
        properties = new Properties();

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // pacote do LDAP
        properties.put(Context.PROVIDER_URL, "LDAP://canada"); // conecta com o AD DC
        properties.put(Context.SECURITY_PRINCIPAL, p.getUsername() + "@umc.br"); // valida credencial de usuário
        properties.put(Context.SECURITY_CREDENTIALS, p.getSenha()); // valida credencial de senha
        dirContext = new InitialDirContext(properties); // cria o contexto do AD passando as credenciais

        return true; // login efetuado
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Métodos da API: closeLdapConnection().">
    public void closeLdapConnection() {
        try {
            if (dirContext != null) {
                dirContext.close();
            }
        } catch (NamingException e) {
            Logger.logSevere(e, this.getClass());
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
    } //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: searchUser(Pessoa).">
    public NamingEnumeration<SearchResult> searchUser(Pessoa p) throws NamingException { // busca de usuário
        String filter = "";

        try {
            filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + p.getUsername() + "))"; // Query LDAP de busca de pessoas
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls); // Define a raiz do domínio do AD
    } // </editor-fold>    

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: searchUser(Pessoa, Grupo).">
    public NamingEnumeration<SearchResult> searchUser(Pessoa p, Grupo g) throws NamingException { //busca de usuário dentro de grupo
        String filter = "";
        try {
            filter = "(&(objectClass=user)(" + g.getGrupo() + ")(sAMAccountName=" + p.getUsername() + "))"; // Query do LDAP de busca de usuários dentro do grupo
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return this.dirContext.search("DC=umc,DC=br", filter, this.searchCtls);
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
            Logger.logSevere(e, this.getClass());
        }
        return false; // o usuário não existe
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: isMember(Pessoa, Grupo).">
    public boolean isMember(Pessoa p, Grupo g) throws NamingException { // o usuário é membro do grupo
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p, g); // invoca o método de busca
            if (result.hasMoreElements()) { // se algo for retornado
                return true; // o usuário é membro do grupo
            } else {
                return false; // o usuário não é membro do grupo
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
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
            Logger.logSevere(e, this.getClass());
        }

        return cn.trim(); // retorna o nome completo
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getCN(Reserva).">
    public String getCN(Reserva r) throws NamingException { // returna nome completo
        String cn = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(r.getPessoa()); // invoca pesquisa de usuário
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); // vai para próxima linha da tupla
                Attributes attrs = sr.getAttributes(); // busca atributos
                cn = attrs.get("cn").toString(); // conversão do atributo em string
                cn = cn.substring(cn.indexOf(":") + 1); // atribuição da string plena na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return cn.trim(); // retorna o nome completo
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getTitle(Pessoa).">
    public String getTitle(Pessoa p) throws NamingException { // busca cargo
        String title = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                title = attrs.get("title").toString(); // conversão do atributo
                title = title.substring(title.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return title.replaceAll("^\\s+|\\s+$", "").trim(); // retorno do cargo
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getDepartment(Pessoa).">
    public String getDepartment(Pessoa p) throws NamingException { // busca departamento
        String depto = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                depto = attrs.get("department").toString(); // conversão do atributo
                depto = depto.substring(depto.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return depto.trim(); // retorno do cargo
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
            Logger.logSevere(e, this.getClass());
        }

        return givenName; // retorno do nome
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getGivenName(Reserva).">
    public String getGivenName(Reserva r) throws NamingException { // busca primeiro nome
        String givenName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(r.getPessoa()); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                givenName = attrs.get("givenName").toString(); // conversão do atributo
                givenName = givenName.substring(givenName.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return givenName; // retorno do nome
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getOffice(Pessoa).">
    public String getOffice(Pessoa p) throws NamingException { // busca office
        String physicalDeliveryOfficeName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                physicalDeliveryOfficeName = attrs.get("physicalDeliveryOfficeName").toString(); // conversão do atributo
                physicalDeliveryOfficeName = physicalDeliveryOfficeName.substring(physicalDeliveryOfficeName.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return physicalDeliveryOfficeName.replaceAll("\\D+", "").trim();
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Métodos próprios: getPicture(Pessoa).">
    public byte[] getPicture(Pessoa p) throws NamingException, FileNotFoundException { // busca foto
        byte[] pic = null;
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                pic = (byte[]) attrs.get("jpegPhoto ").get();
            }
        } catch (Exception e) {
            Logger.logWarning(e, this.getClass());
        }
        return pic;
    } // </editor-fold>    
}
