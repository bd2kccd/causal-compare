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
package edu.pitt.dbmi.causal.compare.config;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * May 17, 2020 2:09:51 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@XmlRootElement(name = "graph")
@XmlAccessorType(XmlAccessType.FIELD)
public class GraphConfiguration {

    @XmlElement(name = "simulationPath", required = true)
    private String simulationPath;

    @XmlElement(name = "trueGraph", required = true)
    private String trueGraph;

    @XmlElementWrapper(name = "resultGraphs")
    @XmlElement(name = "graph", required = true)
    private List<ResultGraph> resultGraphs;

    public GraphConfiguration() {
    }

    public GraphConfiguration(String simulationPath, String trueGraph, List<ResultGraph> resultGraphs) {
        this.simulationPath = simulationPath;
        this.trueGraph = trueGraph;
        this.resultGraphs = resultGraphs;
    }

    public String getSimulationPath() {
        return simulationPath;
    }

    public void setSimulationPath(String simulationPath) {
        this.simulationPath = simulationPath;
    }

    public String getTrueGraph() {
        return trueGraph;
    }

    public void setTrueGraph(String trueGraph) {
        this.trueGraph = trueGraph;
    }

    public List<ResultGraph> getResultGraphs() {
        return resultGraphs;
    }

    public void setResultGraphs(List<ResultGraph> resultGraphs) {
        this.resultGraphs = resultGraphs;
    }

}
