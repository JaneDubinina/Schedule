package studentModule;

import java.io.*;
import java.util.*;

public class StudentReader {
    private final String path;
    public StudentReader(String path)
    {
        this.path = path;
    }
    public List<Student> read() throws IOException{
        List<Student>students = new ArrayList<Student>();
        try(BufferedReader bw = new BufferedReader(new FileReader(path)))
        {
            Scanner fin = new Scanner(bw);
            String line;

            while(fin.hasNextLine())
            {
                line = fin.nextLine();
                String[]data = line.split(";");
                students.add(new Student(data[1],data[2]));

            }
        }
        return students;

    }
}
