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

package org.exoplatform.task.test;

import org.exoplatform.task.domain.Priority;
import org.exoplatform.task.domain.Task;
import org.exoplatform.task.service.TaskCreator;
import org.exoplatform.task.service.impl.TaskCreatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author <a href="mailto:tuyennt@exoplatform.com">Tuyen Nguyen The</a>.
 */
public class TestTaskCreator {
    private TaskCreator creator;

    @Before
    public void setup() {
        this.creator = new TaskCreatorImpl();
    }

    @Test
    public void testParserHighPriority() {
        Task task = creator.buildTask("Test task !High tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be high", Priority.HIGH, task.getPriority());

        task = creator.buildTask("Test task !HIGH tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be High", Priority.HIGH, task.getPriority());

        task = creator.buildTask("Test task !higH tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be High", Priority.HIGH, task.getPriority());

        task = creator.buildTask("Test task !Low tomorrow !higH");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be High", Priority.HIGH, task.getPriority());
    }

    @Test
    public void testParserLowPriority() {
        Task task = creator.buildTask("Test task !Low tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be low", Priority.LOW, task.getPriority());

        task = creator.buildTask("Test task !LOW tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be low", Priority.LOW, task.getPriority());

        task = creator.buildTask("Test task !loW tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Priority must be low", Priority.LOW, task.getPriority());
    }

    @Test
    public void testParserAssignee() {
        Task task = creator.buildTask("Test task @john tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Assignee must be john", "john", task.getAssignee());

        task = creator.buildTask("Test task tomorrow @john");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
        Assert.assertEquals("Assignee must be john", "john", task.getAssignee());
    }

    @Test
    public void testParserDueDate() {
        Task task = creator.buildTask("Test task ^today tomorrow");
        Assert.assertNotNull(task);
        Assert.assertEquals("", "Test task tomorrow", task.getTitle());
    }
}
