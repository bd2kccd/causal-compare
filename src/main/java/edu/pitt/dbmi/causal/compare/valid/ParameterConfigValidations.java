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

import edu.pitt.dbmi.causal.compare.conf.ParameterConfig;
import edu.pitt.dbmi.causal.compare.tetrad.ParameterModels;
import java.util.List;

/**
 *
 * Aug 27, 2019 4:08:08 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ParameterConfigValidations {

    private ParameterConfigValidations() {
    }

    public static void validate(List<ParameterConfig> configs) throws ValidationException {
        if (configs != null) {
            if (configs.isEmpty()) {
                throw new ValidationException("Parent element <parameters> requires child element <parameter>.");
            }

            ParameterModels paramDescs = ParameterModels.getInstance();
            for (ParameterConfig config : configs) {
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

}
