package professorModule;

import professorModule.Professor;

import javax.sql.DataSource;
import java.util.*;

public class ProfessorRepository {
    private final Map<String,Professor> Professors = new HashMap<>();
    private DataSource dataSource;
    public ProfessorRepository(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    public ProfessorRepository(List<Professor> Professors)
    {
        if(Professors !=null)
        {
            for(Professor st:Professors)
            {
                this.Professors.put(st.getID(),st);
            }
        }
    }
    public List<Professor> findAll()
    {
        //return Collections.unmodifiableList(Professors);//защита от дураков
        return new ArrayList<>(Professors.values());
    }
    public Professor create(Professor Professor)
    {
        String id = UUID.randomUUID().toString();
        Professor.setId(id);
        Professors.put(id,Professor);
        return Professor;
    }
    public void remove(String id){
        Professors.remove(id);
    }
    public Professor update(Professor Professor)
    {
        Professors.put(Professor.getID(),Professor);
        return Professor;
    }
}
