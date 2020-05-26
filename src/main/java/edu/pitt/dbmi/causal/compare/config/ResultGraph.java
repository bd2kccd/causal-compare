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

/**
 *
 * May 17, 2020 3:32:02 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class ResultGraph {

    private String graphFile;

    private Long elapseTime;

    private String description;

    public ResultGraph() {
    }

    public ResultGraph(String graphFile, Long elapseTime, String description) {
        this.graphFile = graphFile;
        this.elapseTime = elapseTime;
        this.description = description;
    }

    public String getGraphFile() {
        return graphFile;
    }

    public void setGraphFile(String graphFile) {
        this.graphFile = graphFile;
    }

    public Long getElapseTime() {
        return (elapseTime == null) ? -1L : elapseTime;
    }

    public void setElapseTime(Long elapseTime) {
        this.elapseTime = elapseTime;
    }

    public String getDescription() {
        return (description == null) ? graphFile : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
