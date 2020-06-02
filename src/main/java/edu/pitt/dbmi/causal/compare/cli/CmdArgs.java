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
package edu.pitt.dbmi.causal.compare.cli;

import edu.pitt.dbmi.causal.compare.config.ComparisonConfiguration;
import java.nio.file.Path;

/**
 *
 * Aug 17, 2019 11:49:53 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class CmdArgs {

    protected ComparisonConfiguration configuration;

    protected Path outDirectory;

    protected String fileNamePrefix;

    public CmdArgs() {
    }

    public ComparisonConfiguration getConfiguration() {
        return configuration;
    }

    public Path getOutDirectory() {
        return outDirectory;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

}
