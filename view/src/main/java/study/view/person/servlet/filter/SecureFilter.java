package study.view.person.servlet.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter("/secure")
public class SecureFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Before chain.doFilter(request, response).");
        chain.doFilter(request, response);
        log.info("After chain.doFilter(request, response).");
    }

    @Override
    public void destroy() {

    }
}
