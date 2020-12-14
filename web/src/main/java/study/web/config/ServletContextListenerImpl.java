package study.web.config;

import study.web.person.wsocket.AddPersonEndpoint;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;

@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        addProgrammaticEndpoints(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void addProgrammaticEndpoints(ServletContextEvent sce) {
        try {
            final ServerEndpointConfig config = ServerEndpointConfig.Builder.create(AddPersonEndpoint.class, "/socket/person/add").build();
            final ServerContainer container = (ServerContainer) sce.getServletContext().getAttribute(ServerContainer.class.getName());
            container.addEndpoint(config);
        } catch (DeploymentException e) {
            throw new RuntimeException(e);
        }
    }

}
