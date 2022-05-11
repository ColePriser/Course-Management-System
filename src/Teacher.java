import java.util.ArrayList;

public class Teacher extends Person {
    private ArrayList<TeacherCourse> courses;
    public Teacher(int ID, String name, String email, String password) {
        super(ID, name, email, password);
        this.courses = null;
    }
}
