package studentModule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class StudentWriter {
    private final String path;
    private Connection conn;
    private Statement statement;
    public StudentWriter(String path)
    {
        this.path = path;
    }
    public void write(List<Student> students) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                "user=root&password=jun20jun&serverTimezone=UTC");
            statement = conn.createStatement();
            if(students!=null)
            {
                for(Student st:students){
                    String line = String.format("insert into students(id,first_name,second_name) values(\"%s\",\"%s\",\"%s\")",st.getId(),st.getFirstName(),st.getSecondName());
                    statement.executeQuery(line);
                    //String line = String.format("%s;%s;%s",st.getId(),st.getFirstName(),st.getSecondName());
                    bw.write(line);
                    bw.newLine();
            }
            }
        }
        catch (ClassNotFoundException ex)
        {

        }
        catch (SQLException ex)
        {
            System.out.println("SQL mistake");
        }
    }
}
