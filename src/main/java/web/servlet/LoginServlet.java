package web.servlet;

import userModule.User;
import userModule.UserRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 6345194112526801506L;
    private DataSource dataSource() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/world");
            return ds;
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String name = req.getParameter("firstName");
        String password = req.getParameter("secondName");
        UserRepository userRepository = new UserRepository(dataSource());

//        User user = userRepository.LogIN(name, password);
//        if (user!=null) {
//            HttpSession session = req.getSession();
//            session.setAttribute("user", new User(name, password));
//            response.sendRedirect("/test");
//        } else {
//            doGet(req, response);
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
        dispatcher.forward(req, resp);
    }
}