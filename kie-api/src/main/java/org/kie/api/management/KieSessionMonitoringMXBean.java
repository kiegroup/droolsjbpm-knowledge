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

package org.kie.api.management;

import java.util.Date;
import java.util.Map;

import javax.management.ObjectName;

/**
 * An MBean interface for {@link org.kie.api.runtime.KieSession} monitoring
 */
public interface KieSessionMonitoringMXBean extends GenericKieSessionMonitoringMXBean {
    /**        
     * @return the total fact count current loaded into the session      
     */       
    long getTotalFactCount();
}