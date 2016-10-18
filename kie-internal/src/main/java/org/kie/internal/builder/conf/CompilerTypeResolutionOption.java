/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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
 * An Enum for ResolveTypesDuringCompilationOption option.
 *
 * drools.compiler.typeResolution = &lt;true|false&gt;
 *
 * DEFAULT = false
 *
 * When parsing a DRL file, drools can post-process the eclipse CompilationUnitDeclaration
 * result in order to resolve all java class references used in a DRL.
 *
 * This however does somewhat delay compilation, especially if there are many or large rules.
 * However, this option is necessary in the workbench in order to be able to find referenced
 * resources when refactoring.
 *
 * In order not to delay drools compilation outside of the workbench, this has been made an option.
 */
public enum CompilerTypeResolutionOption implements SingleValueKnowledgeBuilderOption {

    YES(true),
    NO(false);

    /**
     * The property name for the process string escapes option
     */
    public static final String PROPERTY_NAME = "drools.compiler.typeResolution";

    private boolean value;

    public static final boolean DEFAULT = Boolean.getBoolean(PROPERTY_NAME);

    CompilerTypeResolutionOption( final boolean value ) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    public String getPropertyName() {
        return PROPERTY_NAME;
    }

    public boolean isTypeResolution() {
        return this.value;
    }

}
