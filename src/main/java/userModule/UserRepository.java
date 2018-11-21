package userModule;

import javax.sql.DataSource;
import java.sql.*;

public class UserRepository {
    private DataSource dataSource;


    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public User LogIN(String name, String pass) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                    "user=root&password=jun20jun&serverTimezone=UTC");
            String sql="SELECT * FROM  world.users";
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




