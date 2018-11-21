package studentModule;
import groupModule.GroupSQL;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class StudentReaderSQL {
    Map<Integer,Student> groups = new HashMap<>();
    private DataSource dataSource;
    final static private String sql = "select * from groups_";
    public  StudentReaderSQL(DataSource ds){
            this.dataSource = ds;
    }
    public void add(Student student)
    {
        groups.put(new Random().nextInt(5),student);
    }
    public Map<Integer,Student> readGropswithMask(String mask)
    {

        try (Connection connection = dataSource.getConnection()){
        ResultSet res = null;
        Statement statement =connection.createStatement();

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
            try (Connection connection = dataSource.getConnection()){
                ResultSet res = null;
                Statement statement =connection.createStatement();
            res = statement.executeQuery("select * from world.students");

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
        GroupSQL testSQL = new GroupSQL();
        testSQL.readGrops();
    }

}

