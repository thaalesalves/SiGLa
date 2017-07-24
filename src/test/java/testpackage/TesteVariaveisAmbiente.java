package testpackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TesteVariaveisAmbiente {

    public static void main(String[] args) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        InputStream resourceStream = loader.getResourceAsStream("app.properties");
        props.load(resourceStream);
        String siglaDir = (String) props.get("sigla.home");
        siglaDir = siglaDir.replace("\\", "/");

        System.out.println("Caminho: " + siglaDir);
        
        System.out.println("Caminho do projeto: " + System.getProperty("user.dir"));
        System.out.println("Sistema operacional: " + System.getProperty("os.name"));

        String operatingSystem = System.getProperty("os.name").toLowerCase();

        if (operatingSystem.equals("win")) {
            System.out.println("É Windows!");
        } else if (operatingSystem.equals("mac")) {
            System.out.println("É Mac!");
        } else if (operatingSystem.equals("nix") || operatingSystem.equals("nux") || operatingSystem.equals("aix")) {
            System.out.println("É Linux!");
        }
    }
}
