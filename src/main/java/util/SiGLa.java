package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public final class SiGLa {
// <editor-fold defaultstate="collapsed" desc="Inicializador e constantes">
    public static final String HOME;
    public static final String TEST;
    public static final String SOURCE;
    public static final String RESOURCE;
    public static final String LIB;
    public static final String TARGET;
    public static final String WEBAPP;
    public static final String VERSION;
    public static final String KEYSTORE;
    public static final String LOGS;

    static {
        Properties app = new Properties();

        try {
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("sigla.properties");
            app.load(stream);
        } catch (Exception e) {
            util.Logger.logSevere(e, SiGLa.class);
        }

        VERSION = (String) app.get("sigla.info.version");
        KEYSTORE = formatURI((String) app.get("sigla.dir.keystore"));
        HOME = formatURI((String) app.get("sigla.dir.home"));
        TEST = formatURI((String) app.get("sigla.dir.test"));
        SOURCE = formatURI((String) app.get("sigla.dir.source"));
        RESOURCE = formatURI((String) app.get("sigla.dir.resource"));
        LIB = formatURI((String) app.get("sigla.dir.lib"));
        TARGET = formatURI((String) app.get("sigla.dir.target"));
        WEBAPP = formatURI((String) app.get("sigla.dir.webapp"));
        LOGS = formatURI((String) app.get("sigla.dir.logs"));

        SiGLa.loadProperties();
    }      // </editor-fold>

    private static String domain;
    private static String domainHost;
    private static String netbios;
    private static String authMethod;
    private static String authPort;
    private static String dbUser;
    private static String dbName;
    private static String dbPasswd;
    private static String dbAddr;
    private static String dbPort;
    private static String dbSsl;
    private static String dbDbms;
    private static String mailName;
    private static String mailGroup;
    private static String mailSystem;

    private SiGLa() {
        throw new AssertionError();
    }

    private static String formatURI(String uri) {
        return uri.replace("\\", "/");
    }

    public static void loadProperties() {
        try {
            Properties cfg = new Properties();
            InputStream stream = new FileInputStream(TARGET + "/classes/config.properties");
            cfg.load(stream);
            
            domain = (String) cfg.get("sigla.auth.domain");
            domainHost = (String) cfg.get("sigla.auth.host");
            domainHost = domainHost.replaceAll("\\\\", "");
            netbios = (String) cfg.get("sigla.auth.netbios");
            authMethod = (String) cfg.get("sigla.auth.method");
            authPort = (String) cfg.get("sigla.auth.port");

            dbName = (String) cfg.get("sigla.db.name");
            dbUser = (String) cfg.get("sigla.db.user");
            dbPasswd = (String) cfg.get("sigla.db.passwd");
            dbAddr = (String) cfg.get("sigla.db.addr");
            dbAddr = dbAddr.replaceAll("\\\\", "");
            dbPort = (String) cfg.get("sigla.db.port");
            dbSsl = (String) cfg.get("sigla.db.ssl");
            dbDbms = (String) cfg.get("sigla.db.dbms");
            
            mailName = (String) cfg.get("sigla.mail.name");
            mailGroup = (String) cfg.get("sigla.mail.group");
            mailSystem = (String) cfg.get("sigla.mail.system");
        } catch (Exception e) {
            util.Logger.logSevere(e, SiGLa.class);
        }
    }

    public static void writeProperty(String key, String value) {
        try {
            Properties configProperty = new Properties();

            File file = new File(TARGET + "/classes/config.properties");
            FileInputStream fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(key, value);
            FileOutputStream fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, null);
            
            fileOut.close();

            loadProperties();
        } catch (Exception e) {
            Logger.logSevere(e, SiGLa.class);
        }
    }

    public static String getDbms() {
        return dbDbms;
    }
    
    public static String getDomain() {
        return domain;
    }

    public static String getDomainHost() {
        return domainHost;
    }

    public static String getNetbios() {
        return netbios;
    }

    public static String getAuthMethod() {
        return authMethod;
    }
    
    public static String getAuthPort() {
        return authPort;
    }

    public static String getDbUser() {
        return dbUser;
    }

    public static String getDbName() {
        return dbName;
    }

    public static String getDbPasswd() {
        return dbPasswd;
    }

    public static String getDbAddr() {
        return dbAddr;
    }

    public static String getDbPort() {
        return dbPort;
    }

    public static String getDbSsl() {
        return dbSsl;
    } 

    public static String getDbDbms() {
        return dbDbms;
    }

    public static String getMailName() {
        return mailName;
    }

    public static String getMailGroup() {
        return mailGroup;
    }

    public static String getMailSystem() {
        return mailSystem;
    }
}
