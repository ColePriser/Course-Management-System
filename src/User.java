import javax.swing.*;
import java.net.*;
import java.io.*;

public class User implements Runnable {
    public static Socket socket; //Socket object
    public static BufferedReader bfr; //BufferedReader object
    public static PrintWriter writer; //PrintWriter object

    private MainMenu mainMenuFrame = new MainMenu();
    private CreateAccountMenu createActFrame = new CreateAccountMenu();
    private LogInMenu logInFrame = new LogInMenu();
    private TeacherMenu teacherFrame = new TeacherMenu();
    private StudentMenu studentFrame = new StudentMenu();
    private TeacherSettingsMenu teacherSettingsFrame = new TeacherSettingsMenu();
    private StudentSettingsMenu studentSettingsFrame = new StudentSettingsMenu();
    private CreateNewCourseMenu createCourseFrame = new CreateNewCourseMenu();
    private EnrollCourse enrollCourseFrame = new EnrollCourse();
    private teacherViewCourses teacherViewCoursesFrame = new teacherViewCourses();
    private TeacherEditCourse teacherEditCourseFrame = new TeacherEditCourse();
    private StudentViewCourses studentViewCoursesFrame = new StudentViewCourses();
    private StudentOpenCourse studentOpenCourseFrame = new StudentOpenCourse();
    private TeacherViewEnrolledStudents teacherViewEnrolledStudentsFrame = new TeacherViewEnrolledStudents();

    public static void main(String[] args) {
        Thread user = new Thread(new User());
        user.start();
        try {
            /**
             * Creating socket and setting up reading and writing capabilities
             * to communicate with the server.
             */
            socket = new Socket(InetAddress.getLocalHost(), 2361);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String messageFromServer;
                try {
                    messageFromServer = bfr.readLine();
                } catch (Exception e) {
                    continue;
                }
                switch (messageFromServer) {
                    case "Teacher ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a teacher with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        createActFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Student ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a student with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        createActFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Teacher Account Created Success" -> {
                        String userName = bfr.readLine();
                        String userEmail = bfr.readLine();
                        String userPassword = bfr.readLine();
                        int userID = Integer.parseInt(bfr.readLine());
                        setUserInfo(userID, userName, userEmail, userPassword);
                        JOptionPane.showMessageDialog(null,
                                "Your account has been made successfully!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE);
                        createActFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Student Account Created Success" -> {
                        String userName = bfr.readLine();
                        String userEmail = bfr.readLine();
                        String userPassword = bfr.readLine();
                        int userID = Integer.parseInt(bfr.readLine());
                        setUserInfo(userID, userName, userEmail, userPassword);
                        JOptionPane.showMessageDialog(null,
                                "Your account has been made successfully!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE);
                        createActFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Log In Both Checked", "Log In Neither Checked" -> {
                        JOptionPane.showMessageDialog(null,
                                "Choose either Student or Teacher!",
                                "Log In", JOptionPane.ERROR_MESSAGE);
                    }
                    case "Log In Teacher Success" -> {
                        String userName = bfr.readLine();
                        String userEmail = bfr.readLine();
                        String userPassword = bfr.readLine();
                        int userID = Integer.parseInt(bfr.readLine());
                        setUserInfo(userID, userName, userEmail, userPassword);
                        JOptionPane.showMessageDialog(null,
                                "You have logged in successfully!",
                                "Log In", JOptionPane.INFORMATION_MESSAGE);
                        logInFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Log In Student Success" -> {
                        String userName = bfr.readLine();
                        String userEmail = bfr.readLine();
                        String userPassword = bfr.readLine();
                        int userID = Integer.parseInt(bfr.readLine());
                        setUserInfo(userID, userName, userEmail, userPassword);
                        JOptionPane.showMessageDialog(null,
                                "You have logged in successfully!",
                                "Log In", JOptionPane.INFORMATION_MESSAGE);
                        logInFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Log In Failure" -> {
                        JOptionPane.showMessageDialog(null,
                                "No account exists with the given ID and password. Try again!",
                                "Log In", JOptionPane.ERROR_MESSAGE);
                    }
                    case "Course Created Success" -> {
                        String name = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        teacherViewCoursesFrame.addTeacherCourse(name, courseID, password);
                        JOptionPane.showMessageDialog(null,
                                "You have created a course successfully!",
                                "Create Course", JOptionPane.INFORMATION_MESSAGE);
                        createCourseFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Course ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create course! There already exists a course with this ID!",
                                "Create Course", JOptionPane.ERROR_MESSAGE);
                    }
                    case "User Info Edit" -> {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        setUserInfo(ID, name, email, password);
                    }
                    case "Already Enrolled In Course" -> {
                        JOptionPane.showMessageDialog(null,
                                "You are already enrolled in this course!",
                                "Enroll In Course", JOptionPane.ERROR_MESSAGE);
                        enrollCourseFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Enroll In Course Success" -> {
                        String courseName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String coursePassword = bfr.readLine();
                        String teacherName = bfr.readLine();
                        int teacherID = Integer.parseInt(bfr.readLine());
                        String teacherEmail = bfr.readLine();
                        String teacherPassword = bfr.readLine();
                        String studentName = bfr.readLine();
                        int studentID = Integer.parseInt(bfr.readLine());
                        String studentEmail = bfr.readLine();
                        String studentPassword = bfr.readLine();
                        studentViewCoursesFrame.addStudentToCourse(courseID);
                        studentViewCoursesFrame.addStudentCourse(courseName, courseID, coursePassword, teacherName, teacherID, teacherEmail, teacherPassword);
                        teacherViewEnrolledStudentsFrame.addStudent(studentName, studentID, studentEmail, studentPassword);
                        JOptionPane.showMessageDialog(null,
                                "You have successfully enrolled in this course!",
                                "Enroll In Course", JOptionPane.INFORMATION_MESSAGE);
                        enrollCourseFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Enroll In Course Error" -> {
                        JOptionPane.showMessageDialog(null,
                                "No course exists with the given ID and password. Try again!",
                                "Enroll In Course", JOptionPane.ERROR_MESSAGE);
                    }
                    case "Create New Course Menu Back" -> {
                        createCourseFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Create Account Menu Back" -> {
                        createActFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Enroll Course Menu Back" -> {
                        enrollCourseFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Log In Menu Back" -> {
                        logInFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Student Menu Back" -> {
                        studentFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Student Settings Menu Back" -> {
                        studentSettingsFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Teacher Menu Back" -> {
                        teacherFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Teacher Settings Menu Back" -> {
                        teacherSettingsFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Teacher View Courses Menu Back" -> {
                        teacherViewCoursesFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Main Menu Log In" -> {
                        mainMenuFrame.setVisible(false);
                        logInFrame.setVisible(true);
                    }
                    case "Delete Teacher Account User" -> {
                        teacherSettingsFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Teacher Settings User" -> {
                        teacherFrame.setVisible(false);
                        teacherSettingsFrame.setVisible(true);
                        teacherSettingsFrame.resetTeacherSettingLabels();
                    }
                    case "Teacher Create New Course Button User" -> {
                        teacherFrame.setVisible(false);
                        createCourseFrame.setVisible(true);
                    }
                    case "Teacher View Courses Button User"  -> {
                        teacherFrame.setVisible(false);
                        teacherViewCoursesFrame.setVisible(true);
                        teacherViewCoursesFrame.resetCourseList();
                    }
                    case "Delete Student Account User" -> {
                        studentSettingsFrame.setVisible(false);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Main Menu Create New Account User" -> {
                        mainMenuFrame.setVisible(false);
                        createActFrame.setVisible(true);
                    }
                    case "Student Settings User" -> {
                        studentFrame.setVisible(false);
                        studentSettingsFrame.setVisible(true);
                    }
                    case "Student Menu Enroll In Course User" -> {
                        studentFrame.setVisible(false);
                        enrollCourseFrame.setVisible(true);
                    }
                    case "Teacher Edit Courses Menu Back" -> {
                        teacherEditCourseFrame.setVisible(false);
                        teacherViewCoursesFrame.setVisible(true);
                    }
                    case "Teacher Edit Course Button User" -> {
                        String courseName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String teacherName = bfr.readLine();
                        String coursePassword = bfr.readLine();
                        teacherViewCoursesFrame.setVisible(false);
                        teacherEditCourseFrame.setCourseInfo(courseName, courseID, teacherName, coursePassword);
                        teacherEditCourseFrame.resetEditCourseLabels();
                        teacherEditCourseFrame.setVisible(true);
                    }
                    case "Edit Course Name or Password User" -> {
                        String courseName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String teacherName = bfr.readLine();
                        String coursePassword = bfr.readLine();
                        teacherEditCourseFrame.setCourseInfo(courseName, courseID, teacherName, coursePassword);
                        teacherEditCourseFrame.resetEditCourseLabels();
                    }
                    case "Delete Course User" -> {
                        int courseID = Integer.parseInt(bfr.readLine());
                        studentViewCoursesFrame.removeStudentCourse(courseID);
                        teacherViewCoursesFrame.removeTeacherCourse(courseID);
                        teacherEditCourseFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "Student View Enrolled Courses User" -> {
                        int studentID = Integer.parseInt(bfr.readLine());
                        studentViewCoursesFrame.resetCourseList();
                        studentFrame.setVisible(false);
                        studentViewCoursesFrame.setVisible(true);
                    }
                    case "Student View Enrolled Courses Back User" -> {
                        studentViewCoursesFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Student Open Course Back User" -> {
                        studentOpenCourseFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Student Open Course User" -> {
                        String courseName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String teacherName = bfr.readLine();
                        String coursePassword = bfr.readLine();
                        studentViewCoursesFrame.setVisible(false);
                        studentOpenCourseFrame.setCourseInfo(courseName, courseID, teacherName, coursePassword);
                        studentOpenCourseFrame.resetEditCourseLabels();
                        studentOpenCourseFrame.setVisible(true);
                    }
                    case "Unenroll In Course User" -> {
                        int courseID = Integer.parseInt(bfr.readLine());
                        int userID = Integer.parseInt(bfr.readLine());
                        studentViewCoursesFrame.removeStudentCourse(courseID);
                        teacherViewEnrolledStudentsFrame.removeStudent(userID);
                        studentOpenCourseFrame.setVisible(false);
                        studentFrame.setVisible(true);
                    }
                    case "Teacher View Enrolled Students User" -> {
                        teacherEditCourseFrame.setVisible(false);
                        teacherViewEnrolledStudentsFrame.setVisible(true);
                        teacherViewEnrolledStudentsFrame.resetStudentList();
                    }
                    case "Teacher View Enrolled Students Back User" -> {
                        teacherViewEnrolledStudentsFrame.setVisible(false);
                        teacherEditCourseFrame.setVisible(true);
                    }

                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setUserInfo(int userID, String userName, String userEmail, String userPassword) {
        teacherFrame.setUserID(userID);
        studentFrame.setUserID(userID);
        teacherSettingsFrame.setUserID(userID);
        studentSettingsFrame.setUserID(userID);
        createCourseFrame.setUserID(userID);
        enrollCourseFrame.setUserID(userID);
        teacherViewCoursesFrame.setUserID(userID);
        teacherEditCourseFrame.setUserID(userID);
        studentViewCoursesFrame.setUserID(userID);
        studentOpenCourseFrame.setUserID(userID);
        teacherViewEnrolledStudentsFrame.setUserID(userID);
        teacherFrame.setUserName(userName);
        studentFrame.setUserName(userName);
        teacherSettingsFrame.setUserName(userName);
        studentSettingsFrame.setUserName(userName);
        createCourseFrame.setUserName(userName);
        enrollCourseFrame.setUserName(userName);
        teacherViewCoursesFrame.setUserName(userName);
        teacherEditCourseFrame.setUserName(userName);
        studentViewCoursesFrame.setUserName(userName);
        studentOpenCourseFrame.setUserName(userName);
        teacherViewEnrolledStudentsFrame.setUserName(userName);
        teacherFrame.setUserEmail(userEmail);
        studentFrame.setUserEmail(userEmail);
        teacherSettingsFrame.setUserEmail(userEmail);
        studentSettingsFrame.setUserEmail(userEmail);
        createCourseFrame.setUserEmail(userEmail);
        enrollCourseFrame.setUserEmail(userEmail);
        teacherViewCoursesFrame.setUserEmail(userEmail);
        teacherEditCourseFrame.setUserEmail(userEmail);
        studentViewCoursesFrame.setUserEmail(userEmail);
        studentOpenCourseFrame.setUserEmail(userEmail);
        teacherViewEnrolledStudentsFrame.setUserEmail(userEmail);
        teacherFrame.setUserPassword(userPassword);
        studentFrame.setUserPassword(userPassword);
        teacherSettingsFrame.setUserPassword(userPassword);
        studentSettingsFrame.setUserPassword(userPassword);
        createCourseFrame.setUserPassword(userPassword);
        enrollCourseFrame.setUserPassword(userPassword);
        teacherViewCoursesFrame.setUserPassword(userPassword);
        teacherEditCourseFrame.setUserPassword(userPassword);
        studentViewCoursesFrame.setUserPassword(userPassword);
        studentOpenCourseFrame.setUserPassword(userPassword);
        teacherViewEnrolledStudentsFrame.setUserPassword(userPassword);
        teacherSettingsFrame.resetTeacherSettingLabels();
        studentSettingsFrame.resetStudentSettingLabels();
    }
}

