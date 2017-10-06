/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.PrintStream;
import java.util.Scanner;

public class IO {

    static Scanner in = new Scanner(System.in);
    static PrintStream out = new PrintStream(System.out);

    public static String read(String value) {
        return in.next();
    }

    public static String readln(String value) {
        return in.nextLine();
    }

    public static String read(Integer value) {
        return in.next();
    }

    public static String readln(Integer value) {
        return in.nextLine();
    }

    public static String read(Double value) {
        return in.next();
    }

    public static String readln(Double value) {
        return in.nextLine();
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

    public static void write(Double value) {
        out.print(value);
    }

    public static void writeln(Double value) {
        out.println(value);
    }
}
