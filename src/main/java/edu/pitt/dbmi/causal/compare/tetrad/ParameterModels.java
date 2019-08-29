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
import edu.cmu.tetrad.util.Parameters;
import edu.pitt.dbmi.causal.compare.conf.ParameterConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * Aug 27, 2019 5:17:47 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class ParameterModels {

    private static final Pattern COMMA_DELIM = Pattern.compile(",");

    private static final ParameterModels INSTANCE = new ParameterModels();

    private final Map<String, ParamDescription> parameters = new HashMap<>();
    private final Map<String, String> parameterNames = new HashMap<>();

    private ParameterModels() {
        ParamDescriptions paramDescs = ParamDescriptions.getInstance();
        paramDescs.getNames().forEach(name -> {
            parameterNames.put(name.trim().toLowerCase(), name);
            parameters.put(name, paramDescs.get(name));
        });
    }

    public static ParameterModels getInstance() {
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

    public Parameters create(List<ParameterConfig> configs) {
        Parameters params = new Parameters();

        configs.forEach(e -> {
            ParamDescription paramDesc = get(e.getName());

            String[] values = COMMA_DELIM.split(e.getValue());
            Object obj = paramDesc.getDefaultValue();
            if (obj instanceof Byte) {
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), Byte.valueOf(values[0]));
                } else {
                    params.set(getOriginalName(e.getName()), Arrays.stream(values).map(Byte::valueOf).toArray());
                }
            } else if (obj instanceof Integer) {
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), Integer.valueOf(values[0]));
                } else {
                    params.set(getOriginalName(e.getName()), Arrays.stream(values).map(Integer::valueOf).toArray());
                }
            } else if (obj instanceof Long) {
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), Long.valueOf(values[0]));
                } else {
                    params.set(getOriginalName(e.getName()), Arrays.stream(values).map(Long::valueOf).toArray());
                }
            } else if (obj instanceof Float) {
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), Float.valueOf(values[0]));
                } else {
                    params.set(getOriginalName(e.getName()), Arrays.stream(values).map(Float::valueOf).toArray());
                }
            } else if (obj instanceof Double) {
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), Double.valueOf(values[0]));
                } else {
                    params.set(getOriginalName(e.getName()), Arrays.stream(values).map(Double::valueOf).toArray());
                }
            } else if (obj instanceof Boolean) {
                params.set(getOriginalName(e.getName()), Arrays.stream(values).map(v -> Boolean.valueOf(v.trim())).toArray());
            } else if (obj instanceof String) {
                params.set(getOriginalName(e.getName()), e.getValue().trim());
                if (values.length == 1) {
                    params.set(getOriginalName(e.getName()), values[0]);
                } else {
                    params.set(getOriginalName(e.getName()), values);
                }
            }
        });

        return params;
    }

}
