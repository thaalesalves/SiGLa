/*
 * Copyright (C) 2017 thaal
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ad_test;

import dao.DAOFactory;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.naming.AuthenticationException;
import javax.naming.NamingException;
import model.Grupo;
import model.Pessoa;
import util.ActiveDirectory;

public class AccessLevelTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NamingException, AuthenticationException {
        Pessoa p = new Pessoa();
        ActiveDirectory ad = new ActiveDirectory();
        DAOFactory fac = DAOFactory.getFactory();
        ArrayList<Grupo> arrayg = fac.getGrupoDAO().select();
        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);
        String grupo = "";

        out.print("Usuário: ");
        p.setUsername(in.nextLine());
        out.print("Senha: ");
        p.setSenha(in.nextLine());

        if (ad.login(p)) {
            for (Grupo g : arrayg) {
                if (ad.isMember(p, g)) {
                    p.setRole(g.getRole());
                    grupo = g.getRole();
                    
                    out.println("Login feito com o usuário " + p.getUsername() + ", membro do grupo " + grupo);
                    
                    break;
                } else {
                    out.print("Login feito com o usuário " + p.getUsername() + ", mas nenhum grupo válido");
                }
            }            
        } else {
            out.println("Login falhou");
        }
    }
}
