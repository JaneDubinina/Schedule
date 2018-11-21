package web.servlet;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import groupModule.GroupSQL;
import professorModule.Professor;
import professorModule.ProfessorRepository;
import studentModule.*;

public class TestServlet extends HttpServlet {
    private Connection conn;
    private static final long serialVersionUID = 6345194112526801506L;
    private ProfessorRepository professorRepository;
    private final static List<Student> STUDENTS = new ArrayList<Student>();
    private final static List<Professor> PROFESSORS = new ArrayList<Professor>();
    private GroupSQL testsql;
    private List<Student> students = new ArrayList<>();
    private StudentReaderSQL studentsSQL;
    public TestServlet() throws SQLException {
    }

    @Override
    public void init() throws ServletException
    {
        ServletContext sc = getServletContext();
        this.studentsSQL = (StudentReaderSQL)sc.getAttribute("StudentSQL");
        this.professorRepository = (ProfessorRepository) sc.getAttribute("professorRepository");
        this.testsql = (GroupSQL)sc.getAttribute("groupRepository");
        this.students = studentsSQL.getGeroups();
    }

    public static void main(String[] args) {

    }
    @Override
    public void destroy()
    {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("studentCount", STUDENTS.size());
        req.setAttribute("students", studentsSQL.getGeroups());
        req.setAttribute("professorsCount", PROFESSORS.size());
        req.setAttribute("professors", professorRepository.findAll());
        req.setAttribute("groups",testsql.getGeroups());


        /*if("".equals(req.getParameter("json"))) {
            resp.setContentType("z/json");

            PrintWriter pw = resp.getWriter();
            pw.print(toJson(students));
            pw.close();
        } else {*/
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
            dispatcher.forward(req, resp);




    }
    private static String toJson(Student student){
        String json = "{" +
                "\"id\": \"" + student.getId() + "\", " +
                "\"firstName\": \"" + student.getFirstName()+ "\", " +
                "\"lastName\": \"" + student.getSecondName()+ "\" " +
                "}";
        return json;
    }

    private static String toJson(List<Student> students){
        String json = "[" ;
        if(students != null) {
            boolean firstItem = true;
            for (Student student: students){
                if (firstItem){
                    firstItem = false;
                } else{
                    json += ",";
                }
                json += toJson(student);
            }
        }
        json += "]";
        return json;
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String Name = req.getParameter("Name");
        String MaskForStudents = req.getParameter("maskForStudents");
        String Subject = req.getParameter("Subject");
        String NameToDelete = req.getParameter("NameToDelete");
        String SubjectToDelete = req.getParameter("SubjectToDelete");
        deleteStudent(NameToDelete,SubjectToDelete);

        if(firstName.length()>1&&secondName.length()>1)
            studentsSQL.add(new Student(firstName, secondName));
        if(MaskForStudents.length()>1)
            studentsSQL.readGropswithMask(MaskForStudents);
        PROFESSORS.add(new Professor(Name,Subject));


        doGet(req,resp);
    }
    protected void deleteStudent(String name, String sec)
    {
        int i =-1;
        for(Student o:STUDENTS)
        {
            if(o.getFirstName().equals(name)&&o.getSecondName().equals(sec))
                i = STUDENTS.indexOf(o);

        }
        if(i>-1)
        {
            STUDENTS.remove(i);
        }
    }

}

