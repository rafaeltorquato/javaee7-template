package study.business.application.service.impl;

import lombok.extern.slf4j.Slf4j;
import study.business.application.event.EmailEvent;
import study.business.application.service.EmailService;
import study.business.infrastructure.config.JmsConfig;
import study.components.interceptor.Logged;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@Slf4j
@Logged
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmailServiceImpl implements EmailService {

    @Resource(name = "mail/MailSession")
    private Session session;
    @Inject
    private JMSContext jmsContext;
    @Resource(lookup = JmsConfig.PENDING_EMAILS_QUEUE_NAME)
    private Queue queue;
    @Resource(name="java:comp/DefaultManagedExecutorService")
    private ManagedExecutorService managedExecutorService;

    @Override
    public Boolean send(String email, String subject, String message) {
        return execute(email, subject, message);
    }

    @Override
    @Asynchronous
    public Future<Boolean> sendAsync(String email, String subject, String message) {
        return new AsyncResult<>(execute(email, subject, message));
    }

    @Override
    public void queue(String email, String subject, String message) {
        jmsContext.createProducer().send(queue, new EmailEvent(email, subject, message));
    }

    private Boolean execute(final String email, final String subject, final String message) {
        final Future<Boolean> future = managedExecutorService.submit(new Callable<Boolean>() {
            @Override
            public Boolean call() {
                try {
                    Message mimeMessage = new MimeMessage(session);
                    mimeMessage.setFrom(InternetAddress.parse("test@test.com")[0]);
                    mimeMessage.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(email, false));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setHeader("X-Mailer", "JavaMail");
                    Date timeStamp = new Date();
                    mimeMessage.setText(message);
                    mimeMessage.setSentDate(timeStamp);
                    Transport.send(mimeMessage);
                    return true;
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    return false;
                }
            }
        });
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
