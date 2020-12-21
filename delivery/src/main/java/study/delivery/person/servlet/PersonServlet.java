package study.delivery.person.servlet;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import study.delivery.person.facade.PersonFacade;
import study.delivery.person.facade.dto.PersonDTO;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@HttpConstraint(rolesAllowed = {
        "ADMINISTRATOR",
        "LIST_PERSON"
})
@WebServlet(value = "/servlet/persons", initParams = {@WebInitParam(name = "closed", value = "false")})
public class PersonServlet extends HttpServlet {

    private Boolean closed;

    @Inject
    private PersonFacade personFacade;
    @Inject
    private Gson gson;

    @Override
    public void init() throws ServletException {
        ServletConfig config = getServletConfig();
        this.closed = Boolean.valueOf(config.getInitParameter("closed"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(Boolean.TRUE);

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
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("");
//        if(requestDispatcher != null) {
//            requestDispatcher.include(req, resp);
//            requestDispatcher.forward(req, resp);
//        }

        String jsonString = gson.toJson(persons);
        resp.setContentType("application/json");
        resp.setContentLength(jsonString.getBytes().length);
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print(jsonString);
    }

}
