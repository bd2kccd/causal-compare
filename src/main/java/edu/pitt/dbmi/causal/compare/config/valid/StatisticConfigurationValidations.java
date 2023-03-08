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

import edu.pitt.dbmi.causal.compare.tetrad.StatisticModels;
import java.util.List;

/**
 *
 * May 21, 2020 4:18:41 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class StatisticConfigurationValidations {

    private StatisticConfigurationValidations() {
    }

    public static void validate(List<String> statistics) throws ValidationException {
        if (statistics == null) {
            throw new ValidationException("Element <statistics> is required.");
        }
        if (statistics.isEmpty()) {
            throw new ValidationException("Parent element <statistics> requires child element <statistic>.");
        }

        StatisticModels statModes = StatisticModels.getInstance();
        for (String stat : statistics) {
            if (stat.isEmpty()) {
                throw new ValidationException("Element <statistic> requires value.");
            }
            if (!statModes.hasClass(stat)) {
                throw new ValidationException(String.format("No such statistic \"%s\".", stat));
            }
        }
    }

}
