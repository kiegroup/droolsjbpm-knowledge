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

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistenceDescriptorIOTest {

    private InputStream getInputStream( String file ) {
        return PersistenceDescriptorIOTest.class.getResourceAsStream( file );
    }

    @Test
    public void readFromXMLTest() {
        PersistenceDescriptor descriptor = PersistenceDescriptorIO.fromXml( getInputStream( "persistence.xml" ) );
        PersistenceDescriptor expectedDescriptor = expectedDescriptor();
        assertEqualsDescriptor( expectedDescriptor, descriptor );
    }

    @Test
    public void writeToXMLTest() {

        PersistenceDescriptor descriptor = expectedDescriptor();
        String xml = PersistenceDescriptorIO.toXml( descriptor );

        StringBuilder expectedXml = new StringBuilder( );

        InputStream in = getExpectedXML();
        byte buffer[] = new byte[1024];

        int size;
        try {
            while ( ( size = in.read( buffer ) ) != -1 ) {
                expectedXml.append( new String( buffer, 0, size, "UTF-8" ) );
            }
        } catch ( Exception e ) {
            Assert.fail( e.getMessage() );
        }
        assertEquals( expectedXml.toString(), xml );
    }

    private void assertEqualsDescriptor( PersistenceDescriptor expectedDescriptor, PersistenceDescriptor descriptor ) {
        assertEquals( expectedDescriptor.getVersion(), descriptor.getVersion() );

        PersistenceUnit persistenceUnit = descriptor.getPersistenceUnit().get( 0 );
        PersistenceUnit expectedPersistenceUnit = expectedDescriptor.getPersistenceUnit().get( 0 );

        assertEquals( expectedPersistenceUnit.getName(), persistenceUnit.getName() );
        assertEquals( expectedPersistenceUnit.getDescription(), persistenceUnit.getDescription() );
        assertEquals( expectedPersistenceUnit.getProvider(), persistenceUnit.getProvider() );
        assertEquals( expectedPersistenceUnit.getJtaDataSource(), persistenceUnit.getJtaDataSource() );
        assertEquals( expectedPersistenceUnit.getNonJtaDataSource(), persistenceUnit.getNonJtaDataSource() );
        assertEquals( expectedPersistenceUnit.isExcludeUnlistedClasses(), persistenceUnit.isExcludeUnlistedClasses() );
        assertEquals( expectedPersistenceUnit.getClazz(), persistenceUnit.getClazz() );
        assertEquals( expectedPersistenceUnit.getJarFile(), persistenceUnit.getJarFile() );
        assertEquals( expectedPersistenceUnit.getMappingFile(), persistenceUnit.getMappingFile() );
        assertEquals( expectedPersistenceUnit.getSharedCacheMode(), persistenceUnit.getSharedCacheMode() );
        assertEquals( expectedPersistenceUnit.getTransactionType(), persistenceUnit.getTransactionType() );


        assertEquals( expectedPersistenceUnit.getProperties().getProperty(), persistenceUnit.getProperties().getProperty() );
    }

    private PersistenceDescriptor expectedDescriptor() {
        PersistenceDescriptorImpl descriptor = new PersistenceDescriptorImpl();
        descriptor.setVersion( "2.0" );

        PersistenceUnit persistenceUnit = new PersistenceUnit();

        persistenceUnit.setName( "org.test.persistence-unit" );
        persistenceUnit.setDescription( "org.test.description" );
        persistenceUnit.setProvider( "org.test.Provider" );
        persistenceUnit.setJtaDataSource( "java:jboss/datasources/ExampleDS" );
        persistenceUnit.getMappingFile().add( "META-INF/Mapping1.xml" );
        persistenceUnit.getMappingFile().add( "META-INF/Mapping2.xml" );
        persistenceUnit.getJarFile().add( "file1.jar" );
        persistenceUnit.getJarFile().add( "file2.jar" );
        persistenceUnit.getClazz().add( "org.test.Entity1" );
        persistenceUnit.getClazz().add( "org.test.Entity2" );
        persistenceUnit.setExcludeUnlistedClasses( true );
        persistenceUnit.setSharedCacheMode( PersistenceUnitCachingType.ALL );
        persistenceUnit.setValidationMode( PersistenceUnitValidationModeType.AUTO );
        persistenceUnit.setTransactionType( PersistenceUnitTransactionType.JTA );
        descriptor.getPersistenceUnit().add( persistenceUnit );

        Properties properties = new Properties();
        properties.getProperty().add( new Property( "property1", "property1_value" ) );
        properties.getProperty().add( new Property( "property2", "property2_value" ) );
        persistenceUnit.setProperties( properties );

        return descriptor;
    }

    private InputStream getExpectedXML() {
        return getInputStream( "generated-persistence.xml" );
    }
}
