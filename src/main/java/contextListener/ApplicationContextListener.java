package contextListener;

import userModule.UserRepository;
import professorModule.Professor;
import professorModule.ProfessorReader;
import professorModule.ProfessorRepository;
import studentModule.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

public class ApplicationContextListener implements ServletContextListener {
    private final String path ="C:\\Schedule\\students.txt";
    private final String ProfessorsPath="C:\\Schedule\\professors.txt";
    private StudentRepository studentRepository;
    String filepath;
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
    public void contextInitialized(ServletContextEvent sce)
    {

        try {
            UserRepository userRepository = new UserRepository(dataSource());
            TestSQL testsql = new TestSQL();
            DataSource ds = dataSource();
            testsql.readGrops();
            List<Student> students = new EntityFileReader<Student>(path,new StudentLineMapper<>()).read();
            StudentReaderSQL student = new StudentReaderSQL(ds);//
            student.readGrops();
            StudentRepository studentRepository = new StudentRepository(students);
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("studentRepository",studentRepository);
            List<Professor> professors = new ProfessorReader(ProfessorsPath).read();
            ProfessorRepository professorRepository = new ProfessorRepository(professors);
            ServletContext sc1 = sce.getServletContext();
            sc.setAttribute("professorRepository",professorRepository);
        } catch (IOException e) {
            throw new RuntimeException("Can't read students");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        List<Student> students = studentRepository.findAll();
        try {
            new StudentWriter(filepath).write(students);
        } catch (IOException e) {
            System.out.println("Can't save students");
        }
    }
}
