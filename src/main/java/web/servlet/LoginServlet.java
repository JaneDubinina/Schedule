package web.servlet;

import UserModule.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String name = req.getParameter("firstName");
        String password = req.getParameter("secondName");
        if ("admin".equals(name)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", new User(name, password));
            response.sendRedirect("/test");
        } else {
            doGet(req, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
        dispatcher.forward(req, resp);
    }
}