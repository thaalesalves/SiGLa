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

import java.util.logging.Level;

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
        LOGGER.log(Level.SEVERE, "\n=== ERRO ===\nErro em " + c.toString() + ": " + t.getMessage() + "\nExceção lançada: " + t + "\nLinha: " + t.getStackTrace()[0].getLineNumber() + "\n=== FIM DO ERRO ===", t);
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
        LOGGER.log(Level.WARNING, "\n=== ERRO ===\nErro em " + c.toString() + ": " + t.getMessage() + "\nExceção lançada: " + t + "\nLinha: " + t.getStackTrace()[0].getLineNumber() + "\n=== FIM DO ERRO ===", t);
    }
}
