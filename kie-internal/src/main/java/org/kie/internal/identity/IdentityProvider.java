/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
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

package org.kie.internal.identity;

import java.util.List;

public interface IdentityProvider {

    public static final String UNKNOWN_USER_IDENTITY = "unknown";

    String getName();

    default List<String> getRolesFor(String userId) {
        return getRoles(); // current identity (you cannot bypass)
    }

    default boolean hasRoleFor(String userId, String role) {
        return getRolesFor(userId).contains(role);
    }

    List<String> getRoles();

    default boolean hasRole(String role) {
        return getRoles().contains(role);
    }

    default void setContextIdentity(String userId) {
        // do nothing
    }

    default void removeContextIdentity() {
        // do nothing
    }
}
