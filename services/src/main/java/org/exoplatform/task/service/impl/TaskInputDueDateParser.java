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

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author <a href="mailto:tuyennt@exoplatform.com">Tuyen Nguyen The</a>.
 */
public class TaskInputDueDateParser implements TaskInputParser {
    private static final Map<String, Integer> DAY_OF_WEEKS = new HashMap<String, Integer>();
    static {
        DAY_OF_WEEKS.put("monday", Calendar.MONDAY);
        DAY_OF_WEEKS.put("mon", Calendar.MONDAY);

        DAY_OF_WEEKS.put("tuesday", Calendar.TUESDAY);
        DAY_OF_WEEKS.put("tue", Calendar.TUESDAY);

        DAY_OF_WEEKS.put("wednesday", Calendar.WEDNESDAY);
        DAY_OF_WEEKS.put("wed", Calendar.WEDNESDAY);

        DAY_OF_WEEKS.put("thursday", Calendar.THURSDAY);
        DAY_OF_WEEKS.put("thu", Calendar.THURSDAY);

        DAY_OF_WEEKS.put("friday", Calendar.FRIDAY);
        DAY_OF_WEEKS.put("fri", Calendar.FRIDAY);

        DAY_OF_WEEKS.put("saturday", Calendar.SATURDAY);
        DAY_OF_WEEKS.put("sat", Calendar.SATURDAY);

        DAY_OF_WEEKS.put("sunday", Calendar.SUNDAY);
        DAY_OF_WEEKS.put("sun", Calendar.SUNDAY);
    }

    @Override
    public String parse(String input, TaskBuilder builder) {
        Pattern p = Pattern.compile("(\\s)(\\^)([a-zA-Z]+)");
        Matcher m = p.matcher(input);
        while(m.find()) {
            String value = m.group(3);
            System.out.println("Due date: " + value);
            Date date = this.parseDate(value);
            if(date != null) {
                builder.withDueDate(date);
            }
        }

        String in = input.replaceAll("\\s\\^[a-zA-Z]+\\s*", " ").trim();
        return in;
    }

    private Date parseDate(String dateString) {
        //TODO: how to process timezone?
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        if(dateString.equalsIgnoreCase("today")) {
            return calendar.getTime();
        }

        if("Tomorrow".equalsIgnoreCase(dateString)) {
            calendar.add(Calendar.DATE, 1);
            return calendar.getTime();
        }

        String key = dateString.toLowerCase();
        if(DAY_OF_WEEKS.containsKey(key)) {
            int dayOfWeek = DAY_OF_WEEKS.get(key);
            calendar.add(Calendar.DATE, 1);
            while(dayOfWeek != calendar.get(Calendar.DAY_OF_WEEK)) {
                calendar.add(Calendar.DATE, 1);
            }
            return calendar.getTime();
        }

        return null;
    }
}
