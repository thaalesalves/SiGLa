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
package dbms_test;

import dao.DAOFactory;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Grupo;
import model.Laboratorio;
import util.SiGLa;

public class DAOTest {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);
        boolean menu = true;

        do {
            out.println("=============== MENU ===============");
            out.println("1. Configurar SGDB");
            out.println("2. Selecionar");
            out.println("3. Inserir");
            out.println("4. Selecionar");
            out.println("5. Inserir");
            out.println("====================================");
            out.print("Digite um número: ");
            Integer opt = in.nextInt();
            menu = false;
            out.println();

            switch (opt) {
                case 1: {
                    try {
                        out.print("SGBD: ");
                        String dbms = in.next();
                        out.print("Banco: ");
                        String db = in.next();
                        out.print("Usuário: ");
                        String user = in.next();
                        out.print("Senha: ");
                        String pwd = in.next();
                        out.print("Host: ");
                        String host = in.next();

                        SiGLa.writeProperty("sigla.db.name", db);
                        SiGLa.writeProperty("sigla.db.user", user);
                        SiGLa.writeProperty("sigla.db.passwd", pwd);
                        SiGLa.writeProperty("sigla.db.addr", host);
                        SiGLa.writeProperty("sigla.db.dbms", dbms);

                        util.DatabaseConnection.checkDatabase();
                    } catch (Exception e) {
                        util.Logger.logSevere(e, DAOTest.class);
                    }

                    break;
                }

                case 2: {
                    DAOFactory dao = DAOFactory.getFactory();
                    ArrayList<Laboratorio> labs = dao.getLaboratorioDAO().selectLaboratorios();

                    for (Laboratorio i : labs) {
                        out.println("Laboratório nº" + labs.indexOf(i) + ": " + i.getNumero());
                    }

                    break;
                }

                case 3: {
                    DAOFactory dao = DAOFactory.getFactory();
                    Laboratorio lab = new Laboratorio();

                    out.print("Digite o número do laboratório: ");
                    lab.setNumero(in.next());
                    out.print("Digite a capacidade de alunos do laboratório: ");
                    lab.setCapacidade(in.nextInt());
                    out.print("Digite a quantidade de computadores do laboratório: ");
                    lab.setComputadores(in.nextInt());

                    dao.getLaboratorioDAO().insertLaboratorio(lab);

                    break;
                }
                
                case 4: {
                    DAOFactory dao = DAOFactory.getFactory();
                    ArrayList<Grupo> grupos = dao.getGrupoDAO().select();

                    for (Grupo i : grupos) {
                        out.println("Grupo nº" + grupos.indexOf(i) + ": " + i.getGrupo());
                    }

                    break;
                }

                case 5: {
                    DAOFactory dao = DAOFactory.getFactory();
                    Grupo g = new Grupo();

                    out.print("Digite o nome do grupo: ");
                    g.setGrupo(in.next());
                    out.print("Digite a função do grupo: ");
                    g.setRole(in.next());

                    dao.getGrupoDAO().insert(g);

                    break;
                }
            }

            menu = true;
            out.println("\n\n\n");
        } while (menu);
    }
}
