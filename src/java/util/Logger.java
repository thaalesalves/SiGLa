package util;

import java.util.logging.Level;

public class Logger {

    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getAnonymousLogger();

    public static void logWarning(Exception e, Class obj) {
        LOGGER.log(Level.WARNING, "Erro em " + obj.getName() + ": " + e.getMessage(), e);
    }

    public static void logSevere(Exception e, Class obj) {
        LOGGER.log(Level.SEVERE, "Erro em " + obj.getName() + ": " + e.getMessage(), e);
    }

    public static void logSevere(Exception e) {
        LOGGER.log(Level.SEVERE, "Erro: " + e.getMessage(), e);
    }

    public static void logWarning(Exception e) {
        LOGGER.log(Level.WARNING, "Erro: " + e.getMessage(), e);
    }
}
