package study.business.application.service.impl;

import study.business.application.config.JmsConfig;
import study.business.application.event.EmailEvent;
import study.business.application.service.EmailService;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = Logger.getLogger(EmailServiceImpl.class.getName());

    @Resource(name = "mail/MailSession")
    private Session session;

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = JmsConfig.PENDING_EMAILS_QUEUE_NAME)
    private Queue queue;

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

    private Boolean execute(String email, String subject, String message) {
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
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

}
