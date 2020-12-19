package study.business.infrastructure.jms;

import lombok.extern.slf4j.Slf4j;
import study.business.application.event.EmailEvent;
import study.business.application.service.EmailService;
import study.business.infrastructure.config.JmsConfig;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Slf4j
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = JmsConfig.PENDING_EMAILS_QUEUE_NAME),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class SendEmailConsumer implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;

    @EJB
    private EmailService emailService;

    @Override
    public void onMessage(Message message) {
        try {
            final EmailEvent emailEvent = message.getBody(EmailEvent.class);
            emailService.send(emailEvent.getEmail(), emailEvent.getSubject(), emailEvent.getMessage());
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
            mdc.setRollbackOnly();
        }
    }

    //    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
//    private ConnectionFactory connectionFactory;
//
//    @PostConstruct
//    public void init() {
//        //Queue
//        try(JMSContext context = connectionFactory.createContext()) {
//            context.setAutoStart(false);
//            context.setClientID("xpto");
//            //If the destination is a Topic, it create a nondurable unshared subscription
//            final JMSConsumer queueConsumer = context.createConsumer(null);
//            queueConsumer.setMessageListener(new MessageListener() {
//                @Override
//                public void onMessage(Message message) {
//
//                }
//            });
//
//            //If the destination is a Topic, it create a nondurable unshared subscription
//            final String nameOfSubscription = "NameOfSubscription";
//            final JMSConsumer topicConsumer = context.createDurableConsumer(null, nameOfSubscription);
//            topicConsumer.setMessageListener(new MessageListener() {
//                @Override
//                public void onMessage(Message message) {
//
//                }
//            });
//            //to unsubscribe
////            topicConsumer.close();
////            context.unsubscribe(nameOfSubscription);
//            context.start();
//        }
//
//    }
}
