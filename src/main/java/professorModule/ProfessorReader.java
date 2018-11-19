package professorModule;

import studentModule.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorReader {
    private final String path;
    public ProfessorReader(String path)
    {
        this.path = path;
    }
    public List<Professor> read() throws IOException {
        List<Professor>professorss = new ArrayList<Professor>();
        try(BufferedReader bw = new BufferedReader(new FileReader(path)))
        {
            Scanner fin = new Scanner(bw);
            String line;

            while(fin.hasNextLine())
            {
                line = fin.nextLine();
                String[]data = line.split(";");
                professorss.add(new Professor(data[1],data[2]));

            }
        }
        return professorss;

    }
}
