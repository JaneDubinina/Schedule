package studentModule;
import groupModule.Group;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class StudentReaderSQL {
    Connection conn = null;
    Statement statement;
    Map<Integer,Student> groups = new HashMap<>();
    final static private String sql = "select * from groups_";
    public  StudentReaderSQL(DataSource ds){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                            "user=root&password=jun20jun&serverTimezone=UTC");            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            System.out.println("=(");
        }

    }
    public void add(Student student)
    {
        groups.put(new Random().nextInt(5),student);
    }
    public Map<Integer,Student> readGropswithMask(String mask)
    {

        ResultSet res = null;
        try {
            res = statement.executeQuery(String.format("select * from students where first_name like \"%s\";",mask));

            while (res.next()) {

                System.out.println(String.format(
                        "id=%s firstname=%s",
                        res.getString("first_name"),
                        res.getString("second_name")));
                Student l = new Student(res.getString("first_name"), res.getString("second_name"));
                groups.put(Integer.parseInt( res.getString("id")),l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return groups;
    }
    public Map<Integer,Student> readGrops()
    {

        ResultSet res = null;
        try {
            res = statement.executeQuery("select * from students");

            while (res.next()) {

                System.out.println(String.format(
                        "id=%s firstname=%s",
                        res.getString("first_name"),
                        res.getString("second_name")));
                Student l = new Student(res.getString("first_name"), res.getString("second_name"));
                groups.put(Integer.parseInt( res.getString("id")),l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return groups;
    }

    public List<Student>getGeroups()
    {
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        web.servlet.TestSQL testSQL = new web.servlet.TestSQL();
        testSQL.readGrops();
    }

}

