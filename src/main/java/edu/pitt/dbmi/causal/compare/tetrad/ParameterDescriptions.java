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

import edu.cmu.tetrad.util.ParamDescription;
import edu.cmu.tetrad.util.ParamDescriptions;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * Aug 27, 2019 5:17:47 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ParameterDescriptions {

    private static final ParameterDescriptions INSTANCE = new ParameterDescriptions();

    private final Map<String, ParamDescription> map;

    private ParameterDescriptions() {
        ParamDescriptions paramDescs = ParamDescriptions.getInstance();
        this.map = paramDescs.getNames().stream()
                .collect(Collectors.toMap(String::toLowerCase, e -> paramDescs.get(e)));
    }

    public static ParameterDescriptions getInstance() {
        return INSTANCE;
    }

    public boolean hasParameter(String name) {
        return map.containsKey(name);
    }

    public Set<String> getNames() {
        return map.keySet();
    }

    public ParamDescription get(String name) {
        return map.get(name);
    }

}
