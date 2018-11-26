package web.servlet;

import groupModule.Group;
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

public class AddUserServlet extends HttpServlet{

    private userRepository UR;

    @Override
    public void init() throws ServletException {
        ServletContext sc = getServletContext();
        this.UR = (userRepository)sc.getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", );
        if("".equals(req.getParameter("json"))) {
            resp.setContentType("application/json");

            PrintWriter pw = resp.getWriter();
            pw.print(toJson(studentsSQL.getGeroups()));
            pw.close();

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addUser.jsp");
            dispatcher.forward(req, resp);
        }

    }

    private static String toJson(User user){
        String json = "{" +
                "\"id\": \"" + user.getId() + "\"," +
                "\"firstName\": \"" + user.getName()+ "\", " +
                "\"lastName\": \"" + user.getpassword()+ "\" " +
                "}";
        return json;
    }
    private static String toJson(List<User> users){
        String json = "[" ;
        if(users != null) {
            boolean firstItem = true;
            for (User user: users){
                if (firstItem){
                    firstItem = false;
                } else{
                    json += ",";
                }
                json += toJson(user);
            }
        }
        json += "]";
        return json;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Login = req.getParameter("Login");
        String Password= req.getParameter("Password");

        doGet(req,resp);
    }
}
