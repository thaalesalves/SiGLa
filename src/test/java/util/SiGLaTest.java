/*
 * Copyright (C) 2018 balaio2
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
package util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SiGLaTest {
    
    @Test
    public void testLoadProperties() {
        SiGLa.writeProperty("sigla.auth.host", "ec2-52-67-45-76.sa-east-1.compute.amazonaws.com");
        SiGLa.loadProperties();
        assertEquals("ec2-52-67-45-76.sa-east-1.compute.amazonaws.com", SiGLa.getDomainHost());
    }
}
