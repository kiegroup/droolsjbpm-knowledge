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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "" )
public class Property implements Serializable {

    @XmlAttribute( name = "name", required = true )
    protected String name;

    @XmlAttribute( name = "value", required = true )
    protected String value;

    public Property() {
    }

    public Property( String name, String value ) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName( String value ) {
        this.name = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue( String value ) {
        this.value = value;
    }

    @Override public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }

        Property property = ( Property ) o;

        if ( name != null ? !name.equals( property.name ) : property.name != null ) {
            return false;
        }
        return !( value != null ? !value.equals( property.value ) : property.value != null );

    }

    @Override public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + ( value != null ? value.hashCode() : 0 );
        return result;
    }
}
