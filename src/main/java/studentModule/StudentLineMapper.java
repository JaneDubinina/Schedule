package studentModule;

public class StudentLineMapper<T> implements LineMapper<Student> {
    @Override
    public Student mapLine(String line)
    {
        String []data = line.split(";");
        Student student = new Student(data[1],data[2]);
        student.setId(data[0]);
        return student;
    }

}
