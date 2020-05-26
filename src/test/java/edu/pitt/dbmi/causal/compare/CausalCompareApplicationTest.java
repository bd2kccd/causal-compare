/*
 * Copyright (C) 2020 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.pitt.dbmi.causal.compare;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 *
 * Aug 14, 2019 4:56:23 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class CausalCompareApplicationTest {

    @TempDir
    public static Path dirOut;

    @Test
    public void testCompareFromGraph() {
        String configFile = getClass().getResource("/data/compare_graphs.xml").getFile();
        String prefix = "test_compare";
        String[] args = {
            "--config", configFile,
            "--prefix", prefix,
            "--out", dirOut.toString()
        };
        CausalCompareApplication.main(args);
    }

    /**
     * Test of main method, of class CausalCompareApplication.
     */
    @Test
    public void testCompareFromSearch() {
        String configFile = getClass().getResource("/data/compare_search.xml").getFile();
        String prefix = "test_compare";
        String[] args = {
            "--config", configFile,
            "--prefix", prefix,
            "--out", dirOut.toString()
        };
        CausalCompareApplication.main(args);
    }

}
