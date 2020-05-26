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

import edu.pitt.dbmi.causal.compare.config.Property;
import edu.pitt.dbmi.causal.compare.tetrad.ComparisonProperties;
import java.util.List;
import java.util.regex.Pattern;

public final class PropertyConfigurationValidations {

    private static final Pattern DELIM = Pattern.compile(",");

    private PropertyConfigurationValidations() {
    }

    public static void validate(List<Property> properties) throws ValidationException {
        if (properties == null) {
            return;
        }
        if (properties.isEmpty()) {
            throw new ValidationException("Parent element <comparison> requires child element <property>.");
        }

        ComparisonProperties comparisonProps = ComparisonProperties.getInstance();
        for (Property prop : properties) {
            String name = prop.getName();
            if (name == null) {
                throw new ValidationException("Element <property> requires 'name' attribute.");
            }
            if (name.trim().isEmpty()) {
                throw new ValidationException("Attribute 'name' requires value.");
            }

            if (!comparisonProps.hasProperty(name)) {
                throw new ValidationException(String.format("No such property \"%s\".", name));
            }

            String value = prop.getValue();
            if (value == null || value.trim().isEmpty()) {
                throw new ValidationException("Element <property> requires value.");
            } else {
                String[] values = DELIM.split(value.trim().toLowerCase());
            }
        }
    }

}
