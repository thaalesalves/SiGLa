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

    private final String[] returnAttributes = {"mail", "jpegPhoto", "thumbnailPhoto", "sAMAccountName", "givenName", "cn", "memberOf", "title", "department", "physicalDeliveryOfficeName", "manager", "company", "displayName"};
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
     * @return
     */
    public boolean login(Pessoa p) throws NamingException, AuthenticationException {
        properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, SiGLa.getAuthMethod() + "://" + SiGLa.getDomainHost() + ":" + SiGLa.getAuthPort());
        properties.put(Context.SECURITY_PRINCIPAL, p.getUsername() + "@" + SiGLa.getDomain());
        properties.put(Context.SECURITY_CREDENTIALS, p.getSenha());
        properties.put(Context.REFERRAL, "follow");
        dirContext = new InitialDirContext(properties);

        return true;
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
    public NamingEnumeration<SearchResult> searchUser(Pessoa p) throws NamingException {
        String filter = "(&(objectCategory=person)(objectClass=user)(sAMAccountName=" + p.getUsername() + "))";

        return this.dirContext.search(ldap_base, filter, this.searchCtls);
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
    public NamingEnumeration<SearchResult> searchUser(Pessoa p, Grupo g) throws NamingException {
        String filter = "";
        try {
            filter = "(&(objectCategory=person)(objectClass=user)(" + g.getGrupo() + ")(sAMAccountName=" + p.getUsername() + "))";
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return this.dirContext.search(ldap_base, filter, this.searchCtls);
    }

    /**
     * Busca todos os usuários dentro de um grupo específico
     *
     * @param g classe de modelo Grupo
     * @return
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public NamingEnumeration<SearchResult> searchUserGroup(Grupo g) throws NamingException {
        String filter = "(&(objectCategory=person)(objectClass=user)(" + g.getGrupo() + "))";

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
    public boolean isUser(Pessoa p) throws NamingException {
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMore()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return false;
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
    public boolean isMember(Pessoa p, Grupo g) throws NamingException {
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p, g);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return false;
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
    public String getCN(Pessoa p) throws NamingException {
        String cn = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                cn = attrs.get("cn").toString();
                cn = cn.substring(cn.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return cn.trim();
    }

    /**
     * Busca todos os dados dos usuários do grupo de professor
     *
     * @return
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public ArrayList<Pessoa> getProfessores() throws NamingException {
        ArrayList<Pessoa> ps = new ArrayList<Pessoa>();

        try {
            String filter = "(&(objectCategory=person)(objectClass=user)(memberOf=CN=sigla_prof,OU=GRUPOS,OU=SiGLa,DC=thalesalv,DC=es))";

            NamingEnumeration<SearchResult> result = this.dirContext.search(ldap_base, filter, this.searchCtls);
            this.closeLdapConnection();
            
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

                attr = attrs.get("displayName").toString();
                attr = attr.substring(attr.indexOf(":") + 1);
                p.setShownName(attr.trim());

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
    public String getTitle(Pessoa p) throws NamingException {
        String title = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                title = attrs.get("title").toString();
                title = title.substring(title.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return title.replaceAll("^\\s+|\\s+$", "").trim();
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
    public String getDepartment(Pessoa p) throws NamingException {
        String depto = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                depto = attrs.get("department").toString();
                depto = depto.substring(depto.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }
        return depto.trim();
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
    public String getGivenName(Pessoa p) throws NamingException {
        String givenName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                givenName = attrs.get("givenName").toString();
                givenName = givenName.substring(givenName.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return givenName.trim();
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
    public String getMail(Pessoa p) throws NamingException {
        String mail = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                mail = attrs.get("mail").toString();
                mail = mail.substring(mail.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return mail.trim();
    }

    /**
     * Busca o parâmetro <code>manager</code> do <code>Active Directory</code>,
     * que representa o gerente do usuário.
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public String getManager(Pessoa p) throws NamingException {
        String manager = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                manager = attrs.get("manager").toString();
                manager = manager.substring(manager.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return manager.trim();
    }

    /**
     * Busca o parâmetro <code>Company</code> do <code>Active Directory</code>,
     * que representa a empresa na qual o usuário trabalha.
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public String getCompany(Pessoa p) throws NamingException {
        String company = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                company = attrs.get("company").toString();
                company = company.substring(company.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return company.trim();
    }

    /**
     * Busca o parâmetro <code>sAMAccountName</code> do
     * <code>Active Directory</code>, que representa o nome de usuário
     * (pré-Windows 2000).
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public String getsAMAccountName(Pessoa p) throws NamingException {
        String sAMAccountName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                sAMAccountName = attrs.get("sAMAccountName").toString();
                sAMAccountName = sAMAccountName.substring(sAMAccountName.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return sAMAccountName;
    }

    /**
     * Busca o parâmetro <code>displayName</code> do
     * <code>Active Directory</code>, que representa o apelido do usuário
     *
     * @return
     * @param p classe de modelo Pessoa
     * @throws javax.naming.NamingException se uma operação de resolução de
     * nomes falhar
     */
    public String getDisplayName(Pessoa p) throws NamingException {
        String displayName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                displayName = attrs.get("displayName").toString();
                displayName = displayName.substring(displayName.indexOf(":") + 1);
            }
        } catch (Exception e) {
            Logger.logSevere(e, this.getClass());
        }

        return displayName.trim();
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
    public String getOffice(Pessoa p) throws NamingException {
        String physicalDeliveryOfficeName = "";
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
                physicalDeliveryOfficeName = attrs.get("physicalDeliveryOfficeName").toString();
                physicalDeliveryOfficeName = physicalDeliveryOfficeName.substring(physicalDeliveryOfficeName.indexOf(":") + 1);
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
    public byte[] getPicture(Pessoa p) throws NamingException, FileNotFoundException {
        byte[] pic;
        try {
            NamingEnumeration<SearchResult> result = this.searchUser(p);
            this.closeLdapConnection();
            
            if (result.hasMoreElements()) {
                SearchResult sr = (SearchResult) result.next();
                Attributes attrs = sr.getAttributes();
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
