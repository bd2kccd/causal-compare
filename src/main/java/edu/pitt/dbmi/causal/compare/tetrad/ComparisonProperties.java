/*
 * Copyright (C) 2019 University of Pittsburgh.
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
package edu.pitt.dbmi.causal.compare.tetrad;

import edu.cmu.tetrad.algcomparison.Comparison;
import edu.pitt.dbmi.causal.compare.conf.Property;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Aug 28, 2019 2:37:35 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ComparisonProperties {

    private static final ComparisonProperties INSTANCE = new ComparisonProperties();

    public static final String SHOW_ALGORITHM_INDICES = "showalgorithmindices";
    public static final String SHOW_SIMULATION_INDICES = "showsimulationindices";
    public static final String SORT_BY_UTILITY = "sortbyutility";
    public static final String SHOW_UTILITIES = "showutilities";
    public static final String SAVE_SEARCH_GRAPHS = "savesearchgraphs";
    public static final String TAB_DELIMITED_TABLES = "tabdelimitedtables";

    private final Set<String> properties = new HashSet<>(Arrays.asList(
            SHOW_ALGORITHM_INDICES,
            SHOW_SIMULATION_INDICES,
            SORT_BY_UTILITY,
            SHOW_UTILITIES,
            SAVE_SEARCH_GRAPHS,
            TAB_DELIMITED_TABLES
    ));

    public ComparisonProperties() {
    }

    public static ComparisonProperties getInstance() {
        return INSTANCE;
    }

    public boolean hasProperty(String prop) {
        return (prop == null) ? false : properties.contains(prop.trim().toLowerCase());
    }

    public Comparison create(List<Property> props) {
        Comparison comparison = new Comparison();

        props.forEach(e -> {
            switch (e.getName().trim().toLowerCase()) {
                case SHOW_ALGORITHM_INDICES:
                    comparison.setShowAlgorithmIndices(Boolean.valueOf(e.getValue().trim()));
                    break;
                case SHOW_SIMULATION_INDICES:
                    comparison.setShowSimulationIndices(Boolean.valueOf(e.getValue().trim()));
                    break;
                case SORT_BY_UTILITY:
                    comparison.setSortByUtility(Boolean.valueOf(e.getValue().trim()));
                    break;
                case SHOW_UTILITIES:
                    comparison.setShowUtilities(Boolean.valueOf(e.getValue().trim()));
                    break;
                case SAVE_SEARCH_GRAPHS:
                    comparison.setSaveGraphs(Boolean.valueOf(e.getValue().trim()));
                    break;
                case TAB_DELIMITED_TABLES:
                    comparison.setTabDelimitedTables(Boolean.valueOf(e.getValue().trim()));
                    break;
            }
        });

        return comparison;
    }

}
