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
package timer;

import dao.DAOFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import mailsender.Mail;
import mailsender.VencimentoHojeMail;
import model.Licenca;
import model.Software;
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
import util.Logger;

public class ChecagemVencimentoLicenca implements Job {

    private Software sw = new Software();

    public void vencimento(String data) {
        try {
            DAOFactory fac = DAOFactory.getFactory();
            Licenca licenca = new Licenca();
            licenca.setDataVencimento(data);
            List<Licenca> licencas = fac.getLicencaDAO().selectVencimento(licenca);

            if (licencas != null) {
                //IO.writeln("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()) + "]: "
                //        + licencas.size() + " licenças vencerão hoje! Elas não serão mais exibidas como ativas a partir de agora.");
                for (Licenca i : licencas) {
                    Mail mail = new VencimentoHojeMail();
                    i.setSoftware(fac.getSoftwareDAO().selectId(i.getSoftware()));
                    fac.getLicencaDAO().desativa(i);
                    mail.setLicenca(i);
                    mail.sendMail(mail);
                    Logger.logOutput("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()) + "]: "
                            + "Enviado email de aviso sobre " + i.getSoftware().getFabricante() + " " + i.getSoftware().getNome() + ". "
                            + "Este software vence em " + data + ".");
                }
            }
        } catch (Exception e) {
            Logger.logSevere(e, ChecagemVencimentoLicenca.class);
        }
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            Calendar cal = Calendar.getInstance();
            vencimento(new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime()));
            cal.add(Calendar.DATE, 3);
            vencimento(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
            cal.add(Calendar.DATE, 4);
            vencimento(new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime()));
        } catch (Exception e) {
            Logger.logSevere(e, ChecagemVencimentoLicenca.class);
        }
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schFac = new StdSchedulerFactory();
            Scheduler sch = schFac.getScheduler();
            sch.start();
            JobDetail j = JobBuilder.newJob(ChecagemVencimentoLicenca.class)
                    .withIdentity("aviso_licenca", "licenca")
                    .build();

            Trigger t = TriggerBuilder
                    .newTrigger()
                    .withIdentity("aviso_licenca_trigger", "licenca")
                    .startNow()
                    //.withSchedule(CronScheduleBuilder.cronSchedule("0 30 23 1/1 * ? *"))
                    .build();

            IO.writeln("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime()) + "]: Serviço de checagem de licenças iniciado.");
            sch.scheduleJob(j, t);
        } catch (Exception e) {
            Logger.logSevere(e, ChecagemVencimentoLicenca.class);
        }
    }
}
