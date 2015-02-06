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

package org.exoplatform.task.service.memory;

import org.exoplatform.task.domain.Label;
import org.exoplatform.task.domain.Project;
import org.exoplatform.task.domain.Task;
import org.exoplatform.task.service.TaskService;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:tuyennt@exoplatform.com">Tuyen Nguyen The</a>.
 */
public class TaskServiceMemImpl implements TaskService {
    private final List<Task> tasks;

    public TaskServiceMemImpl() {
        this.tasks = new LinkedList<Task>();
    }

    @Override
    public void save(Task task) {
        int index = this.tasks.indexOf(task);
        if(index != -1) {
            this.tasks.remove(index);
        }
        this.tasks.add(task);
    }

    @Override
    public Task findTaskById(long id) {
        for(Task t : this.tasks) {
            if(id == t.getId()) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<Task> findTaskByProject(Project project) {
        List<Task> ts = new LinkedList<Task>();
        for(Task t : this.tasks) {
            for(Project p : t.getProjects()) {
                if(p.getId() == project.getId()) {
                    ts.add(t);
                    break;
                }
            }
        }
        return ts;
    }

    @Override
    public List<Task> findTaskByLabel(Label label) {
        List<Task> ts = new LinkedList<Task>();
        for(Task t : this.tasks) {
            for(Label l : t.getLabels()) {
                if(l.getId() == label.getId()) {
                    ts.add(t);
                    break;
                }
            }
        }
        return ts;
    }
}
