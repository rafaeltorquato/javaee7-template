package study.web.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
//        try {
//            final ServerEndpointConfig config = ServerEndpointConfig.Builder
//                    .create(AddPersonEndpoint.class, AddPersonEndpoint.MY_URL)
//                    .build();
//            final ServerContainer container = (ServerContainer) sce.getServletContext().getAttribute(ServerContainer.class.getName());
//            container.addEndpoint(config);
//        } catch (DeploymentException e) {
//            throw new RuntimeException(e);
//        }
    }

}
