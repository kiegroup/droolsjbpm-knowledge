/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.api.conf;

/**
 * An Enum for AlphaNodeOrdering option.
 *
 * drools.alphaNodeOrdering = &lt;count|none&gt;
 *
 * count : Reorder alpha nodes based on usage count
 * none  : Leave alpha node order as written in rules
 *
 * DEFAULT = none
 */
public enum AlphaNodeOrderingOption implements SingleValueKieBaseOption {

    COUNT("count"),
    NONE("none");

    /**
     * The property name for the AlphaNodeOrdering option
     */
    public static final String PROPERTY_NAME = "drools.alphaNodeOrdering";

    private String value;

    AlphaNodeOrderingOption(String option) {
        this.value = option;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName() {
        return PROPERTY_NAME;
    }

    public String getValue() {
        return value;
    }

    public static AlphaNodeOrderingOption determineAlphaNodeOrdering(String option) {
        if (COUNT.getValue().equalsIgnoreCase(option)) {
            return COUNT;
        } else if (NONE.getValue().equalsIgnoreCase(option)) {
            return NONE;
        }
        throw new IllegalArgumentException("Illegal enum value '" + option + "' for AlphaNodeOrderingOption");
    }
}
