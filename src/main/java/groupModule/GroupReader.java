package groupModule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupReader {
    private final String path;
    public GroupReader(String path)
    {
        this.path = path;
    }
    public List<Group> read() throws IOException {
        List<Group>Groups = new ArrayList<Group>();
        try(BufferedReader bw = new BufferedReader(new FileReader(path)))
        {
            Scanner fin = new Scanner(bw);
            String line;

            while(fin.hasNextLine())
            {
                line = fin.nextLine();
                String[]data = line.split(";");
                Groups.add(new Group(data[0],data[1]));

            }
        }
        return Groups;

    }
}
