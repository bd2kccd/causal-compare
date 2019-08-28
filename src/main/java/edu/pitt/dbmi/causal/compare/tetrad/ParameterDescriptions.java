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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * Aug 27, 2019 5:17:47 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ParameterDescriptions {

    private static final ParameterDescriptions INSTANCE = new ParameterDescriptions();

    private final Map<String, ParamDescription> parameters = new HashMap<>();
    private final Map<String, String> parameterNames = new HashMap<>();

    private ParameterDescriptions() {
        ParamDescriptions paramDescs = ParamDescriptions.getInstance();
        paramDescs.getNames().forEach(name -> {
            parameterNames.put(name.trim().toLowerCase(), name);
            parameters.put(name, paramDescs.get(name));
        });
    }

    public static ParameterDescriptions getInstance() {
        return INSTANCE;
    }

    public boolean hasParameter(String name) {
        return (name == null)
                ? false
                : parameterNames.containsKey(name.trim().toLowerCase());
    }

    public Set<String> getNames() {
        return parameters.keySet();
    }

    public String getOriginalName(String name) {
        return (name == null)
                ? null
                : parameterNames.get(name.trim().toLowerCase());
    }

    public ParamDescription get(String name) {
        return (name == null)
                ? null
                : parameters.get(parameterNames.get(name.trim().toLowerCase()));
    }

}
