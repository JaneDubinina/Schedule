package web.servlet;
import professorModule.Professor;
import professorModule.ProfessorRepository;
import studentModule.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
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
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Professors.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String Name = req.getParameter("Name");
        String Subject = req.getParameter("Subject");
        professorRepository.update(new Professor(Name,Subject));
        if("".equals(req.getParameter("json"))) {
            resp.setContentType("application/json");

            PrintWriter pw = resp.getWriter();
            pw.print(toJson(professorRepository.findAll()));
            pw.close();

        } else {
            resp.sendRedirect(req.getContextPath() + "/professors");
        }

    }
    private static String toJson(Professor student){
        String json = "{" +
                "\"firstName\": \"" + student.getName()+ "\", " +
                "\"lastName\": \"" + student.getSubject()+ "\" " +
                "}";
        return json;
    }

    private static String toJson(List<Professor> students){
        String json = "[" ;
        if(students != null) {
            boolean firstItem = true;
            for (Professor student: students){
                if (firstItem){
                    firstItem = false;
                } else{
                    json += ",";
                }
                json += toJson(student);
            }
        }
        json += "]";
        return json;
    }

}
