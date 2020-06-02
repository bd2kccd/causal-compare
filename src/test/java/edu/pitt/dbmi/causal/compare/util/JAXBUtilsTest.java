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
package edu.pitt.dbmi.causal.compare.util;

import edu.cmu.tetrad.util.Params;
import edu.pitt.dbmi.causal.compare.config.AlgorithmConfiguration;
import edu.pitt.dbmi.causal.compare.config.CompareByConfiguration;
import edu.pitt.dbmi.causal.compare.config.ComparisonConfiguration;
import edu.pitt.dbmi.causal.compare.config.GraphConfiguration;
import edu.pitt.dbmi.causal.compare.config.ParameterConfiguration;
import edu.pitt.dbmi.causal.compare.config.Property;
import edu.pitt.dbmi.causal.compare.config.ResultGraph;
import edu.pitt.dbmi.causal.compare.config.SearchConfiguration;
import edu.pitt.dbmi.causal.compare.config.SimulationConfiguration;
import edu.pitt.dbmi.causal.compare.config.SimulationSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kvb2
 */
public class JAXBUtilsTest {

    /**
     * Test of read method, of class JAXBUtils.
     *
     * @throws JAXBException
     */
    @Test
    public void testReadGraph() throws JAXBException {
        Path xmlFile = Paths.get(getClass().getResource("/data/compare_graphs.xml").getFile());
        ComparisonConfiguration compareConfig = JAXBUtils.read(xmlFile, ComparisonConfiguration.class);

        Assertions.assertNotNull(compareConfig);
    }

    @Test
    public void testReadSearch() throws JAXBException {
        Path xmlFile = Paths.get(getClass().getResource("/data/compare_search.xml").getFile());
        ComparisonConfiguration compareConfig = JAXBUtils.read(xmlFile, ComparisonConfiguration.class);

        Assertions.assertNotNull(compareConfig);
    }

    /**
     * Test of write method, of class JAXBUtils.
     *
     * @throws JAXBException
     */
    @Test
    public void testWrite() throws JAXBException {
        Object jaxbObject = getComparisonConfiguration();
        String result = JAXBUtils.write(jaxbObject);

        Assertions.assertNotNull(result);
    }

    private ComparisonConfiguration getComparisonConfiguration() {
        ComparisonConfiguration comparisonConfig = new ComparisonConfiguration();
        comparisonConfig.setCompareBy(getCompareBy());
        comparisonConfig.setStatistics(getStatistics());
        comparisonConfig.setProperties(getProperties());

        return comparisonConfig;
    }

    private CompareByConfiguration getCompareBy() {
        CompareByConfiguration compareByConfig = new CompareByConfiguration();
        compareByConfig.setSearch(getSearch());
        compareByConfig.setGraph(getGraph());

        return compareByConfig;
    }

    private SearchConfiguration getSearch() {
        SearchConfiguration searchConfig = new SearchConfiguration();
        searchConfig.setSimulations(getSimulations());
        searchConfig.setAlgorithms(getAlgorithms());
        searchConfig.setParameters(getParameters());

        return searchConfig;
    }

    private GraphConfiguration getGraph() {
        GraphConfiguration graphConfig = new GraphConfiguration();
        graphConfig.setTrueGraph(getClass().getResource("/data/graph/simulation/graph/graph.1.txt").getFile());
        graphConfig.setSimulationPath(getClass().getResource("/data/graph/simulation").getFile());
        graphConfig.setResultGraphs(getResultGraphs());

        return graphConfig;
    }

    private List<ResultGraph> getResultGraphs() {
        List<ResultGraph> resultGraphs = new LinkedList<>();

        String graphFile = getClass().getResource("/data/graph/result_graphs/graph_fges.txt").getFile();
        long elapseTime = System.currentTimeMillis();
        String description = "FGES";
        resultGraphs.add(new ResultGraph(graphFile, elapseTime, description));

        graphFile = getClass().getResource("/data/graph/result_graphs/graph_gfic.txt").getFile();
        elapseTime = System.currentTimeMillis();
        description = "GFCI";
        resultGraphs.add(new ResultGraph(graphFile, elapseTime, description));

        graphFile = getClass().getResource("/data/graph/result_graphs/graph_pc.txt").getFile();
        elapseTime = System.currentTimeMillis();
        description = "PC";
        resultGraphs.add(new ResultGraph(graphFile, elapseTime, description));

        return resultGraphs;
    }

    private List<ParameterConfiguration> getParameters() {
        return Arrays.asList(
                new ParameterConfiguration(Params.NUM_RUNS, "1"),
                new ParameterConfiguration(Params.NUM_MEASURES, "4,6"),
                new ParameterConfiguration(Params.AVG_DEGREE, "4")
        );
    }

    private List<AlgorithmConfiguration> getAlgorithms() {
        return Arrays.asList(
                new AlgorithmConfiguration("gfci", "fisher-z-test", "sem-bic"),
                new AlgorithmConfiguration("fges", null, "sem-bic")
        );
    }

    private List<SimulationConfiguration> getSimulations() {
        List<SimulationConfiguration> simulations = new LinkedList<>();

        SimulationConfiguration dirSim = new SimulationConfiguration();
        dirSim.setSource(SimulationSource.directory);
        dirSim.setPath(getClass().getResource("/data/simulation").getFile());
        simulations.add(dirSim);

        SimulationConfiguration genSim = new SimulationConfiguration();
        genSim.setSource(SimulationSource.generate);
        genSim.setGraphType("RandomForward");
        genSim.setModelType("SemSimulation");
        simulations.add(genSim);

        return simulations;
    }

    private List<String> getStatistics() {
        return Arrays.asList(
                "adjacencyPrecision",
                "arrowheadRecall",
                "adjacencyRecall"
        );
    }

    private List<Property> getProperties() {
        return Arrays.asList(
                new Property("showAlgorithmIndices", "true"),
                new Property("showSimulationIndices", "true"),
                new Property("sortByUtility", "true"),
                new Property("showUtilities", "true"),
                new Property("saveSearchGraphs", "true"),
                new Property("tabDelimitedTables", "true")
        );
    }

}
