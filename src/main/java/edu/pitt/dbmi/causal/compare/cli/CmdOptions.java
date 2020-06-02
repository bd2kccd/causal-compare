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

import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 *
 * Aug 16, 2019 11:35:28 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class CmdOptions {

    private final Map<String, Option> options = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private final static CmdOptions INSTANCE = new CmdOptions();

    private CmdOptions() {
        addRequiredOptions();
        addOptionalOptions();
    }

    public static CmdOptions getInstance() {
        return INSTANCE;
    }

    public Options getOptions() {
        Options opts = new Options();
        options.entrySet().forEach(e -> {
            opts.addOption(e.getValue());
        });

        return opts;
    }

    private void addRequiredOptions() {
        options.put(CmdParams.CONFIG_FILE, Option.builder().longOpt(CmdParams.CONFIG_FILE).desc("XML configuration file.").hasArg().argName("string").required().build());
        options.put(CmdParams.DIR_OUT, Option.builder().longOpt(CmdParams.DIR_OUT).desc("Output directory").hasArg().argName("directory").build());
    }

    private void addOptionalOptions() {
        options.put(CmdParams.FILENAME_PREFIX, Option.builder().longOpt(CmdParams.FILENAME_PREFIX).desc("Prefix filename of output files.").hasArg().argName("string").build());
    }

}
