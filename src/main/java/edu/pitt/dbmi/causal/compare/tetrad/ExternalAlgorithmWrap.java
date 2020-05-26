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
package edu.pitt.dbmi.causal.compare.tetrad;

import edu.cmu.tetrad.algcomparison.algorithm.ExternalAlgorithm;
import edu.cmu.tetrad.data.DataModel;
import edu.cmu.tetrad.data.DataType;
import edu.cmu.tetrad.graph.Graph;
import edu.cmu.tetrad.util.Parameters;
import edu.pitt.dbmi.causal.compare.config.ResultGraph;

/**
 *
 * May 24, 2020 9:20:39 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class ExternalAlgorithmWrap extends ExternalAlgorithm {

    static final long serialVersionUID = 23L;

    private final ResultGraph resultGraph;
    private final Graph graph;
    private final Graph trueGraph;

    public ExternalAlgorithmWrap(ResultGraph resultGraph, Graph graph, Graph trueGraph) {
        this.resultGraph = resultGraph;
        this.graph = graph;
        this.trueGraph = trueGraph;
    }

    @Override
    public long getElapsedTime(DataModel dataSet, Parameters parameters) {
        if (resultGraph.getElapseTime() != null) {
            return resultGraph.getElapseTime();
        } else {
            return 0;
        }
    }

    @Override
    public Graph search(DataModel dataSet, Parameters parameters) {
        return graph;
    }

    @Override
    public Graph getComparisonGraph(Graph graph) {
        return trueGraph;
    }

    @Override
    public String getDescription() {
        return resultGraph.getDescription();
    }

    @Override
    public DataType getDataType() {
        return DataType.Mixed;
    }

}
