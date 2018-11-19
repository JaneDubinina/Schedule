package studentModule;

import groupModule.Group;

import java.util.ArrayList;

public class Student {

    private String id;
    private String firstName;
    private String secondName;
    private ArrayList<Group>groups;

    public Student(){
        super();
    }

    public Student(String firstName, String secondName) {
        super();

        this.firstName = firstName;
        this.secondName = secondName;
        groups = new ArrayList<>();
    }

    public void setId(String id){this.id = id;}
    public String getId(){return this.id;}
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getSecondName() {
        return secondName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
