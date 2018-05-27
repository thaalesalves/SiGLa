/*
 * Copyright (C) 2018 clouddog_04
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package util.timer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import util.Logger;

public class Timer {

    public static void main(String[] args) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);

            executorService.execute(() -> {
                VencimentoLicenca.main(null);
            });
        } catch (Exception e) {
            Logger.logSevere(e, Timer.class);
        }
    }
}
