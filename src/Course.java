import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseID;
    private String coursePassword;
    private Teacher teacher;
    public static ArrayList<Student> enrolledStudents = new ArrayList<>();

    public Course(String courseName, int courseID, String coursePassword, Teacher teacher) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.coursePassword = coursePassword;
        this.teacher = teacher;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public String getCoursePassword() {
        return coursePassword;
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
