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
package edu.pitt.dbmi.causal.compare.tetrad;

import edu.cmu.tetrad.algcomparison.algorithm.Algorithm;
import edu.cmu.tetrad.algcomparison.algorithm.AlgorithmFactory;
import edu.cmu.tetrad.algcomparison.algorithm.Algorithms;
import edu.cmu.tetrad.annotation.AlgorithmAnnotations;
import edu.pitt.dbmi.causal.compare.ComparisonException;
import edu.pitt.dbmi.causal.compare.conf.AlgorithmConfig;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * Aug 24, 2019 5:51:21 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class AlgorithmModels extends AbstractAnnotatedClassFactory<Algorithm> {

    private static final AlgorithmModels INSTANCE = new AlgorithmModels();

    private AlgorithmModels() {
        super(AlgorithmAnnotations.getInstance().getAnnotatedClasses().stream()
                .collect(Collectors.toMap(e -> e.getAnnotation().command().trim().toLowerCase(), e -> e.getClazz())));
    }

    public static AlgorithmModels getInstance() {
        return INSTANCE;
    }

    public boolean requireIndependenceTest(Class clazz) {
        return AlgorithmAnnotations.getInstance().requireIndependenceTest(clazz);
    }

    public boolean requireScore(Class clazz) {
        return AlgorithmAnnotations.getInstance().requireScore(clazz);
    }

    public Algorithm create(AlgorithmConfig config) throws ComparisonException {
        try {
            return AlgorithmFactory.create(
                    getClass(config.getName()),
                    IndependenceTestModels.getInstance().getClass(config.getTest()),
                    ScoreModels.getInstance().getClass(config.getScore()));
        } catch (IllegalAccessException | InstantiationException exception) {
            throw new ComparisonException(exception);
        }
    }

    public Algorithms create(List<AlgorithmConfig> configs) throws ComparisonException {
        Algorithms algorithms = new Algorithms();
        for (AlgorithmConfig config : configs) {
            algorithms.add(create(config));
        }

        return algorithms;
    }

}
