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

import java.util.Arrays;
import java.util.Objects;

/**
 * Constants used by  PMML implementations
 */
public enum  PMMLConstants {

    KIE_PMML_IMPLEMENTATION("kie-pmml-implementation"),
    LEGACY("legacy"),
    NEW("new");


    private final String name;

    PMMLConstants(String name) {
        this.name = name;
    }

    public static PMMLConstants byName(String name) {
        return Arrays.stream(PMMLConstants.values()).filter(value -> Objects.equals(name, value.name)).findFirst().orElseThrow(() -> new RuntimeException("Failed to find PMMLConstants with name: " + name));
    }

    public String getName() {
        return name;
    }

}
