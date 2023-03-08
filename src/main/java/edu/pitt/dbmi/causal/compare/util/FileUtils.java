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
package edu.pitt.dbmi.causal.compare.util;

import edu.cmu.tetrad.graph.Graph;
import edu.cmu.tetrad.graph.GraphPersistence;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 *
 * May 24, 2020 2:20:37 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static Graph readGraph(Path file) throws IOException {
        try (Reader reader = Files.newBufferedReader(file)) {
            return GraphPersistence.readerToGraphTxt(reader);
        }
    }

    public static void deleteNonemptyDirectory(Path dir) throws IOException {
        Files.walk(dir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

}
