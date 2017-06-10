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
package JNA;

import com.sun.jna.platform.win32.Advapi32Util;

/**
 *
 * @author thalespereira
 */
public class App {
    public static void main(String[] args) {
        for (Advapi32Util.Account account : Advapi32Util.getCurrentUserGroups()) {
                System.out.println(account.fqn);
            }
    }
}
