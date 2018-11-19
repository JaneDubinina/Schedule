package groupModule;
import studentModule.LineMapper;


public class GroupLineMapper implements LineMapper<Group> {
    @Override
    public Group mapLine(String line)
    {
        String []data = line.split(";");
        Group group = new Group(data[0],data[1]);
        return group;
    }
}
