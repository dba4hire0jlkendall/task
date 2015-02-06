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

import org.exoplatform.task.domain.Task;
import org.exoplatform.task.service.TaskCreator;

import java.util.ServiceLoader;

/**
 * @author <a href="mailto:tuyennt@exoplatform.com">Tuyen Nguyen The</a>.
 */
public class TaskCreatorImpl implements TaskCreator {
    @Override
    public Task buildTask(String input) {
        if(input == null) {
            return null;
        }

        TaskBuilder builder = new TaskBuilder();
        ServiceLoader<TaskInputParser> parsers = ServiceLoader.load(TaskInputParser.class, this.getClass().getClassLoader());
        String in = input;
        for(TaskInputParser parser : parsers) {
            in = parser.parse(in, builder);
        }
        builder.withTitle(in);

        return builder.build();
    }
}
