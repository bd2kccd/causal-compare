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

import edu.pitt.dbmi.causal.compare.conf.AlgorithmConfig;
import edu.pitt.dbmi.causal.compare.conf.Configuration;
import edu.pitt.dbmi.causal.compare.conf.Configurations;
import edu.pitt.dbmi.causal.compare.conf.SimulationConfig;
import edu.pitt.dbmi.causal.compare.conf.SimulationSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.xml.bind.JAXBException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 *
 * Aug 14, 2019 4:56:23 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class CausalCompareApplicationTest {

    @Rule
    public final TemporaryFolder tmpFolder = new TemporaryFolder();

    /**
     * Test of main method, of class CausalCompareApplication.
     *
     * @throws IOException
     */
    @Test
    public void testMain() throws IOException {
        String configFile = getClass().getResource("/data/comparison-tool.xml").getFile();
        String dirOut = tmpFolder.newFolder("comparison").toString();
        String[] args = {
            "--config", configFile,
            "--out", dirOut
        };
        CausalCompareApplication.main(args);

        System.out.println("================================================================================");
        Files.list(Paths.get(dirOut))
                .filter(Files::isRegularFile)
                .forEach(e -> {
                    try (Stream<String> stream = Files.lines(e)) {
                        stream.forEach(System.out::println);
                    } catch (IOException exception) {
                        exception.printStackTrace(System.err);
                    }
                });
        System.out.println("================================================================================");
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
        fileSimeConfig.setSource(SimulationSource.file);
        fileSimeConfig.setDataFile("src/test/resources/data/data_sim_10var_1kcase.txt");
        fileSimeConfig.setTrueGraphFile("src/test/resources/data/graph_sim_10var_1kcase.txt");
        config.setSimulationConfigs(Arrays.asList(
                genSimConfig,
                fileSimeConfig
        ));

        config.setAlgorithmConfigs(Arrays.asList(
                new AlgorithmConfig("gfci", "fisher-z", "sem-bic"),
                new AlgorithmConfig("fges", null, "sem-bic")
        ));

        config.setStatistics(Arrays.asList(
                "adjacencyprecision",
                "arrowheadrecall",
                "adjacencyrecall"
        ));

        return config;
    }

}
