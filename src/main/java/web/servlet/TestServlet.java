package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
    private static final long serialVersionUID = 6345194112526801506L;
    private ProfessorRepository professorRepository;
    private final static List<Student> STUDENTS = new ArrayList<Student>();
    private GroupSQL testsql;
    private StudentWriterSQL studentWriterSQL;
    private StudentReaderSQL studentsSQL;
    public TestServlet() throws SQLException {
    }

    @Override
    public void init() throws ServletException
    {
        ServletContext sc = getServletContext();
        this.studentsSQL = (StudentReaderSQL)sc.getAttribute("StudentSQL");
        this.studentWriterSQL = (StudentWriterSQL)sc.getAttribute("StudentWriterSQL");
    }

    public static void main(String[] args) {

    }
    @Override
    public void destroy()
    {

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentsSQL.getGeroups());
        if("".equals(req.getParameter("json"))) {
            resp.setContentType("application/json");

            PrintWriter pw = resp.getWriter();
            pw.print(toJson(studentsSQL.getGeroups()));
            pw.close();

        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/test.jsp");
            dispatcher.forward(req, resp);
        }


    }
    private static String toJson(Student student){
        String json = "{" +
                "\"id\": \"" + student.getId() + "\"," +
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
        String MaskForStudents = req.getParameter("MaskForStudents");
        if(firstName!=null&&secondName!=null)
            studentsSQL.add(new Student(firstName, secondName));
        if(firstName!=null&&(!firstName.equals("")))
            studentWriterSQL.write(new Student(firstName, secondName));

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

