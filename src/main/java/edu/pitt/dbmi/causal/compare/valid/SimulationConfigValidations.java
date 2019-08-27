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

import edu.pitt.dbmi.causal.compare.conf.SimulationConfig;
import edu.pitt.dbmi.causal.compare.conf.SimulationSource;
import edu.pitt.dbmi.causal.compare.tetrad.SimulationGraphTypes;
import edu.pitt.dbmi.causal.compare.tetrad.SimulationModelTypes;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Aug 26, 2019 12:03:09 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class SimulationConfigValidations {

    private SimulationConfigValidations() {
    }

    public static void validate(List<SimulationConfig> configs) throws ValidationException {
        if (configs == null) {
            throw new ValidationException("Element <simulations> is required.");
        }
        if (configs.isEmpty()) {
            throw new ValidationException("Parent element <simulations> requires child element <simulation>.");
        }
        for (SimulationConfig config : configs) {
            SimulationSource source = config.getSource();
            if (source == null) {
                String values = Arrays.stream(SimulationSource.values()).map(SimulationSource::name).collect(Collectors.joining(","));
                String errMsg = String.format("Element <simulation> requires 'source' attribute and one of the following values: %s.", values);
                throw new ValidationException(errMsg);
            }

            switch (source) {
                case generate:
                    validateGraphType(config.getGraphType());
                    validateModelType(config.getModelType());
                    break;
                case file:
                    validateDataFile(config.getDataFile());
                    validateTrueGraphFile(config.getTrueGraphFile());
                    break;
            }
        }
    }

    private static void validateTrueGraphFile(String trueGraphFile) throws ValidationException {
        if (trueGraphFile == null) {
            throw new ValidationException("Element <truegraph> is required for simulation source of type 'file'.");
        }
        trueGraphFile = trueGraphFile.trim().toLowerCase();
        if (trueGraphFile.isEmpty()) {
            throw new ValidationException("Element <truegraph> requires value.");
        }

        validateFile(Paths.get(trueGraphFile));
    }

    private static void validateDataFile(String dataFile) throws ValidationException {
        if (dataFile == null) {
            throw new ValidationException("Element <data> is required for simulation source of type 'file'.");
        }

        dataFile = dataFile.trim().toLowerCase();
        if (dataFile.isEmpty()) {
            throw new ValidationException("Element <data> requires value.");
        }

        validateFile(Paths.get(dataFile));
    }

    private static void validateModelType(String modelType) throws ValidationException {
        if (modelType == null) {
            throw new ValidationException("Element <modelType> is required for simulation source of type 'generate'.");
        }

        modelType = modelType.trim().toLowerCase();
        if (modelType.isEmpty()) {
            throw new ValidationException("Element <modelType> requires value.");
        }

        if (!SimulationModelTypes.getInstance().hasClass(modelType)) {
            throw new ValidationException(String.format("No such graph type \"%s\".", modelType));
        }
    }

    private static void validateGraphType(String graphType) throws ValidationException {
        if (graphType == null) {
            throw new ValidationException("Element <graphtype> is required for simulation source of type 'generate'.");
        }

        graphType = graphType.trim().toLowerCase();
        if (graphType.isEmpty()) {
            throw new ValidationException("Element <graphtype> requires value.");
        }

        if (!SimulationGraphTypes.getInstance().hasClass(graphType)) {
            throw new ValidationException(String.format("No such graph type \"%s\".", graphType));
        }
    }

    private static void validateFile(Path file) throws ValidationException {
        if (Files.exists(file)) {
            if (!Files.isRegularFile(file)) {
                throw new ValidationException(String.format("File \"%s\" is not a file.", file.toString()));
            }
        } else {
            throw new ValidationException(String.format("File \"%s\" does not exist.", file.toString()));
        }
    }

}
