package groupModule;





import java.util.*;

public class GroupRepository {
    private final Map<String,Group> Groups = new HashMap<>();
    public GroupRepository(List<Group> Groups)
    {
        if(Groups !=null)
        {
            for(Group st:Groups)
            {
                this.Groups.put(st.getID(),st);
            }
        }
    }
    public List<Group> findAll()
    {
        //return Collections.unmodifiableList(Groups);//защита от дураков
        return new ArrayList<>(Groups.values());
    }
    public Group create(Group Group)
    {
        String id = UUID.randomUUID().toString();
        Group.setID(id);
        Groups.put(id,Group);
        return Group;
    }
    public void remove(String id){
        Groups.remove(id);
    }
    public Group update(Group Group)
    {
        Groups.put(Group.getID(),Group);
        return Group;
    }
}
