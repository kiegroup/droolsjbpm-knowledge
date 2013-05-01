/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.kie.internal.task.api;

import java.util.Date;
import java.util.List;

import org.kie.api.task.model.Status;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;


/**
 * The Task Query Service will contain all the methods 
 *  to get information about the current Task Instances. 
 *  Most of the times these methods will be used to build
 *  User Interfaces, and we should not include any method 
 *  related with Task Statistics here. 
 */
public interface TaskQueryService {

    List<TaskSummary> getTasksAssignedAsBusinessAdministrator(String userId, String language);

    List<TaskSummary> getTasksAssignedAsExcludedOwner(String userId, String language);

    List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, String language);

    List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, List<String> groupIds, String language);

    List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, List<String> groupIds, String language, int firstResult, int maxResults);

    List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatus(String userId, List<Status> status, String language);
    
    List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatusByGroup(String userId, List<String> groupIds, List<Status> status, String language);
    
    List<TaskSummary> getTasksAssignedAsRecipient(String userId, String language);

    List<TaskSummary> getTasksAssignedAsTaskInitiator(String userId, String language);

    List<TaskSummary> getTasksAssignedAsTaskStakeholder(String userId, String language);

    List<TaskSummary> getTasksAssignedByGroup(String groupId, String language);
    
    List<TaskSummary> getTasksAssignedByGroups(List<String> groupsId, String language);
    
    List<TaskSummary> getTasksAssignedByGroupsByExpirationDate(List<String> groupIds, String language, Date expirationDate);
    
    List<TaskSummary> getTasksAssignedByGroupsByExpirationDateByTaskName(List<String> groupIds, String language, Date expirationDate, String taskName);
    
    List<TaskSummary> getTasksAssignedByGroupsByExpirationDateOptional(List<String> groupIds, String language, Date expirationDate);

    List<TaskSummary> getTasksAssignedByGroupsByExpirationDateOptionalByTaskName(List<String> groupIds, String language, Date expirationDate, String taskName);

    List<TaskSummary> getTasksOwned(String userId, String language);

    List<TaskSummary> getTasksOwnedByStatus(String userId, List<Status> status, String language);

    List<TaskSummary> getTasksOwnedByStatusByTaskName(String userId, List<Status> status, String language, String taskName);
    
    List<TaskSummary> getTasksOwnedByExpirationDate(String userId, List<Status> status, Date expirationDate);

    List<TaskSummary> getTasksOwnedByExpirationDateByTaskName(String userId, List<Status> statuses, String language, Date expirationDate, String taskName);

    List<TaskSummary> getTasksOwnedByExpirationDateOptional(String userId, List<Status> status, Date expirationDate);

    List<TaskSummary> getTasksOwnedByExpirationDateOptionalByTaskName(String userId, List<Status> statuses, String language, Date expirationDate, String taskName);

    List<TaskSummary> getTasksOwnedByExpirationDateBeforeSpecifiedDate(String userId, List<Status> status, Date date);
    
    List<TaskSummary> getSubTasksAssignedAsPotentialOwner(long parentId, String userId, String language);
    
    List<TaskSummary> getSubTasksByParent(long parentId);
    
    List<TaskSummary> getTasksByStatusByProcessInstanceId(long processInstanceId, List<Status> status, String language);

    List<TaskSummary> getTasksByStatusByProcessInstanceIdByTaskName(long processInstanceId, List<Status> status, String taskName, String language);

    int getPendingSubTasksByParent(long parentId);
    
    Task getTaskByWorkItemId(long workItemId);
    
    Task getTaskInstanceById(long taskId);
    
    List<Long> getTasksByProcessInstanceId(long processInstanceId);
    
    
}
