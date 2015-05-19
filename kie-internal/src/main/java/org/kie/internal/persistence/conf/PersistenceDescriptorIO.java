/*
 * Copyright 2015 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.internal.persistence.conf;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class PersistenceDescriptorIO {

    private static JAXBContext context = null;
    private static Schema schema = null;

    public static PersistenceDescriptor fromXml( InputStream inputStream ) {
        try {
            Unmarshaller unmarshaller = getContext().createUnmarshaller();
            unmarshaller.setSchema( schema );
            PersistenceDescriptor descriptor = ( PersistenceDescriptor ) unmarshaller.unmarshal( inputStream );

            return descriptor;
        } catch ( Exception e ) {
            throw new RuntimeException( "Unable to read deployment descriptor from xml", e );
        }
    }

    public static JAXBContext getContext() throws JAXBException, SAXException {
        if ( context == null ) {
            Class<?>[] jaxbClasses = { PersistenceDescriptorImpl.class };
            context = JAXBContext.newInstance( jaxbClasses );
            // load schema for validation
            URL schemaLocation = PersistenceDescriptorIO.class.getResource( "persistence_2_0.xsd" );
            schema = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI ).newSchema( schemaLocation );
        }

        return context;
    }

    public static String toXml( PersistenceDescriptor descriptor ) {
        try {

            Marshaller marshaller = getContext().createMarshaller();
            marshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
            marshaller.setProperty( Marshaller.JAXB_SCHEMA_LOCATION, "http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd" );
            marshaller.setSchema( schema );
            StringWriter stringWriter = new StringWriter();

            marshaller.marshal( descriptor, stringWriter );
            String output = stringWriter.toString();

            return output;
        } catch ( Exception e ) {
            throw new RuntimeException( "Unable to generate persistence.xml file from PersistenceDescriptor", e );
        }
    }
}
