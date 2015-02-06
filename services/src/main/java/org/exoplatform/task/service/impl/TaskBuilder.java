/*
* JBoss, a division of Red Hat
* Copyright 2006, Red Hat Middleware, LLC, and individual contributors as indicated
* by the @authors tag. See the copyright.txt in the distribution for a
* full listing of individual contributors.
*
* This is free software; you can redistribute it and/or modify it
* under the terms of the GNU Lesser General Public License as
* published by the Free Software Foundation; either version 2.1 of
* the License, or (at your option) any later version.
*
* This software is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
* Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public
* License along with this software; if not, write to the Free
* Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
* 02110-1301 USA, or see the FSF site: http://www.fsf.org.
*/

package org.exoplatform.task.service.impl;

import org.exoplatform.task.domain.*;

import java.util.Date;
import java.util.Set;

/**
 * @author <a href="mailto:tuyennt@exoplatform.com">Tuyen Nguyen The</a>.
 */
public class TaskBuilder {
    private String title;
    private String description;

    private Priority priority = Priority.UNDEFINED;
    private String context;
    private String assignee;

    private Status status;
    private Set<Label> labels;
    private Set<Project> projects;

    private String createdBy;
    private Date createdTime = new Date();

    private long duration;
    private Date startDate;
    private Date dueDate;

    public Task build() {
        Task task = new Task();

        task.setTitle(title);
        task.setDescription(description);
        task.setPriority(priority);
        task.setContext(context);
        task.setAssignee(assignee);
        task.setStatus(status);
        task.setLabels(labels);
        task.setProjects(projects);
        task.setCreatedBy(createdBy);
        task.setCreatedTime(createdTime);
        task.setDuration(duration);
        task.setStartDate(startDate);
        task.setDueDate(dueDate);

        return task;
    }

    public TaskBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    public TaskBuilder withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    public TaskBuilder withAssignee(String assignee) {
        this.assignee = assignee;
        return this;
    }

    public TaskBuilder withDueDate(Date date) {
        this.dueDate = date;
        return this;
    }

}
