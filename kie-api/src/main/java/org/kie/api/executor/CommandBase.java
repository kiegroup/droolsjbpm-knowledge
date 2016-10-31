/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
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

package org.kie.api.executor;

/**
 * Executor's Command are dedicated to contain purely business logic that should be executed.
 * It should not have any reference to underlying process engine and should not be concerned
 * with any process runtime related logic such us completing work item, sending signals, etc.
 * <br>
 * Base interface for Command and CommandAsync
 */
public interface CommandBase {

}
