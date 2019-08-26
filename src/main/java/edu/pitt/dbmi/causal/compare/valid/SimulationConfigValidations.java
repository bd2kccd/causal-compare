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
import java.util.List;

/**
 *
 * Aug 26, 2019 12:03:09 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class SimulationConfigValidations extends AbstractValidations {

    private SimulationConfigValidations() {
    }

    public static void validate(List<SimulationConfig> simulationConfigs) throws ValidationException {
        for (SimulationConfig config : simulationConfigs) {
            SimulationSource source = config.getSource();
            if (source == null) {
                throw new ValidationException("Simulation requires value for attribute 'source'.");
            }

            switch (source) {
                case file:
                    validateDataFile(clean(config.getDataFile()));
                    validateTrueGraphFile(clean(config.getTrueGraphFile()));
                    break;
                case generate:
                    validateGraphType(clean(config.getGraphType()));
                    validateModelType(clean(config.getModelType()));
                    break;
            }
        }
    }

    private static void validateTrueGraphFile(String trueGraphFile) throws ValidationException {
        if (trueGraphFile.isEmpty()) {
            throw new ValidationException("Simulation of type 'file' requires attribute 'truegraph' and value.");
        }

        validateFile(Paths.get(trueGraphFile));
    }

    private static void validateDataFile(String dataFile) throws ValidationException {
        if (dataFile.isEmpty()) {
            throw new ValidationException("Simulation of type 'file' requires attribute 'data' and value.");
        }

        validateFile(Paths.get(dataFile));
    }

    private static void validateGraphType(String graphType) throws ValidationException {
        if (graphType.isEmpty()) {
            throw new ValidationException("Simulation of type 'generate' requires attribute 'graphType' and value.");
        }
        if (!SimulationGraphTypes.getInstance().hasClass(graphType)) {
            throw new ValidationException(String.format("No such graph type \"%s\".", graphType));
        }
    }

    private static void validateModelType(String modelType) throws ValidationException {
        if (modelType.isEmpty()) {
            throw new ValidationException("Simulation of type 'generate' requires attribute 'modeltype' and value.");
        }
        if (!SimulationModelTypes.getInstance().hasClass(modelType)) {
            throw new ValidationException(String.format("No such model type \"%s\".", modelType));
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
