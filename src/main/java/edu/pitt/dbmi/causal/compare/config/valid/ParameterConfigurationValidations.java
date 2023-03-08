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

import edu.pitt.dbmi.causal.compare.config.ParameterConfiguration;
import edu.pitt.dbmi.causal.compare.tetrad.ParameterModels;
import java.util.List;

/**
 *
 * Aug 27, 2019 4:08:08 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ParameterConfigurationValidations {

    private ParameterConfigurationValidations() {
    }

    public static void validate(List<ParameterConfiguration> configs) throws ValidationException {
        if (configs == null) {
            return;
        }

        if (configs.isEmpty()) {
            throw new ValidationException("Parent element <parameters> requires child element <parameter>.");
        }

        ParameterModels paramDescs = ParameterModels.getInstance();
        for (ParameterConfiguration config : configs) {
            String name = config.getName();
            if (name == null) {
                throw new ValidationException("Element <parameter> requires 'name' attribute.");
            }
            if (name.trim().isEmpty()) {
                throw new ValidationException("Attribute 'name' requires value.");
            }

            if (!paramDescs.hasParameter(name)) {
                throw new ValidationException(String.format("No such parameter \"%s\".", name));
            }

            String value = config.getValue();
            if (value == null || value.trim().isEmpty()) {
                throw new ValidationException("Element <parameter> requires value.");
            }
        }
    }

}
