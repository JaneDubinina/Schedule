package web.servlet;

import professorModule.Professor;
import professorModule.ProfessorReader;
import professorModule.ProfessorRepository;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.util.List;

public class ProfessorsContextListener {
    public class ApplicationContextListener implements ServletContextListener {
        private final String ProfessorsPath="C:\\Schedule\\professors.txt";

        @Override
        public void contextInitialized(ServletContextEvent sce)
        {

            try {
                List<Professor> professors = new ProfessorReader(ProfessorsPath).read();
                ProfessorRepository professorRepository = new ProfessorRepository(professors);
                ServletContext sc1 = sce.getServletContext();
                sc1.setAttribute("professorRepository",professorRepository);

            } catch (IOException e) {
                throw new RuntimeException("Can't read students");
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce)
        {

        }
    }

}
