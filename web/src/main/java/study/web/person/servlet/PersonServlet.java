//package study.web.person.web;
//
//import study.web.person.facade.PersonFacade;
//import study.web.person.facade.dto.PersonDTO;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.SessionCookieConfig;
//import javax.servlet.annotation.WebInitParam;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//
//@WebServlet(value = "/persons", initParams = {@WebInitParam(name = "closed", value = "false")})
//public class PersonServlet extends HttpServlet {
//
//    private Boolean closed;
//
//    private PersonFacade personFacade;
//
//    @Override
//    public void init() throws ServletException {
//        ServletConfig config = getServletConfig();
//        this.closed = Boolean.valueOf(config.getInitParameter("closed"));
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
//        sessionCookieConfig.setHttpOnly(Boolean.TRUE);
//
//
//        HttpSession session = req.getSession(Boolean.TRUE);
//        String operatorUsername = (String) session.getAttribute("operator.username");
//
//
//        if(closed) {
//            //TODO Return message
//        }
//
//        String term = req.getParameter("term");
//        List<PersonDTO> persons =  personFacade.list(term);
//        //TODO implement more
//    }
//
//}
