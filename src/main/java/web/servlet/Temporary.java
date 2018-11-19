package web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Temporary implements ServletContextListener {
    private  Connection conn = null;
    private  Statement statement;

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {

        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                            "user=root&password=jun20jun&serverTimezone=UTC");
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {

    }
}
