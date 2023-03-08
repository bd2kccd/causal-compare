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
package edu.pitt.dbmi.causal.compare.config.valid;

import edu.pitt.dbmi.causal.compare.config.CompareByConfiguration;
import edu.pitt.dbmi.causal.compare.config.GraphConfiguration;
import edu.pitt.dbmi.causal.compare.config.SearchConfiguration;

/**
 *
 * May 21, 2020 4:09:20 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class CompareByConfigurationValidations {

    private CompareByConfigurationValidations() {
    }

    public static void validate(CompareByConfiguration config) throws ValidationException {
        if (config == null) {
            throw new ValidationException("Element <compareBy> is required.");
        }

        SearchConfiguration search = config.getSearch();
        GraphConfiguration graph = config.getGraph();
        if (search == null && graph == null) {
            throw new ValidationException("Missing either <search> or <graph> element.");
        } else if (!(search == null ^ graph == null)) {
            throw new ValidationException("Required either <search> or <graph>, not both.");
        }

        SearchConfigurationValidations.validate(search);
        GraphConfigurationValidations.validate(graph);
    }

}
