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

import edu.cmu.tetrad.algcomparison.graph.RandomGraph;
import edu.cmu.tetrad.algcomparison.simulation.Simulation;
import edu.cmu.tetrad.algcomparison.simulation.Simulations;
import edu.pitt.dbmi.causal.compare.conf.SimulationConfig;
import java.lang.reflect.Constructor;
import java.util.List;

/**
 *
 * Aug 28, 2019 5:51:05 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class SimulationModels {

    private static final SimulationModels INSTANCE = new SimulationModels();

    private static final SimulationGraphTypes GRAPH_TYPES = SimulationGraphTypes.getInstance();
    private static final SimulationModelTypes SIM_MODEL_TYPES = SimulationModelTypes.getInstance();

    private SimulationModels() {
    }

    public Simulations create(List<SimulationConfig> configs) throws Exception {
        Simulations simulations = new Simulations();

        for (SimulationConfig config : configs) {
            switch (config.getSource()) {
                case file:
                    // todo
                    break;
                case generate:
                    Class<? extends RandomGraph> randGraphClass = GRAPH_TYPES.getClass(config.getGraphType());
                    Class<? extends Simulation> simModelClass = SIM_MODEL_TYPES.getClass(config.getModelType());

                    Constructor<?> ctr = simModelClass.getConstructor(RandomGraph.class);
                    simulations.add((Simulation) ctr.newInstance(new Object[]{randGraphClass.newInstance()}));
                    break;
            }
        }

        return simulations;
    }

    public static SimulationModels getInstance() {
        return INSTANCE;
    }

    public SimulationGraphTypes getGraphTypes() {
        return GRAPH_TYPES;
    }

    public SimulationModelTypes getSimulationModelTypes() {
        return SIM_MODEL_TYPES;
    }

}
