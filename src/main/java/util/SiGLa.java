package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

public class SiGLa {

    private static Properties props;
    private static ClassLoader loader;
    private static InputStream resourceStream;
    public static String HOME = "";
    public static String TEST = "";
    public static String SOURCE = "";
    public static String RESOURCE = "";
    public static String LIB = "";
    public static String TARGET = "";
    public static String WEBAPP = "";
    public static String DOMAIN = "";
    public static String DOMAIN_HOST = "";
    public static String NETBIOS = "";
    public static String AUTH_METHOD = "";
    public static String VERSION = "";
    public static String DB_NAME = "";
    public static String DB_USER = "";
    public static String DB_PASSWD = "";
    public static String DB_ADDR = "";
    public static String DB_PORT = "";
    public static String DB_SSL = "";
    public static String KEYSTORE = "";

    static {
        try {
            loader = Thread.currentThread().getContextClassLoader();           
                        
            /* Propriedades do Sistema */   
            resourceStream = loader.getResourceAsStream("app.properties");
            props = new Properties();
            props.load(resourceStream);
            VERSION = (String) props.get("sigla.info.version");
            KEYSTORE = (String) props.get("sigla.dir.keystore");
            KEYSTORE = KEYSTORE.replace("\\", "/");
            HOME = (String) props.get("sigla.dir.home");
            HOME = HOME.replace("\\", "/");
            TEST = (String) props.get("sigla.dir.test");
            TEST = TEST.replace("\\", "/");
            SOURCE = (String) props.get("sigla.dir.source");
            SOURCE = SOURCE.replace("\\", "/");
            RESOURCE = (String) props.get("sigla.dir.resource");
            RESOURCE = RESOURCE.replace("\\", "/");
            LIB = (String) props.get("sigla.dir.lib");
            LIB = LIB.replace("\\", "/");
            TARGET = (String) props.get("sigla.dir.target");
            TARGET = TARGET.replace("\\", "/");
            WEBAPP = (String) props.get("sigla.dir.webapp");
            WEBAPP = WEBAPP.replace("\\", "/");
            
            /* Propriedades do Banco de Dados */ 
            resourceStream = loader.getResourceAsStream("db.properties");
            props = new Properties();
            props.load(resourceStream);
            DB_NAME = (String) props.get("sigla.db.name");
            DB_USER = (String) props.get("sigla.db.user");
            DB_PASSWD = (String) props.get("sigla.db.passwd");
            DB_ADDR = (String) props.get("sigla.db.addr");
            DB_PORT = (String) props.get("sigla.db.port");
            DB_SSL = (String) props.get("sigla.db.ssl");

            /* Propriedades de Autenticação */
            resourceStream = loader.getResourceAsStream("auth.properties");
            props = new Properties();
            props.load(resourceStream);
            DOMAIN = (String) props.get("sigla.auth.domain");
            DOMAIN = DOMAIN.trim();
            DOMAIN_HOST = (String) props.get("sigla.auth.host");
            DOMAIN_HOST = DOMAIN_HOST.trim();
            NETBIOS = (String) props.get("sigla.auth.netbios");
            NETBIOS = NETBIOS.trim();
            AUTH_METHOD = (String) props.get("sigla.auth.method");
            AUTH_METHOD = AUTH_METHOD.trim();            
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());
        }
    }

    public static void writeProperty(String cfg, String key, String data) {
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties configProperty = new Properties();

            File file = new File(SOURCE + "/resources/" + cfg + ".properties");
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(key, data);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, null);

            fileOut.close();
        } catch (Exception e) {
            Logger.logSevere(e, e.getClass());
        }
    }
}
