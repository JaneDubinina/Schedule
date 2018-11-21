package web.servlet;

import professorModule.Professor;
import professorModule.ProfessorReader;
import professorModule.ProfessorRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ProfessorServlet extends HttpServlet {

    private static final long serialVersionUID = 6345194112526801506L;
    public void init() {
        ServletContext sc = getServletContext();
        professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
    }
    private ProfessorRepository professorRepository = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("professorRepostitory",professorRepository.findAll());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String Name = req.getParameter("Name");
        String Subject = req.getParameter("Subject");
        professorRepository.update(new Professor(Name,Subject));
        resp.sendRedirect(req.getContextPath() + "/test");

    }
}
