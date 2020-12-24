package study.jspview.person.servlet;

import lombok.extern.slf4j.Slf4j;
import study.facade.person.PersonFacade;
import study.facade.person.dto.PersonDTO;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMINISTRATOR", "LIST_PERSON"}))
@WebServlet(value = "/secure/person", initParams = {@WebInitParam(name = "closed", value = "false")})
public class IndexPersonServlet extends HttpServlet {

    private static final String JSP_PAGE = "/secure/person/index.jsp";

    private Boolean closed;

    @EJB
    private PersonFacade personFacade;

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        this.closed = Boolean.valueOf(config.getInitParameter("closed"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletInputStream inputStream = req.getInputStream();
//        inputStream.setReadListener(new ReadListener() {
//            @Override
//            public void onDataAvailable() throws IOException {
//
//            }
//
//            @Override
//            public void onAllDataRead() throws IOException {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//        });
//        ServletOutputStream outputStream = resp.getOutputStream();
//        outputStream.setWriteListener(new WriteListener() {
//            @Override
//            public void onWritePossible() throws IOException {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//        });
        if (closed) {
            //TODO Return message
        }
        log.info("Context path: {}", req.getContextPath());
        log.info("Servlet path: {}", req.getServletPath());
        log.info("Path info: {}", req.getPathInfo());
        List<PersonDTO> persons = personFacade.list();
        log.info("{}", persons);
        req.setAttribute("list", persons);
        req.getRequestDispatcher(JSP_PAGE).forward(req, resp);

    }

}
