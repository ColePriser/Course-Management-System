import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<TeacherCourse> courses;
    public Student(int ID, String name, String email, String password) {
        super(ID, name, email, password);
        this.courses = null;
    }

}
