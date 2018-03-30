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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import mailsender.Mail;
import mailsender.VencimentoHojeMail;
import mailsender.VencimentoSeteDiasMail;
import mailsender.VencimentoTresDiasMail;
import model.Licenca;
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
import util.Logger;

public class VencimentoLicenca implements Job {

    public enum VENCIMENTO_LICENCA {
        HOJE(0, new VencimentoHojeMail()),
        TRES_DIAS(3, new VencimentoTresDiasMail()),
        SETE_DIAS(7, new VencimentoSeteDiasMail());

        private Integer quantidadeDias;
        private Mail emailNotificacao;

        private VENCIMENTO_LICENCA(Integer quantidadeDias, Mail emailNotificacao) {
            this.quantidadeDias = quantidadeDias;
            this.emailNotificacao = emailNotificacao;
        }
    }

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        try {
            SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");

            for (VENCIMENTO_LICENCA prazo : VENCIMENTO_LICENCA.values()) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, prazo.quantidadeDias);
                Date date = data.parse(data.format(cal.getTime()));
                Licenca l = new Licenca();
                l.setDataVencimento(IO.getData(data.format(cal.getTime())));

                List<Licenca> licencas = DAOFactory.getFactory().getLicencaDAO().selectVencimento(l);

                for (Licenca i : licencas) {
                    Logger.logOutput(i.getSoftware().getFabricante() + " " + i.getSoftware().getNome() + "vence "
                            + ((prazo.quantidadeDias == 0) ? "hoje. " : "em " + prazo.quantidadeDias + " dias (" + i.getDataVencimento() + "). ")
                            + "Enviando email de aviso.");
                    Mail mail = prazo.emailNotificacao;
                    mail.setLicenca(i);
                    mail.sendMail(mail);
                    
                    if (i.venceHoje(date)) {
                        DAOFactory.getFactory().getLicencaDAO().desativa(i);
                    }
                }
            }
        } catch (Exception e) {
            Logger.logSevere(e, VencimentoLicenca.class);
        }
    }

    public static void main(String[] args) {
        try {
            SchedulerFactory schFac = new StdSchedulerFactory();
            Scheduler sch = schFac.getScheduler();
            sch.start();
            JobDetail j = JobBuilder.newJob(VencimentoLicenca.class)
                    .withIdentity("aviso_licenca", "licenca")
                    .build();

            Trigger t = TriggerBuilder
                    .newTrigger()
                    .withIdentity("aviso_licenca_trigger", "licenca")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 30 23 1/1 * ? *"))
                    .build();

            Logger.logOutput("Serviço de checagem de licenças iniciado.");
            sch.scheduleJob(j, t);
        } catch (Exception e) {
            Logger.logSevere(e, VencimentoLicenca.class);
        }
    }
}
