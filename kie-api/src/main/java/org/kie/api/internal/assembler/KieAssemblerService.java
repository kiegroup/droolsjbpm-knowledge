/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.kie.api.internal.assembler;

import java.util.List;
import java.util.function.Consumer;

import org.kie.api.internal.utils.KieService;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceConfiguration;
import org.kie.api.io.ResourceType;

public interface KieAssemblerService extends KieService {
    ResourceType getResourceType();

    public static class ResourceAndConfig {

        private final Resource resource;
        private final ResourceConfiguration resourceConfiguration;
        private final Consumer<Object> beforeAdd;
        private final Consumer<Object> afterAdd;

        /**
         * 
         * @param resource 
         * @param resourceConfiguration
         * @param beforeAdd callback executed on `kbuilder` as a paramenter, which will be executed before performing {@link KieAssemblerService#addResource(Object, Resource, ResourceType, ResourceConfiguration)} for the given resource {@link #resource}
         * @param afterAdd callback executed on `kbuilder` as a paramenter, which will be executed after performing {@link KieAssemblerService#addResource(Object, Resource, ResourceType, ResourceConfiguration)} for the given resource {@link #resource}
         */
        public ResourceAndConfig(Resource resource, ResourceConfiguration resourceConfiguration, Consumer<Object> beforeAdd, Consumer<Object> afterAdd) {
            this.resource = resource;
            this.resourceConfiguration = resourceConfiguration;
            this.beforeAdd = beforeAdd;
            this.afterAdd = afterAdd;
        }

        public Resource getResource() {
            return resource;
        }

        public ResourceConfiguration getResourceConfigutation() {
            return resourceConfiguration;
        }

        public Consumer<Object> getBeforeAdd() {
            return beforeAdd;
        }

        public Consumer<Object> getAfterAdd() {
            return afterAdd;
        }

    }

    default void addResources(Object kbuilder, List<ResourceAndConfig> resources, ResourceType type) throws Exception {
        for (ResourceAndConfig rd : resources) {
            rd.getBeforeAdd().accept(kbuilder);
            addResource(kbuilder, rd.getResource(), type, rd.getResourceConfigutation());
            rd.getAfterAdd().accept(kbuilder);
        }
    }

    void addResource(Object kbuilder, Resource resource, ResourceType type, ResourceConfiguration configuration) throws Exception;
}