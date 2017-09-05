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
import java.util.ArrayList;
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

/**
 * Classe que integra o Java com o Active Directory via <code>LDAP</code> ou
 * <code>LDAPS</code>, com métodos que buscam todos os dados úteis do usuário
 *
 * @author Thales Alves Pereira
 * @version 1.0
 * @since 2016-06-20
 * @throws NamingException quando há algum erro de resolução de nomes
 * @throws AuthenticationException quando há algum erro de autenticação
 */
public class ActiveDirectory {

    private final String[] returnAttributes = {"mail", "jpegPhoto", "thumbnailPhoto", "sAMAccountName", "givenName", "cn", "memberOf", "title", "department", "physicalDeliveryOfficeName"};
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    private String ldap_base = "DC=thalesalv,DC=es";

    public ActiveDirectory() {
        searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnAttributes);
    }

    /**
     * Efetua o login no diretório via <code>LDAP</code>, e define propriedades
     * de domínio, endereço, porta, usuário e senha.
     *
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     * @throws javax.naming.AuthenticationException se uma operação de
     * autenticação falhar
     */
    public boolean login(Pessoa p) throws NamingException, AuthenticationException { // método de login
        properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // pacote do LDAP
        properties.put(Context.PROVIDER_URL, SiGLa.getAuthMethod() + "://" + SiGLa.getDomainHost() + ":" + SiGLa.getAuthPort()); // conecta com o AD DC
        properties.put(Context.SECURITY_PRINCIPAL, p.getUsername() + "@" + SiGLa.getDomain()); // valida credencial de usuário
        properties.put(Context.SECURITY_CREDENTIALS, p.getSenha()); // valida credencial de senha
        properties.put(Context.REFERRAL, "follow");
        dirContext = new InitialDirContext(properties); // cria o contexto do AD passando as credenciais

        return true; // login efetuado
    }

    /**
     * Fecha a conexão entre aplicação e diretório via <code>LDAP</code>
     */
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
    }

    /**
     * Busca um usuário específico no diretório
     *
     * @param p classe de modelo Pessoa
     * @return
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public NamingEnumeration<SearchResult> searchUser(Pessoa p) throws NamingException { // busca de usuário
        String filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + p.getUsername() + "))"; // Query LDAP de busca de pessoas

        return this.dirContext.search(ldap_base, filter, this.searchCtls); // Define a raiz do domínio do AD
    }

    /**
     * Busca usuários de professor no contexto do diretório methods.
     *
     * @return
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public NamingEnumeration<SearchResult> searchUser() throws NamingException { // busca de usuário
        String filter = "(&(objectCategory=person)(objectClass=user)(memberOf=CN=sigla_prof,OU=AADDC Users,DC=thalesalv,DC=es))"; // Query LDAP de busca de pessoas
        /* COLOCAR NO LDAP FILTRO DE GRUPO DE PROFESSOR */
        return this.dirContext.search(ldap_base, filter, this.searchCtls); // Define a raiz do domínio do AD
    }

    /**
     * Busca uma pessoa dentro de um grupo específico
     *
     * @return
     * @param p classe de modelo Pessoa
     * @param g classe de modelo Grupo
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public NamingEnumeration<SearchResult> searchUser(Pessoa p, Grupo g) throws NamingException { //busca de usuário dentro de grupo
        String filter = "";
        try {
            filter = "(&(objectClass=user)(" + g.getGrupo() + ")(sAMAccountName=" + p.getUsername() + "))"; // Query do LDAP de busca de usuários dentro do grupo
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return this.dirContext.search(ldap_base, filter, this.searchCtls);
    }

    /**
     * Teste se um usuário realmente existe no contexto do diretório
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public boolean isUser(Pessoa p) throws NamingException { // Verifica se o usuário existe
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca o método de busca de usuário
            if (result.hasMore()) { // caso algo seja retornado
                return true; // o usuário existe
            } else {
                return false; // o usuário não existe
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return false; // o usuário não existe
    }

    /**
     * Checa se um usuário específico é membro de um grupo específico do
     * diretório
     *
     * @return
     * @param p classe de modelo Pessoa
     * @param g classe de modelo Grupo
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca o parâmetro <code>cn</code> do <code>Active Directory</code>, que
     * representa o nome completo de um usuário
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca todos os dados dos usuários do grupo de professor
     *
     * @return
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public ArrayList<Pessoa> getProfessores() throws NamingException { // returna nome completo
        ArrayList<Pessoa> ps = new ArrayList<Pessoa>();

        try {
            String filter = "(&(objectCategory=person)(objectClass=user)(memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es))";

            NamingEnumeration<SearchResult> result = this.dirContext.search(ldap_base, filter, this.searchCtls);
            while (result.hasMoreElements()) {
                Pessoa p = new Pessoa();
                String attr;

                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();

                attr = attrs.get("givenName").toString();
                attr = attr.substring(attr.indexOf(":") + 1);
                p.setNome(attr.trim());
                
                attr = attrs.get("cn").toString();
                attr = attr.substring(attr.indexOf(":") + 1);
                p.setNomeCompleto(attr.trim());

                attr = attrs.get("sAMAccountName").toString();
                attr = attr.substring(attr.indexOf(":") + 1);
                attr = attr.replace("sAMAccountName:", "").trim();
                p.setUsername(attr.trim());
                
                attr = attrs.get("mail").toString();
                attr = attr.substring(attr.indexOf(":") + 1);
                p.setEmail(attr.trim());

                p.setShownName(p.getNome() + " " + p.getNomeCompleto().substring(p.getNomeCompleto().lastIndexOf(" ") + 1));

                ps.add(p);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return ps;
    }

    /**
     * Busca o parâmetro <code>title</code> do <code>Active Directory</code>,
     * que representa o cargo do usuário na empresa
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca o parâmetro <code>department</code> do
     * <code>Active Directory</code>, que representa o departamento do usuário
     * na empresa
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca o parâmetro <code>givenName</code> do
     * <code>Active Directory</code>, que representa o primeiro nome de um
     * usuário
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca o parâmetro <code>mail</code> do <code>Active Directory</code>, que
     * representa o email de um usuário.
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public String getMail(Pessoa p) throws NamingException { // busca primeiro nome
        String mail = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                mail = attrs.get("mail").toString(); // conversão do atributo
                mail = mail.substring(mail.indexOf(":") + 1); // definição na variável
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return mail; // retorno do nome
    }

    /**
     * Busca o parâmetro <code>office</code> do <code>Active Directory</code>,
     * que representa o escritório do usuário
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
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
    }

    /**
     * Busca o parâmetro <code>jpegPhoto</code> ou o <code>thumbnailPhoto</code>
     * do <code>Active Directory</code>, que representa a foto do usuário
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public byte[] getPicture(Pessoa p) throws NamingException, FileNotFoundException { // busca foto
        byte[] pic;
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p); // invoca método de busca
            if (result.hasMoreElements()) { // caso algo seja retornado
                SearchResult sr = (SearchResult) result.next(); //entra na tupla
                Attributes attrs = sr.getAttributes(); // define atributos
                pic = (byte[]) attrs.get("jpegPhoto").get();

                if (pic == null) {
                    pic = (byte[]) attrs.get("thumbnailPhoto").get();
                }

                return pic;
            }
        } catch (Exception e) {
            Logger.logWarning(e, this.getClass());
            return null;
        }

        return null;
    }
}
