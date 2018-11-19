package professorModule;

import groupModule.Group;

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String Name;
    private String Subject;
    private String id;
    private List<Group> groups;

    public Professor(){super();}

    public Professor(String firstName, String secondName) {
        super();
        this.Name = firstName;
        this.Subject = secondName;
        groups = new ArrayList<>();
    }
    public String getID(){return this.id;}
    public  void setId(String id){this.id = id;}
    public String getName() {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public String getSubject() { return Subject; }
    public void setSubject(String secondName) {
        this.Subject = secondName;
    }
}
