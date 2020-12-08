package study.business.application.config;

import javax.enterprise.context.ApplicationScoped;
import javax.jms.JMSConnectionFactoryDefinition;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;


@JMSConnectionFactoryDefinition(
        name="java:app/jms/DefaultConnectionFactory",
        clientId = "javaee7-template"
)
@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = JmsConfig.PENDING_EMAILS_QUEUE_NAME,
                destinationName = "PendingEmailsQueue",
                interfaceName = "javax.jms.Queue"
        )
})
@ApplicationScoped
public class JmsConfig {
    public static final String PENDING_EMAILS_QUEUE_NAME = "java:app/jms/PendingEmailsQueue";
}
