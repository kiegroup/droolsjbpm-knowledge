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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlType( propOrder = { "persistenceUnit" } )
@XmlAccessorType( XmlAccessType.NONE )
@XmlRootElement( name = "persistence" )
public class PersistenceDescriptorImpl implements PersistenceDescriptor, Serializable {

    @XmlElement( name = "persistence-unit", required = true )
    protected List<PersistenceUnit> persistenceUnit;

    @XmlAttribute( name = "version", required = true )
    @XmlJavaTypeAdapter( CollapsedStringAdapter.class )
    protected String version;

    public PersistenceDescriptorImpl() {
    }

    public List<PersistenceUnit> getPersistenceUnit() {
        if ( persistenceUnit == null ) {
            persistenceUnit = new ArrayList<PersistenceUnit>();
        }
        return this.persistenceUnit;
    }

    public void setPersistenceUnit( List<PersistenceUnit> persistenceUnit ) {
        if ( persistenceUnit == null ) {
            this.persistenceUnit = new ArrayList<PersistenceUnit>();
        } else {
            this.persistenceUnit = persistenceUnit;
        }
    }

    public String getVersion() {
        if ( version == null ) {
            return "2.0";
        } else {
            return version;
        }
    }

    public void setVersion( String value ) {
        this.version = value;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        PersistenceDescriptorImpl that = ( PersistenceDescriptorImpl ) o;

        if ( persistenceUnit != null ? !persistenceUnit.equals( that.persistenceUnit ) : that.persistenceUnit != null ) {
            return false;
        }
        return !( version != null ? !version.equals( that.version ) : that.version != null );

    }

    @Override public int hashCode() {
        int result = persistenceUnit != null ? persistenceUnit.hashCode() : 0;
        result = 31 * result + ( version != null ? version.hashCode() : 0 );
        return result;
    }
}
