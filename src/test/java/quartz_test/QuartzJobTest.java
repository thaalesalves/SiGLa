/*
 * Copyright (C) 2018 thales
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package quartz_test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import util.IO;

/**
 *
 * @author thales
 */
public class QuartzJobTest implements Job {

    int i = 0;

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        IO.writeln("Serviço executado.");
        IO.writeln("Serviço rodado às: " + timeStamp + ", pela " + i + "º vez");
        i = i++;
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = JobBuilder.newJob(QuartzJobTest.class)
                    .withIdentity("myJob", "group1")
                    .build();

            String dataInicio = "25/02/2018 12:25";
            String dataFim = "25/02/2018 12:30";

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    //.startAt(IO.getData(dataInicio))
                    .endAt(IO.getData(dataFim))
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(60))
                    .build();
            sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
        }
    }
}
