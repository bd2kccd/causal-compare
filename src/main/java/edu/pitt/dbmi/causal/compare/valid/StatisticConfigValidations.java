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
package edu.pitt.dbmi.causal.compare.valid;

import edu.pitt.dbmi.causal.compare.tetrad.StatisticModels;
import java.util.List;

/**
 *
 * Aug 26, 2019 6:01:46 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class StatisticConfigValidations {

    private StatisticConfigValidations() {
    }

    public static void validate(List<String> stats) throws ValidationException {
        if (stats == null) {
            throw new ValidationException("Element <statistics> is required.");
        }
        if (stats.isEmpty()) {
            throw new ValidationException("Parent element <statistics> requires child element <statistic>.");
        }

        StatisticModels statModes = StatisticModels.getInstance();
        for (String stat : stats) {
            if (stat.isEmpty()) {
                throw new ValidationException("Element <statistic> requires value.");
            }
            if (!statModes.hasClass(stat)) {
                throw new ValidationException(String.format("No such statistic \"%s\".", stat));
            }
        }
    }

}
