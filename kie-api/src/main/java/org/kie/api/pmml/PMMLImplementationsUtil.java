/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.api.pmml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.kie.api.pmml.PMMLConstants.KIE_PMML_IMPLEMENTATION;
import static org.kie.api.pmml.PMMLConstants.LEGACY;

/**
 * Class used to provide utility methods to manage implementation to be invoked
 * at runtime
 */
public class PMMLImplementationsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PMMLImplementationsUtil.class);

    /**
     * @param classLoader
     * @return <code>true</code> if the <b>otherImplementationFullName</b> is not found in the given <code>ClassLoader</code> <b>OR</b>
     * if the  <code>KIE_PMML_IMPLEMENTATION</code> property is equals to the given <b>requestingImplementation</b>;
     * return <code>false</code> otherwise
     */
    public static boolean isToEnable(final String otherImplementationFullName, final ClassLoader classLoader, final String requestingImplementation) {
        if (!isOtherImplementationPresent(otherImplementationFullName, classLoader)) {
            return true;
        } else {
            final String property = System.getProperty(KIE_PMML_IMPLEMENTATION.getName(), LEGACY.getName());
            return property.equals(requestingImplementation);
        }
    }

    /**
     * @param classLoader
     * @return <code>true</code> if the <b>otherImplementationFullName</b> is found in the given <code>ClassLoader</code>,
     * <code>false</code> otherwise
     */
    public static boolean isOtherImplementationPresent(final String otherImplementationFullName, final ClassLoader classLoader) {
        try {
            classLoader.loadClass(otherImplementationFullName);
            return true;
        } catch (NoClassDefFoundError | ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * @param classLoader
     * @return <code>true</code> if <b>org.kie.dmn.jpmml.DMNjPMMLInvocationEvaluator</b> is found in the given <code>ClassLoader</code>,
     * <code>false</code> otherwise
     */
    public static boolean isjPMMLAvailableToClassLoader(final ClassLoader classLoader) {
        try {
            classLoader.loadClass("org.kie.dmn.jpmml.DMNjPMMLInvocationEvaluator");
            LOGGER.info("jpmml libraries available on classpath, skipping kie-pmml parsing and compilation");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
