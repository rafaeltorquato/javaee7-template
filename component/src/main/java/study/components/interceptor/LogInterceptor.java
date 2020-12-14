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
        log.info("Executing {}::{}", simpleName, invocationContext.getMethod().getName());
        Object result = invocationContext.proceed();
        log.info("{}::{} executed!", simpleName,invocationContext.getMethod().getName());
        return result;
    }

}
