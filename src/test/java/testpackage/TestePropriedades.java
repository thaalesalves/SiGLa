package testpackage;

import java.io.PrintStream;
import java.util.Properties;
import java.util.Scanner;
import util.SiGLa;

public class TestePropriedades {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);

        do {
            try {
                out.println("===== MENU =====");
                out.println("0. Fechar");
                out.println("1. Ler propriedades");
                out.println("2. Escrever propriedades");
                out.println("================");

                out.print("Selecione uma opção: ");
                int opt = in.nextInt();

                switch (opt) {
                    case 0: {
                        System.exit(0);
                        break;
                    }
                    case 1: {
                        out.println("SiGLa home (sigla.dir.home): " + SiGLa.HOME);
                        out.println("SiGLa home (sigla.dir.home): " + SiGLa.KEYSTORE);
                        out.println("Domínio (sigla.auth.domain): " + SiGLa.getDomain());
                        out.println("Host (sigla.auth.host): " + SiGLa.getDomainHost());
                        out.println("NetBIOS (sigla.auth.netbios): " + SiGLa.getNetbios());
                        out.println("Método (sigla.auth.method): " + SiGLa.getAuthMethod());
                        break;
                    }
                    case 2: {
                        try {
                            Properties props = new Properties();
                            out.print("Nome da propriedade: ");
                            String prop = in.next();
                            out.print("Valor: ");
                            String value = in.next();

                            SiGLa.writeProperty(prop, value);
                        } catch (Exception e) {
                            util.Logger.logSevere(e, TestePropriedades.class);
                        }
                        break;
                    }
                    default:
                        break;
                }
            } catch (Exception e) {
                util.Logger.logSevere(e, TestePropriedades.class);
            }
        } while (1 == 1);
    }
}
