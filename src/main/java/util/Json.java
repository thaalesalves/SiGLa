/*
 * Copyright (C) 2017 Thales Alves Pereira
 *
 * This file is part of SiGLa.

 * SiGLa is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * SiGLa  is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with SiGLa.  If not, see <http://www.gnu.org/licenses/>.
 */
package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Json {

    /**
     * Método que transforma um <code>Object</code> (uma classe inteira) em uma
     * <code>String</code> em formato JSON.
     *
     * @param obj Classe que será transformada em JSON
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();

        return gson.toJson(obj);
    }
    
    /**
     * Método que transforma um <code>Object</code> (uma classe inteira) em uma
     * <code>String</code> em formato JSON prettificado.
     *
     * @param obj Classe que será transformada em JSON
     * @return
     */
    public static String toPrettyJson(Object obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        return gson.toJson(obj);
    }

    /**
     * Método para "embelezar" um <code>String</code> JSON.
     *
     * @param json
     * @return
     */
    public static String beautify(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

        return gson.toJson(json);
    }

    /**
     * Método que formata um <code>String</code> e o transforma em um
     * <code>String</code> em formato JSON.
     *
     * @param json <code>String</code> que será formatada para JSON
     * @return
     */
    public static String toJson(String json) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(json);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } catch (Exception e) {
            Logger.logSevere(e, Json.class);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return null;
    }
}
