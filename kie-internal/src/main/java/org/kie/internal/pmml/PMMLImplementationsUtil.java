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
package org.kie.internal.pmml;

import org.kie.api.pmml.PMMLConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.kie.api.pmml.PMMLConstants.KIE_PMML_IMPLEMENTATION;
import static org.kie.api.pmml.PMMLConstants.LEGACY;
import static org.kie.api.pmml.PMMLConstants.NEW;

/**
 * Class used to provide utility methods to manage implementation to be invoked
 * at runtime
 */
public class PMMLImplementationsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PMMLImplementationsUtil.class);
    private static final String LEGACY_IMPL = "org.kie.pmml.assembler.PMMLAssemblerService";
    private static final String TRUSTY_IMPL = "org.kie.pmml.evaluator.assembler.service.PMMLAssemblerService";

    /**
     * @param classLoader
     * @return the <b>PMML</b> implementation to enable
     */
    public static PMMLConstants toEnable(final ClassLoader classLoader) {
        boolean isLegacyPresent = isImplementationPresent(LEGACY_IMPL, classLoader);
        boolean isTrustyPresent = isImplementationPresent(TRUSTY_IMPL, classLoader);
        if (!isLegacyPresent && !isTrustyPresent) {
            throw new IllegalStateException("Could not find PMML implementation");
        }
        PMMLConstants pmmlConstants = PMMLConstants.byName(System.getProperty(KIE_PMML_IMPLEMENTATION.getName(), LEGACY.getName()));
        switch (pmmlConstants) {
            case LEGACY:
                if (isLegacyPresent) {
                    return LEGACY;
                } else {
                    return NEW;
                }
            case NEW:
                if (isTrustyPresent) {
                    return NEW;
                } else {
                    return LEGACY;
                }
            default:
                throw new RuntimeException("Unmanaged PMMLConstants " + pmmlConstants);
        }
    }

    /**
     * @param classLoader
     * @return <code>true</code> if the <b>implementationFullName</b> is found in the given <code>ClassLoader</code>,
     * <code>false</code> otherwise
     */
    private static boolean isImplementationPresent(final String implementationFullName, final ClassLoader classLoader) {
        try {
            classLoader.loadClass(implementationFullName);
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
