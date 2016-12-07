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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType( XmlAccessType.NONE )
@XmlType( name = "", propOrder = {
        "description",
        "provider",
        "jtaDataSource",
        "nonJtaDataSource",
        "mappingFile",
        "jarFile",
        "clazz",
        "excludeUnlistedClasses",
        "sharedCacheMode",
        "validationMode",
        "properties"
} )
public class PersistenceUnit implements Serializable {

    @XmlAttribute( name = "name", required = true )
    protected String name;

    @XmlElement( name = "description" )
    protected String description;

    @XmlElement( name = "provider" )
    protected String provider;

    @XmlElement( name = "jta-data-source" )
    protected String jtaDataSource;

    @XmlElement( name = "non-jta-data-source" )
    protected String nonJtaDataSource;

    @XmlElement( name = "mapping-file" )
    protected List<String> mappingFile;

    @XmlElement( name = "jar-file" )
    protected List<String> jarFile;

    @XmlElement( name = "class" )
    protected List<String> clazz;

    @XmlElement( name = "exclude-unlisted-classes", defaultValue = "true" )
    protected Boolean excludeUnlistedClasses;

    @XmlElement( name = "shared-cache-mode" )
    protected PersistenceUnitCachingType sharedCacheMode;

    @XmlElement( name = "validation-mode" )
    protected PersistenceUnitValidationModeType validationMode;

    @XmlElement( name = "properties" )
    protected Properties properties;

    @XmlAttribute( name = "transaction-type" )
    protected PersistenceUnitTransactionType transactionType;

    public PersistenceUnit() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String value ) {
        this.description = value;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider( String value ) {
        this.provider = value;
    }

    public String getJtaDataSource() {
        return jtaDataSource;
    }

    public void setJtaDataSource( String value ) {
        this.jtaDataSource = value;
    }

    public String getNonJtaDataSource() {
        return nonJtaDataSource;
    }

    public void setNonJtaDataSource( String value ) {
        this.nonJtaDataSource = value;
    }

    public List<String> getMappingFile() {
        if ( mappingFile == null ) {
            mappingFile = new ArrayList<String>();
        }
        return this.mappingFile;
    }

    public List<String> getJarFile() {
        if ( jarFile == null ) {
            jarFile = new ArrayList<String>();
        }
        return this.jarFile;
    }

    public List<String> getClazz() {
        if ( clazz == null ) {
            clazz = new ArrayList<String>();
        }
        return this.clazz;
    }

    public Boolean isExcludeUnlistedClasses() {
        return excludeUnlistedClasses;
    }

    public Boolean getExcludeUnlistedClasses() {
        return excludeUnlistedClasses;
    }

    public void setExcludeUnlistedClasses( Boolean value ) {
        this.excludeUnlistedClasses = value;
    }

    public PersistenceUnitCachingType getSharedCacheMode() {
        return sharedCacheMode;
    }

    public void setSharedCacheMode( PersistenceUnitCachingType value ) {
        this.sharedCacheMode = value;
    }

    public PersistenceUnitValidationModeType getValidationMode() {
        return validationMode;
    }

    public void setValidationMode( PersistenceUnitValidationModeType value ) {
        this.validationMode = value;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties( Properties value ) {
        this.properties = value;
    }

    public String getName() {
        return name;
    }

    public void setName( String value ) {
        this.name = value;
    }

    public PersistenceUnitTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType( PersistenceUnitTransactionType value ) {
        this.transactionType = value;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        PersistenceUnit that = ( PersistenceUnit ) o;

        if ( name != null ? !name.equals( that.name ) : that.name != null ) {
            return false;
        }
        if ( description != null ? !description.equals( that.description ) : that.description != null ) {
            return false;
        }
        if ( provider != null ? !provider.equals( that.provider ) : that.provider != null ) {
            return false;
        }
        if ( jtaDataSource != null ? !jtaDataSource.equals( that.jtaDataSource ) : that.jtaDataSource != null ) {
            return false;
        }
        if ( nonJtaDataSource != null ? !nonJtaDataSource.equals( that.nonJtaDataSource ) : that.nonJtaDataSource != null ) {
            return false;
        }
        if ( mappingFile != null ? !mappingFile.equals( that.mappingFile ) : that.mappingFile != null ) {
            return false;
        }
        if ( jarFile != null ? !jarFile.equals( that.jarFile ) : that.jarFile != null ) {
            return false;
        }
        if ( clazz != null ? !clazz.equals( that.clazz ) : that.clazz != null ) {
            return false;
        }
        if ( excludeUnlistedClasses != null ? !excludeUnlistedClasses.equals( that.excludeUnlistedClasses ) : that.excludeUnlistedClasses != null ) {
            return false;
        }
        if ( sharedCacheMode != that.sharedCacheMode ) {
            return false;
        }
        if ( validationMode != that.validationMode ) {
            return false;
        }
        if ( properties != null ? !properties.equals( that.properties ) : that.properties != null ) {
            return false;
        }
        return transactionType == that.transactionType;

    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ( description != null ? description.hashCode() : 0 );
        result = 31 * result + ( provider != null ? provider.hashCode() : 0 );
        result = 31 * result + ( jtaDataSource != null ? jtaDataSource.hashCode() : 0 );
        result = 31 * result + ( nonJtaDataSource != null ? nonJtaDataSource.hashCode() : 0 );
        result = 31 * result + ( mappingFile != null ? mappingFile.hashCode() : 0 );
        result = 31 * result + ( jarFile != null ? jarFile.hashCode() : 0 );
        result = 31 * result + ( clazz != null ? clazz.hashCode() : 0 );
        result = 31 * result + ( excludeUnlistedClasses != null ? excludeUnlistedClasses.hashCode() : 0 );
        result = 31 * result + ( sharedCacheMode != null ? sharedCacheMode.hashCode() : 0 );
        result = 31 * result + ( validationMode != null ? validationMode.hashCode() : 0 );
        result = 31 * result + ( properties != null ? properties.hashCode() : 0 );
        result = 31 * result + ( transactionType != null ? transactionType.hashCode() : 0 );
        return result;
    }
}
