package study.business.application.misc;


import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LogInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        String simpleName = invocationContext.getTarget().getClass().getSimpleName();
        Logger logger = Logger.getLogger(simpleName);
        logger.info("Executing " + simpleName + "::" + invocationContext.getMethod().getName());
        Object result = invocationContext.proceed();
        logger.info(simpleName + "::" + invocationContext.getMethod().getName() + " executed!");
        return result;
    }

}
