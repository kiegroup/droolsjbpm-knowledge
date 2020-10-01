/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
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

package org.kie.internal.builder.conf;

/**
 * An Enum for AlphaNetworkCompilerOption option.
 *
 * drools.alphaNetworkCompiler = &lt;true|false&gt;
 *
 * DEFAULT = false
 */
public enum AlphaNetworkCompilerOption implements SingleValueKnowledgeBuilderOption {

    ENABLED(true),
    DISABLED(false);

    /**
     * The property name for the sequential mode option
     */
    public static final String PROPERTY_NAME = "drools.alphaNetworkCompiler";

    private boolean value;

    AlphaNetworkCompilerOption(final boolean value ) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName() {
        return PROPERTY_NAME;
    }

    public boolean isAlphaNetworkCompilerEnabled() {
        return this.value;
    }

}
