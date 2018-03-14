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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import static util.SiGLa.TARGET;

public class Logger {

    private final static java.util.logging.Logger LOGGER = java.util.logging.Logger.getAnonymousLogger();

    /**
     * Método que gera um log sobre um erro, e lança a pilha do erro na saída do
     * programa. Serve para gerar logs de erros severos
     *
     * @author Thales Alves Pereira
     * @version 1.0
     * @param t <code>Throwable</code> a ser lançado. Normalmente um
     * <code>Exception</code>
     * @param c Classe na qual o método foi chamado. Utilizado como parâmetro de
     * linha que deu erro.
     */
    public static void logSevere(Throwable t, Class c) {
        try {
            String message = "\n=== ERRO ===\nErro em " + c.toString() + ": "
                    + t.getMessage() + "\nExceção lançada: " + t + "\nLinha: "
                    + t.getStackTrace()[0].getLineNumber() + "\n=== FIM DO ERRO ===\n";

            LOGGER.log(Level.SEVERE, message, t);

            File file = new File(SiGLa.LOGS + "/stderr.log");
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file, true);
            fileOut.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que gera um log sobre um erro, e lança a pilha do erro na saída do
     * programa. Serve para gerar logs de erros não importantes
     *
     * @author Thales Alves Pereira
     * @version 1.0
     * @param t <code>Throwable</code> a ser lançado. Normalmente um
     * <code>Exception</code>
     * @param c Classe na qual o método foi chamado. Utilizado como parâmetro de
     * linha que deu erro.
     */
    public static void logWarning(Throwable t, Class c) {
        try {
            String message = "\n=== ERRO ===\nErro em " + c.toString() + ": "
                    + t.getMessage() + "\nExceção lançada: " + t + "\nLinha: "
                    + t.getStackTrace()[0].getLineNumber() + "\n=== FIM DO ERRO ===\n";

            LOGGER.log(Level.WARNING, message, t);

            File file = new File(SiGLa.LOGS + "/stderr.log");
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file, true);
            fileOut.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método que gera um log de informação e o joga no arquivo de saída
     *
     * @author Thales Alves Pereira
     * @version 1.0
     * @param message <code>String</code> com a mensagem a ser exibida
     * @throws java.io.FileNotFoundException
     */
    public static void logOutput(String message) {
        try {
            message += "\n";
            String timeStamp = "[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()) + "]: ";
            message = timeStamp + message;
            LOGGER.log(Level.INFO, message);

            File file = new File(SiGLa.LOGS + "/stdout.log");
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file, true);
            fileOut.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
