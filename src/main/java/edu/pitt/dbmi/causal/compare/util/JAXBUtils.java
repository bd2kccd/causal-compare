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

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

/**
 *
 * May 17, 2020 11:43:46 AM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public final class JAXBUtils {

    private JAXBUtils() {
    }

    public static <T extends Object> T read(Path xmlFile, Class<T> type) throws JAXBException {
        try (InputStream is = Files.newInputStream(xmlFile)) {
            return JAXB.unmarshal(is, type);
        } catch (IOException exception) {
            throw new JAXBException(exception);
        }
    }

    public static String write(Object jaxbObject) throws JAXBException {
        try (StringWriter writer = new StringWriter()) {
            JAXB.marshal(jaxbObject, writer);

            return writer.toString().trim();
        } catch (IOException exception) {
            throw new JAXBException(exception);
        }
    }

}
