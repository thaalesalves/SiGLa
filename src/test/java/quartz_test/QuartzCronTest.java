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

import actions_test.SoftwareLicencaTeste;
import dao.DAOFactory;
import dao.dao.SoftwareDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Software;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import util.IO;

/**
 *
 * @author thales
 */
public class QuartzCronTest implements Job {

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
            IO.writeln("Serviço rodado às: " + timeStamp);
            DAOFactory fac = DAOFactory.getFactory();
            Software sw = new Software();
            SoftwareDAO swdao = new dao.dao.psql.SoftwareDAOPsql();
            SoftwareLicencaTeste.LicencaDAO ldao = new SoftwareLicencaTeste.LicencaDAO();
            
            sw.setId(1);
            sw = swdao.selectId(sw);
            sw = ldao.selectLicenca(sw);
            String dateToday = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

            IO.writeln("Data de vencimento: " + IO.formatData(sw.getLicenca().getDataVencimento()));
            IO.writeln("Data de aquisição: " + IO.formatData(sw.getLicenca().getDataAquisicao()));
            IO.writeln("Hoje: " + dateToday);
            
            if (sw.getLicenca().getDataVencimento().equals(dateToday)) {
                IO.writeln("Hora de renovar!");
            } else {
                IO.writeln("Opa, dá pra esperar!");
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schedFact = new StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            JobDetail job = JobBuilder.newJob(QuartzJobTest.class)
                    .withIdentity("testeCron", "testeQuartz")
                    .build();

            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity("testeCron", "testeQuartz")
                    .startNow()
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 1/1 * ? *"))
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * 1/1 * ? *"))
                    .build();
            sched.scheduleJob(job, trigger);
        } catch (Exception e) {
            System.out.println("erro");
            e.printStackTrace();
        }
    }
}
