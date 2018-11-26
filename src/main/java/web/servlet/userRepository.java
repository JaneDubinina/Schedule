package web.servlet;

import java.sql.*;

public class userRepository {
    String name;
    String pass;
    public userRepository(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public User LogIN(String name, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                    "user=root&password=jun20jun&serverTimezone=UTC");
            String sql="SELECT * FROM  world.users_";
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(sql);
            while(rs.next())
            {
                if(rs.getString("login").equals(name)&&rs.getString("password").equals(pass))
                {

                    User user = new User(name,pass);
                    return user;
                }
            }
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

}




