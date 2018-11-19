package studentModule;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EntityFileReader<T> implements EntityReader<T> {
    private final String path;
    private final LineMapper<T> mapper;
    public EntityFileReader(String path,LineMapper<T> mapper)
    {
        this.path = path;
        this.mapper = mapper;
    }
    @Override
    public List<T> read(){
        List<T> items = new ArrayList<T>();
        try(BufferedReader bw = new BufferedReader(new FileReader(path)))
        {
            Scanner fin = new Scanner(bw);
            String line;

            while(fin.hasNextLine())
            {
                line = fin.nextLine();
                T item = mapper.mapLine(line);
                items.add(item);

            }
        } catch (FileNotFoundException e) { throw new RuntimeException(e);
        } catch (IOException e) {

        }
        return items;

    }
}