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

import edu.cmu.tetrad.algcomparison.statistic.Statistic;
import edu.cmu.tetrad.algcomparison.statistic.Statistics;
import java.util.List;

/**
 *
 * Aug 26, 2019 5:40:38 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class StatisticModels extends AbstractClassFactory<Statistic> {

    private static final StatisticModels INSTANCE = new StatisticModels();

    private StatisticModels() {
        super("edu.cmu.tetrad.algcomparison.statistic", Statistic.class);
    }

    public static StatisticModels getInstance() {
        return INSTANCE;
    }

    public Statistics create(List<String> stats) throws InstantiationException, IllegalAccessException {
        Statistics statistics = new Statistics();

        for (String stat : stats) {
            statistics.add((Statistic) getClass(stat).newInstance());
        }

        return statistics;
    }

}
