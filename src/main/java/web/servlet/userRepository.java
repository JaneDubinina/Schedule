package web.servlet;

import studentModule.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class userRepository {
    DataSource dataSource;
    List<User> users = new ArrayList<>();
    public userRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public User LogIN(String name, String pass) {
        try {
            Connection conn = dataSource.getConnection();
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
            conn.close();
            st.close();
            return null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public List<User> getUsers()
    {
        try (Connection connection = dataSource.getConnection()){
            ResultSet res = null;
            Statement statement =connection.createStatement();
            String sql = "select * from world.users_";
            res = statement.executeQuery(sql);

            while (res.next()) {
                User user= new User(res.getString("login"), res.getString("password"));
                this.users.add(user);
                statement.close();
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return this.users;
    }

}




