import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Student extends Person {
    private ArrayList<TeacherCourse> courses;
    public Student(String name, int ID, String email, String password) {
        super(name, ID, email, password);
        this.courses = null;
    }

    public void createTextFile() {
        try {
            File text = new File(super.getTextFile());
            text.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
