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

import edu.pitt.dbmi.causal.compare.conf.AlgorithmConfig;
import edu.pitt.dbmi.causal.compare.tetrad.AlgorithmModels;
import edu.pitt.dbmi.causal.compare.tetrad.IndependenceTestModels;
import edu.pitt.dbmi.causal.compare.tetrad.ScoreModels;
import java.util.List;

/**
 *
 * Aug 26, 2019 12:09:13 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class AlgorithmConfigValidations extends AbstractValidations {

    private AlgorithmConfigValidations() {
    }

    public static void validate(List<AlgorithmConfig> configs) throws ValidationException {
        if (configs == null) {
            throw new ValidationException("Element <algorithms> is required.");
        }
        if (configs.isEmpty()) {
            throw new ValidationException("Parent element <algorithms> requires child element <algorithm>.");
        }

        for (AlgorithmConfig config : configs) {
            String algoName = getAlgorithmName(config);

            AlgorithmModels algoModels = AlgorithmModels.getInstance();
            if (!algoModels.hasClass(algoName)) {
                throw new ValidationException(String.format("No such algorithm name \"%s\".", algoName));
            }

            Class algoClass = algoModels.getClass(algoName);
            validateTestOfIndependence(algoClass, config);
            validateScore(algoClass, config);
        }
    }

    private static void validateScore(Class algoClass, AlgorithmConfig config) throws ValidationException {
        String score = config.getScore();
        if (AlgorithmModels.getInstance().requireScore(algoClass)) {
            if (score == null) {
                throw new ValidationException(String.format("Element <score> is required for algorithm \"%s\".", config.getName()));
            }

            score = score.trim().toLowerCase();
            if (score.isEmpty()) {
                throw new ValidationException("Element <score> requires value.");
            }

            if (!ScoreModels.getInstance().hasClass(score)) {
                throw new ValidationException(String.format("No such score \"%s\".", score));
            }
        } else if (!(score == null || score.trim().isEmpty())) {
            throw new ValidationException(String.format("Algorithm \"%s\" does not take score.", config.getName()));
        }
    }

    private static void validateTestOfIndependence(Class algoClass, AlgorithmConfig config) throws ValidationException {
        String indTest = config.getTest();
        if (AlgorithmModels.getInstance().requireIndependenceTest(algoClass)) {
            if (indTest == null) {
                throw new ValidationException(String.format("Element <test> is required for algorithm \"%s\".", config.getName()));
            }

            indTest = indTest.trim().toLowerCase();
            if (indTest.isEmpty()) {
                throw new ValidationException("Element <test> requires value.");
            }

            if (!IndependenceTestModels.getInstance().hasClass(indTest)) {
                throw new ValidationException(String.format("No such test of independence \"%s\".", indTest));
            }
        } else if (!(indTest == null || indTest.trim().isEmpty())) {
            throw new ValidationException(String.format("Algorithm \"%s\" does not take test of independence.", config.getName()));
        }
    }

    private static String getAlgorithmName(AlgorithmConfig config) throws ValidationException {
        String name = config.getName();
        if (name == null) {
            throw new ValidationException("Element <algorithm> requires 'name' attribute.");
        }

        name = name.trim();
        if (name.isEmpty()) {
            throw new ValidationException("Attribute 'name' requires value.");
        }

        return name;
    }

}