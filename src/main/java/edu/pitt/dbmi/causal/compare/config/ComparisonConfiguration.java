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
 * May 17, 2020 11:30:59 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
@XmlRootElement(name = "comparison")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComparisonConfiguration {

    @XmlElement(name = "compareBy", required = true)
    private CompareByConfiguration compareBy;

    @XmlElementWrapper(name = "statistics")
    @XmlElement(name = "statistic", required = true)
    private List<String> statistics;

    @XmlElementWrapper(name = "properties")
    @XmlElement(name = "property")
    private List<Property> properties;

    public ComparisonConfiguration() {
    }

    public CompareByConfiguration getCompareBy() {
        return compareBy;
    }

    public void setCompareBy(CompareByConfiguration compareBy) {
        this.compareBy = compareBy;
    }

    public List<String> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<String> statistics) {
        this.statistics = statistics;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
