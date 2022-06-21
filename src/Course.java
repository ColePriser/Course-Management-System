public class Course {
    private String courseName;
    private int courseID;
    private Teacher teacher;

    public Course(String courseName, int courseID, Teacher teacher) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.teacher = teacher;
    }

    public int getCourseID() {
        return courseID;
    }
}
