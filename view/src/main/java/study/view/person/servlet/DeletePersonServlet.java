package study.view.person.servlet;

import lombok.extern.slf4j.Slf4j;
import study.facade.exception.FacadeBusinessException;
import study.facade.person.PersonFacade;
import study.view.exception.PageNotFoundException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMINISTRATOR", "DELETE_PERSON"}))
@WebServlet(value = "/secure/person/delete")
public class DeletePersonServlet extends HttpServlet {

    @EJB
    private PersonFacade personFacade;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) throw new PageNotFoundException();

        try {
            personFacade.delete(Long.valueOf(id));
            resp.sendRedirect(req.getHeader("Referer"));
        } catch (FacadeBusinessException e) {
            if(e.getBusinessExceptionClassName().contains("PersonNotFoundException")) {
                throw new PageNotFoundException();
            }
        }
    }

}
