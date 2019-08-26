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
package edu.pitt.dbmi.causal.compare.conf;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * Aug 15, 2019 11:20:07 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@XmlRootElement(name = "comparisontool")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {

    @XmlElementWrapper(name = "simulations")
    @XmlElement(name = "simulation")
    private List<SimulationConfig> simulationConfigs;

    @XmlElementWrapper(name = "algorithms")
    @XmlElement(name = "algorithm")
    private List<AlgorithmConfig> algorithmConfigs;

    @XmlElementWrapper(name = "statistics")
    @XmlElement(name = "statistic")
    private List<String> statistics;

    public Configuration() {
    }

    public List<SimulationConfig> getSimulationConfigs() {
        return simulationConfigs;
    }

    public void setSimulationConfigs(List<SimulationConfig> simulationConfigs) {
        this.simulationConfigs = simulationConfigs;
    }

    public List<AlgorithmConfig> getAlgorithmConfigs() {
        return algorithmConfigs;
    }

    public void setAlgorithmConfigs(List<AlgorithmConfig> algorithmConfigs) {
        this.algorithmConfigs = algorithmConfigs;
    }

    public List<String> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<String> statistics) {
        this.statistics = statistics;
    }

}
