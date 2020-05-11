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
package edu.pitt.dbmi.causal.compare;

import edu.cmu.tetrad.util.Params;
import edu.pitt.dbmi.causal.compare.conf.AlgorithmConfig;
import edu.pitt.dbmi.causal.compare.conf.Configuration;
import edu.pitt.dbmi.causal.compare.conf.Configurations;
import edu.pitt.dbmi.causal.compare.conf.ParameterConfig;
import edu.pitt.dbmi.causal.compare.conf.Property;
import edu.pitt.dbmi.causal.compare.conf.SimulationConfig;
import edu.pitt.dbmi.causal.compare.conf.SimulationSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 *
 * Aug 14, 2019 4:56:23 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@Disabled
public class CausalCompareApplicationTest {

    @TempDir
    public Path tmpFolder;

    /**
     * Test of main method, of class CausalCompareApplication.
     *
     * @throws IOException
     */
    @Test
    public void testMain() throws IOException {
        String configFile = getClass().getResource("/data/comparison-tool.xml").getFile();
        String prefix = "test_compare";
        String[] args = {
            "--config", configFile,
            "--prefix", prefix,
            "--out", tmpFolder.toString()
        };
        CausalCompareApplication.main(args);
    }

    @Test
    public void testCreateXMLConfig() {
        System.out.println("================================================================================");
        try {
            System.out.println(Configurations.marshal(createSampleConfiguration()));
        } catch (JAXBException exception) {
            exception.printStackTrace(System.err);
        }
        System.out.println("================================================================================");
    }

    private static Configuration createSampleConfiguration() {
        Configuration config = new Configuration();

        SimulationConfig genSimConfig = new SimulationConfig();
        genSimConfig.setSource(SimulationSource.generate);
        genSimConfig.setGraphType("RandomForward");
        genSimConfig.setModelType("SemSimulation");

        SimulationConfig fileSimeConfig = new SimulationConfig();
        fileSimeConfig.setSource(SimulationSource.directory);
        fileSimeConfig.setPath("src/test/resources/data/simulation");
        config.setSimulationConfigs(Arrays.asList(
                genSimConfig,
                fileSimeConfig
        ));

        config.setAlgorithmConfigs(Arrays.asList(
                new AlgorithmConfig("gfci", "fisher-z-test", "sem-bic"),
                new AlgorithmConfig("fges", null, "sem-bic")
        ));

        config.setStatistics(Arrays.asList(
                "adjacencyPrecision",
                "arrowheadRecall",
                "adjacencyRecall"
        ));

        config.setParameters(Arrays.asList(
                new ParameterConfig(Params.NUM_RUNS, "1"),
                new ParameterConfig(Params.NUM_MEASURES, "4,6"),
                new ParameterConfig(Params.AVG_DEGREE, "4")
        ));

        config.setComparisonProperties(Arrays.asList(
                new Property("showAlgorithmIndices", "true"),
                new Property("showSimulationIndices", "true"),
                new Property("sortByUtility", "true"),
                new Property("showUtilities", "true"),
                new Property("saveSearchGraphs", "true"),
                new Property("tabDelimitedTables", "true")
        ));

        return config;
    }

    private static void printFile(Path file) {
        try (Stream<String> stream = Files.lines(file)) {
            stream.forEach(System.out::println);
        } catch (IOException exception) {
            exception.printStackTrace(System.err);
        }
    }

    private List<Path> listFiles(Path folder) throws IOException {
        return Files.list(folder)
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

}
