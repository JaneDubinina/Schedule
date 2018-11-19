package studentModule;

import java.util.*;

public class StudentRepository {
    private final Map<String,Student> students = new HashMap<>();
    public StudentRepository(List<Student>students)
    {
        if(students !=null)
        {
            for(Student st:students)
            {
                this.students.put(st.getId(),st);
            }
        }
    }
    public List<Student> findAll()
    {
        //return Collections.unmodifiableList(students);//защита от дураков
        return new ArrayList<>(students.values());
    }
    public Student create(Student student)
    {
        String id =UUID.randomUUID().toString();
        student.setId(id);
        students.put(id,student);
        return student;
    }
    public void remove(String id){
                students.remove(id);
    }
    public Student update(Student student)
    {
        students.put(student.getId(),student);
        return student;
    }
}
