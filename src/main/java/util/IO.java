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

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class IO {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);

    public static String read() {
        return in.next();
    }

    public static String readln() {
        return in.nextLine();
    }

    public static Integer readInt() {
        return in.nextInt();
    }

    public static Float readFloat() {
        return in.nextFloat();
    }

    public static Double readDouble() {
        return in.nextDouble();
    }

    public static void writeln(Date value) {
        out.println(value);
    }

    public static void write(Date value) {
        out.print(value);
    }

    public static void writeln(String[] value) {
        out.println(value);
    }

    public static void write(String[] value) {
        out.print(value);
    }

    public static void write(String value) {
        out.print(value);
    }

    public static void writeln(String value) {
        out.println(value);
    }

    public static void write(Integer value) {
        out.print(value);
    }

    public static void writeln(Integer value) {
        out.println(value);
    }

    public static void write(Float value) {
        out.print(value);
    }

    public static void writeln(Float value) {
        out.println(value);
    }

    public static void write(Double value) {
        out.print(value);
    }

    public static void writeln(Double value) {
        out.println(value);
    }

    public static String formatData(String data) throws ParseException {
        if (data == null) {
            return null;
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(formato.parse(data));
    }

    public static String formatData(Date date) throws ParseException {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        return data.format(cal.getTime());
    }
    
    public static String formatDataHora(Date date) throws ParseException {
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        return data.format(cal.getTime());
    }

    public static Date getDataHora(String data) throws ParseException {
        if (data == null) {
            return null;
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formato.parse(data);
    }

    public static Date getData(String data) throws ParseException {
        if (data == null) {
            return null;
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.parse(data);
    }
}
