package web.servlet;
import groupModule.GroupSQL;
import professorModule.Professor;
import professorModule.ProfessorReader;
import professorModule.ProfessorRepository;
import studentModule.*;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextListener;


public class ApplicationContextListener implements ServletContextListener {
    private final String ProfessorsPath="C:\\Schedule\\professors.txt";
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
            DataSource ds = dataSource();
            StudentReaderSQL student = new StudentReaderSQL(ds);
            student.readGrops("");
            ServletContext sc = sce.getServletContext();
            sc.setAttribute("StudentSQL",student);
            List<Professor> professors = new ProfessorReader(ProfessorsPath).read();
            ProfessorRepository professorRepository = new ProfessorRepository(professors);
            sc.setAttribute("professorRepository",professorRepository);
            GroupSQL groups = new GroupSQL(ds);
            groups.readGrops();
            StudentWriterSQL studentWriterSQL= new StudentWriterSQL(ds);
            sc.setAttribute("StudentWriterSQL", studentWriterSQL);
            sc.setAttribute("groupRepository", groups);
        } catch (IOException e) {
            throw new RuntimeException("Can't read students");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {

    }
}
