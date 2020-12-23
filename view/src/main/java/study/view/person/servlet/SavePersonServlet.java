package study.view.person.servlet;

import lombok.extern.slf4j.Slf4j;
import study.facade.person.PersonFacade;
import study.facade.person.dto.PersonDTO;
import study.facade.person.dto.SavePersonCommandDTO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMINISTRATOR", "DELETE_PERSON"}))
@WebServlet(value = "/secure/person/save")
public class SavePersonServlet extends HttpServlet {

    private static final String JSP_PAGE = "/secure/person/save.jsp";

    @EJB
    private PersonFacade personFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JSP_PAGE)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SavePersonCommandDTO command = new SavePersonCommandDTO();
        command.setName(req.getParameter("name"));
        command.setSurname(req.getParameter("surname"));
        command.setEmail(req.getParameter("email"));

        String birthDate = req.getParameter("birthDate");
        if(birthDate != null) {
            try {
                Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                command.setBirthDate(parse);
            } catch (ParseException ignore) {
            }
        }
        PersonDTO person  = personFacade.save(command);
        req.setAttribute("person", person);
        req.getRequestDispatcher(JSP_PAGE)
                .forward(req, resp);
    }
}
