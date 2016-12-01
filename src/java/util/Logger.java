/**
* Copyright (C) 2016 Thales Alves Pereira
* 
*  This file is part of SiGla.

*   SiGla is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.

*   SiGla is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.

*   You should have received a copy of the GNU General Public License
*   along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
**/

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
