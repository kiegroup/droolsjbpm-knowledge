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

package org.kie.api.runtime.process;

/**
 * An interface that represents an element that is listening
 * for specific types of events.
 */
public interface EventListener extends EventSignallable {

    /**
     * Returns the event types this event listener is interested in.
     * May return <code>null</code> if the event types are unknown.
     *
     * @return a {@link String} array of the types of events that this element listens to
     */
    String[] getEventTypes();

}
