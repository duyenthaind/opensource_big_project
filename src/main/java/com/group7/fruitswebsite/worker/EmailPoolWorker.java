package com.group7.fruitswebsite.worker;

import com.group7.fruitswebsite.common.Constants;
import com.group7.fruitswebsite.creational.EmailServiceFactory;
import com.group7.fruitswebsite.job.EmailPoolJob;
import com.group7.fruitswebsite.job.PoolJob;
import com.group7.fruitswebsite.service.EmailService;
import com.group7.fruitswebsite.util.RenderMailHelper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author duyenthai
 */
@Log4j
public class EmailPoolWorker extends PoolWorker {

    public EmailPoolWorker(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (isRunning.get()) {
            try {
                PoolJob job = queue.poll();
                if (job instanceof EmailPoolJob) {
                    runSendMail((EmailPoolJob) job);
                }
            } catch (Exception ex) {
                log.error("Error while executing job", ex);
            }
            if (queue.isEmpty()) {
                try {
                    // When there is no job, sleep 30s to increase performance
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException ex) {
                    log.error("Sleep worker error", ex);
                }
            }
        }
    }

    private void runSendMail(EmailPoolJob job) {
        log.info(String.format("Process job %s at %s", job, new Date()));
        String body = StringUtils.EMPTY;
        Constants.JobType jobType = job.getJobType();
        switch (jobType) {
            case EMAIL_ORDER:
                body = RenderMailHelper.renderBodyMailOrder(job.getUsername(), (Integer) job.getCustoms().get("orderId"));
                break;
            default:
                break;
        }
        if (StringUtils.isEmpty(body)) {
            log.error(String.format("Cannot run job %s because mail body is empty", job));
            return;
        }
        EmailService emailService = EmailServiceFactory.getEmailService();
        if (Objects.nonNull(emailService)) {
            emailService.send(job.getEmail(), body);
        }
    }

    public static void pubJob(EmailPoolJob job) {
        log.info(String.format("Publish a job send mail %s", job));
        PoolWorker.pubJob(job, EmailPoolWorker.class);
    }

}
