/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.kie.api.internal.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.junit.Test;
import org.kie.api.internal.assembler.KieAssemblerService;
import org.kie.api.io.ResourceType;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ServiceDiscoveryImplTest {

    @Test
    public void testServiceAndChildServiceInSameKieConf() {
        final ServiceDiscoveryImpl serviceDiscovery = ServiceDiscoveryImpl.getInstance();
        final Map<String, Object> services = serviceDiscovery.getServices();
        assertTrue(services.size() == 1);

        final Object service = services.get("org.kie.api.internal.assembler.KieAssemblers");
        assertNotNull(service);
        assertTrue(service instanceof MockAssemblersImpl);

        final Map<ResourceType, KieAssemblerService> childServices = ((MockAssemblersImpl) service).getAssemblers();
        assertTrue(childServices.size() == 1);
        assertNotNull(childServices.get(ResourceType.DRL));
        assertTrue(childServices.get(ResourceType.DRL) instanceof MockChildAssemblerService);
    }

    @Test
    public void testDuplicatedServiceShouldFail() {
        ServiceDiscoveryImpl serviceDiscovery =  new ServiceDiscoveryImpl();
        ClassLoader cl = ServiceDiscoveryImplTest.class.getClassLoader();

        try {
            serviceDiscovery.registerConfs( cl, getUrl( cl, "META-INF/kie.conf.test1" ) );
            serviceDiscovery.registerConfs( cl, getUrl( cl, "META-INF/kie.conf.test2" ) );
            serviceDiscovery.getServices();
            fail( "Trying to load a duplicated service should fail" );
        } catch (Exception e) {
            System.out.println( e.getMessage() );
        }
    }

    @Test
    public void testLoadServiceWithHighestPriority() {
        ServiceDiscoveryImpl serviceDiscovery =  new ServiceDiscoveryImpl();
        ClassLoader cl = ServiceDiscoveryImplTest.class.getClassLoader();

        serviceDiscovery.registerConfs( cl, getUrl( cl, "META-INF/kie.conf.test3" ) );
        serviceDiscovery.registerConfs( cl, getUrl( cl, "META-INF/kie.conf.test1" ) );
        Map<String, Object> services = serviceDiscovery.getServices();

        Object service = services.get("org.kie.api.internal.assembler.KieAssemblers");
        assertNotNull(service);
        assertTrue(service instanceof AnotherMockAssemblersImpl);
    }

    protected URL getUrl( ClassLoader cl, String resourceName ) {
        try {
            return cl.getResources( resourceName ).nextElement();
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}