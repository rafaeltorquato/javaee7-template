package study.components.interceptor;


import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Slf4j
@Logged
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LogInterceptor {

    @AroundInvoke
    public Object aroundInvoke(InvocationContext invocationContext) throws Exception {
        String simpleName = invocationContext.getTarget().getClass().getSimpleName();
        String parameters = getParameters(invocationContext);

        log.info("Executing  method {}::{}({})", simpleName, invocationContext.getMethod().getName(), parameters);
        Object result = invocationContext.proceed();
        log.info("Method {}::{}({}) executed!", simpleName, invocationContext.getMethod().getName(), parameters);
        return result;
    }

    private String getParameters(InvocationContext invocationContext) {
        StringBuilder sb = new StringBuilder();
        Object[] parametersValue = invocationContext.getParameters();
        if (parametersValue != null) {
            String comma = "";
            for (Object o : parametersValue) {
                sb
                        .append(comma)
                        .append(o.getClass().getSimpleName())
                        .append(":")
                        .append(o);
                comma = ",";
            }
        }
        return sb.toString();
    }

}
