package web.servlet;

import groupModule.Group;
import groupModule.GroupSQL;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    public void init() {
        ServletContext sc = getServletContext();
        groupSQL = (GroupSQL) sc.getAttribute("groupRepository");
    }
    private GroupSQL groupSQL = null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException

    {
        req.setAttribute("groups",groupSQL.getGroups());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/groups.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String Name = req.getParameter("Group");
        String Subject = req.getParameter("NameOfGroup");
        groupSQL.AddGroup(Name, Subject);
        //resp.sendRedirect(req.getContextPath() + "/groups");
        if("".equals(req.getParameter("json"))) {
            resp.setContentType("application/json");

            PrintWriter pw = resp.getWriter();
            pw.print(toJson(groupSQL.getGroups()));
            pw.close();

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/groups.jsp");
            dispatcher.forward(req, resp);
        }
    }
    private static String toJson(Group student){
        String json = "{" +
                "\"firstName\": \"" + student.getGroupNumber()+ "\", " +
                "\"lastName\": \"" + student.getAvhMark()+ "\" " +
                "}";
        return json;
    }

    private static String toJson(List<Group> students){
        String json = "[" ;
        if(students != null) {
            boolean firstItem = true;
            for (Group student: students){
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

