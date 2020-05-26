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
 * May 17, 2020 5:26:47 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@XmlRootElement(name = "search")
@XmlAccessorType(XmlAccessType.FIELD)
public class SearchConfiguration {

    @XmlElementWrapper(name = "simulations")
    @XmlElement(name = "simulation", required = true)
    private List<SimulationConfiguration> simulations;

    @XmlElementWrapper(name = "algorithms")
    @XmlElement(name = "algorithm", required = true)
    private List<AlgorithmConfiguration> algorithms;

    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter", required = true)
    private List<ParameterConfiguration> parameters;

    public SearchConfiguration() {
    }

    public List<SimulationConfiguration> getSimulations() {
        return simulations;
    }

    public void setSimulations(List<SimulationConfiguration> simulations) {
        this.simulations = simulations;
    }

    public List<AlgorithmConfiguration> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<AlgorithmConfiguration> algorithms) {
        this.algorithms = algorithms;
    }

    public List<ParameterConfiguration> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterConfiguration> parameters) {
        this.parameters = parameters;
    }

}
