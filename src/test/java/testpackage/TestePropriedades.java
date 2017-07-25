package testpackage;

import util.SiGLa;

public class TestePropriedades {

    public static void main(String[] args) {
        System.out.println("===== DOMÍNIO =====");
        System.out.println("Domínio: " + SiGLa.DOMAIN);
        System.out.println("NetBIOS: " + SiGLa.NETBIOS);
        System.out.println("Método de autenticação: " + SiGLa.AUTH_METHOD);
        
        System.out.println("\n\n===== SiGLa =====");
        System.out.println("Versão: " + SiGLa.VERSION);
        
        System.out.println("\n\n===== CAMINHOS =====");        
        System.out.println("Caminho absoluto: " + SiGLa.HOME);
        System.out.println("Caminho das Bibliotecas: " + SiGLa.LIB);
        System.out.println("Caminho dos Recursos: " + SiGLa.RESOURCE);
        System.out.println("Caminho das Fontes: " + SiGLa.SOURCE);
        System.out.println("Caminho da Compilação: " + SiGLa.TARGET);
        System.out.println("Caminho de Testes: " + SiGLa.TEST);
        System.out.println("Caminho do Webapp: " + SiGLa.WEBAPP);
        
        System.out.println("\n\n===== BANCO DE DADOS =====");
        System.out.println("Banco de Dados: " + SiGLa.DB_NAME);
        System.out.println("Usuário: " + SiGLa.DB_USER);
        System.out.println("Senha: " + SiGLa.DB_PASSWD);
        System.out.println("Host: " + SiGLa.DB_ADDR);
        System.out.println("Porta: " + SiGLa.DB_PORT);
        System.out.println("SSL: " + SiGLa.DB_SSL);
    }
}
