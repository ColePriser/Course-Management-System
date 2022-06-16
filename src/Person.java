import java.util.ArrayList;

public class Person {
    private ArrayList<Course> enrolledCourses; //courses person is enrolled in
    private final int ID; //ID of person
    private String name; //name of person
    private String email; //email of person
    private String password; //password of person
    private int numEnrolledCourses; //number of courses a person is enrolled in

    /**
     * Constructor for Person.java
     * @param name
     * @param ID
     * @param email
     * @param password
     */
    public Person(String name, int ID, String email, String password) {
        this.name = name;
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.numEnrolledCourses = 0;
    }

    public void addCourse(Course course) {
        this.enrolledCourses.add(course);
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumEnrolledCourses() {
        return numEnrolledCourses;
    }

    public void incrementNumEnrolledCourses () {
        this.numEnrolledCourses++;
    }

    public void decrementNumEnrolledCourses () {
        this.numEnrolledCourses--;
    }
}
