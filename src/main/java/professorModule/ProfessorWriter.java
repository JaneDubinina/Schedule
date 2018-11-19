package professorModule;

import studentModule.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProfessorWriter {
    private final String path;
    public ProfessorWriter(String path)
    {
        this.path = path;
    }
    public void write(List<Professor> professors) throws IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path)))
        {
            if(professors!=null)
            {
                for(Professor st:professors){
                    String line = String.format("%s;%s;%s",st.getID(),st.getName(),st.getSubject());
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
    }
}

