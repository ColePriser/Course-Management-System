import javax.swing.*;
import java.net.*;
import java.io.*;

public class User implements Runnable {
    public static Socket socket; //Socket object
    public static BufferedReader bfr; //BufferedReader object
    public static PrintWriter writer; //PrintWriter object

    public static MainMenu mainMenuFrame;
    public static CreateAccountMenu createActFrame;
    public static LogInMenu logInFrame;
    public static TeacherMenu teacherFrame;
    public static StudentMenu studentFrame;
    public static TeacherSettingsMenu teacherSettingsFrame;
    public static StudentSettingsMenu studentSettingsFrame;
    public static CreateNewCourseMenu createCourseFrame;
    public static EnrollCourse enrollCourseFrame;

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
            mainMenuFrame = new MainMenu();
            createActFrame = new CreateAccountMenu();
            logInFrame = new LogInMenu();
            teacherFrame = new TeacherMenu();
            studentFrame = new StudentMenu();
            teacherSettingsFrame = new TeacherSettingsMenu();
            studentSettingsFrame = new StudentSettingsMenu();
            createCourseFrame = new CreateNewCourseMenu();
            enrollCourseFrame = new EnrollCourse();

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
                        createCourseFrame.setVisible(false);
                        teacherFrame.setVisible(true);
                    }
                    case "User Info Edit" -> {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        setUserInfo(ID, name, email, password);
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
        teacherFrame.setUserName(userName);
        studentFrame.setUserName(userName);
        teacherSettingsFrame.setUserName(userName);
        studentSettingsFrame.setUserName(userName);
        createCourseFrame.setUserName(userName);
        enrollCourseFrame.setUserName(userName);
        teacherFrame.setUserEmail(userEmail);
        studentFrame.setUserEmail(userEmail);
        teacherSettingsFrame.setUserEmail(userEmail);
        studentSettingsFrame.setUserEmail(userEmail);
        createCourseFrame.setUserEmail(userEmail);
        enrollCourseFrame.setUserEmail(userEmail);
        teacherFrame.setUserPassword(userPassword);
        studentFrame.setUserPassword(userPassword);
        teacherSettingsFrame.setUserPassword(userPassword);
        studentSettingsFrame.setUserPassword(userPassword);
        createCourseFrame.setUserPassword(userPassword);
        enrollCourseFrame.setUserPassword(userPassword);
        teacherSettingsFrame.resetTeacherSettingLabels();
        studentSettingsFrame.resetStudentSettingLabels();
    }
}

