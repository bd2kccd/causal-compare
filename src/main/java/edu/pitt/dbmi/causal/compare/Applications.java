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

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 *
 * Aug 16, 2019 3:05:52 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class Applications {

    private Applications() {
    }

    public static void showHelp(String[] args, Options options) {
        Map<String, String> argsMap = new TreeMap<>(toMapLongOptions(args));

        List<String> optList = new LinkedList<>();
        argsMap.forEach((k, v) -> {
            Option opt = options.getOption(k);
            if (opt != null) {
                optList.add(String.format("--%s", opt.getLongOpt()));
                if (v != null) {
                    optList.add(v);
                }
            }
        });
        String header = optList.stream().collect(Collectors.joining(" "));
        String cmdLineSyntax = String.format("%s %s", getHelpTitle(), header);

        // create new options
        Options helpOpts = new Options();
        options.getOptions().stream()
                .filter(e -> !argsMap.containsKey(e.getLongOpt()))
                .forEach(e -> helpOpts.addOption(e));

        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(-1);
        formatter.printHelp(cmdLineSyntax, helpOpts, true);
    }

    private static String getHelpTitle() {
        String title = jarTitle();
        String version = jarVersion();

        return (title == null || version == null)
                ? "java -jar causal-compare.jar"
                : String.format("java -jar %s-%s.jar", title, version);
    }

    /**
     * Parse the long parameters from the command inputs to map where the
     * parameters are map keys and parameter values are map values.
     *
     * @param args
     * @return
     */
    private static Map<String, String> toMapLongOptions(String[] args) {
        Map<String, String> map = new HashMap<>();

        if (args != null && args.length > 0) {
            String key = null;
            for (String arg : args) {
                if (key != null) {
                    if (arg.startsWith("--")) {
                        map.put(key, null);
                    } else {
                        map.put(key, arg);
                    }
                    key = null;
                }

                if (arg.startsWith("--")) {
                    key = arg.substring(2, arg.length());
                }
            }
            if (key != null) {
                map.put(key, null);
            }
        }

        return map;
    }

    public static String jarTitle() {
        return Applications.class.getPackage().getImplementationTitle();
    }

    public static String jarVersion() {
        String version = Applications.class.getPackage().getImplementationVersion();

        return (version == null) ? "unknown" : version;
    }

}
