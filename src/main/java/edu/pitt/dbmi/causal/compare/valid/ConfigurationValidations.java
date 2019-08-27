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

import edu.pitt.dbmi.causal.compare.conf.Configuration;

/**
 *
 * Aug 24, 2019 11:54:46 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ConfigurationValidations {

    private ConfigurationValidations() {
    }

    public static void validate(Configuration config) throws ValidationException {
        SimulationConfigValidations.validate(config.getSimulationConfigs());
        AlgorithmConfigValidations.validate(config.getAlgorithmConfigs());
        StatisticConfigValidations.validate(config.getStatistics());
        ParameterConfigValidations.validate(config.getParameters());
    }

}
