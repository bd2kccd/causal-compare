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

    public static void validate(List<AlgorithmConfig> algorithmConfigs) throws ValidationException {
        for (AlgorithmConfig config : algorithmConfigs) {
            String algo = clean(config.getName());
            if (algo.isEmpty()) {
                throw new ValidationException("Algorithm requires value for attribute 'name'.");
            }

            AlgorithmModels algoModels = AlgorithmModels.getInstance();
            if (!algoModels.hasClass(algo)) {
                throw new ValidationException(String.format("No such algorithm name \"%s\".", algo));
            }

            Class algoClass = algoModels.getClass(algo);

            String indTest = clean(config.getTest());
            if (algoModels.requireIndependenceTest(algoClass)) {
                if (indTest.isEmpty()) {
                    throw new ValidationException(String.format("XML tag 'test' and value is required algorithm \"%s\".", algo));
                }
                if (!IndependenceTestModels.getInstance().hasClass(indTest)) {
                    throw new ValidationException(String.format("No such test of independence \"%s\".", indTest));
                }
            } else if (!indTest.isEmpty()) {
                throw new ValidationException(String.format("algorithm \"%s\" does not take test of independence.", algo));
            }

            String score = clean(config.getScore());
            if (algoModels.requireScore(algoClass)) {
                if (score.isEmpty()) {
                    throw new ValidationException(String.format("XML tag 'score' and value is required algorithm \"%s\".", algo));
                }
                if (!ScoreModels.getInstance().hasClass(score)) {
                    throw new ValidationException(String.format("No such score \"%s\".", score));
                }
            } else if (!score.isEmpty()) {
                throw new ValidationException(String.format("algorithm \"%s\" does not take score.", algo));
            }
        }
    }

}
