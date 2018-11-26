package studentModule;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentWriterSQL {
    DataSource dataSource = null;
    public StudentWriterSQL(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    public void write(Student student)
    {
        try {
            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
            String sql = String.format("insert into students(first_name, second_name) values('%s', '%s')",student.getFirstName(), student.getSecondName() );
            statement.execute(sql);
            conn.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("SQL error in writing students");
        }

    }
}
