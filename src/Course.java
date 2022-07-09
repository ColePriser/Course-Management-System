import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseID;
    private String coursePassword;
    private Teacher teacher;
    public ArrayList<Student> enrolledStudents;

    public Course(String courseName, int courseID, String coursePassword, Teacher teacher) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.coursePassword = coursePassword;
        this.teacher = teacher;
        this.enrolledStudents = new ArrayList<>();
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

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCoursePassword(String coursePassword) {
        this.coursePassword = coursePassword;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }
}
