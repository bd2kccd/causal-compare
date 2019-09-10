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
package edu.pitt.dbmi.causal.compare.conf;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Path;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * Aug 24, 2019 12:04:46 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class Configurations {

    private Configurations() {
    }

    public static Configuration umarshal(Path configFile) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Configuration.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (Configuration) unmarshaller.unmarshal(configFile.toFile());
    }

    public static String marshal(Configuration config) throws JAXBException {
        StringWriter strWriter = new StringWriter();

        JAXBContext context = JAXBContext.newInstance(Configuration.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(config, new PrintWriter(strWriter));

        return strWriter.toString().trim();
    }

}
