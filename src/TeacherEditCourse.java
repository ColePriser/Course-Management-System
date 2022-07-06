import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeacherEditCourse extends JFrame {
    private JPanel teacherEditCoursePanel;
    private JLabel courseNameLabel;
    private JLabel courseIDLabel;
    private JLabel teacherNameLabel;
    private JButton addHomeworkButton;
    private JButton gradeQuizButton;
    private JButton backButton;
    private JButton editCourseNameButton;
    private JButton editCoursePasswordButton;
    private JButton viewEnrolledStudentsButton;
    private JButton addQuizButton;
    private JButton gradeHomeworkButton;
    private JButton deleteCourseButton;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String courseName;
    private int courseID;
    private String teacherName;
    private String coursePassword;

    public TeacherEditCourse() {
        setTitle("Teacher Edit Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        setContentPane(teacherEditCoursePanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });

        editCourseNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempName = "";
                do {
                    tempName = (JOptionPane.showInputDialog(null, "What is your new course name?",
                            "Edit Course Name",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempName == null) || (tempName.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Course name cannot be empty!", "Edit Course Name",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempName == null) || (tempName.isBlank()));
                JOptionPane.showMessageDialog(null,
                        "Your course name has been changed successfully!",
                        "Edit Course Name", JOptionPane.INFORMATION_MESSAGE);
                User.writer.write("Edit Course Name");
                User.writer.println();
                User.writer.flush();
                User.writer.write(tempName);
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(courseID));
                User.writer.println();
                User.writer.flush();
                resetEditCourseLabels();
            }
        });

        editCoursePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempPassword = "";
                do {
                    tempPassword = (JOptionPane.showInputDialog(null, "What is your new course password?",
                            "Edit Course Password",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempPassword == null) || (tempPassword.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Course password cannot be empty!", "Edit Course Password",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempPassword == null) || (tempPassword.isBlank()));
                JOptionPane.showMessageDialog(null,
                        "Your course password has been changed successfully!",
                        "Edit Course Password", JOptionPane.INFORMATION_MESSAGE);
                User.writer.write("Edit Course Password");
                User.writer.println();
                User.writer.flush();
                User.writer.write(tempPassword);
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(courseID));
                User.writer.println();
                User.writer.flush();
                resetEditCourseLabels();
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Delete Course Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(courseID));
                User.writer.println();
                User.writer.flush();
                JOptionPane.showMessageDialog(null,
                        "Course successfully deleted! Returning to Teacher Menu.",
                        "Delete Course", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Courses Back");
                User.writer.println();
                User.writer.flush();
            }
        });

        viewEnrolledStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Enrolled Students");
                User.writer.println();
                User.writer.flush();
            }
        });
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setCourseInfo(String courseName, int courseID, String teacherName, String coursePassword) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.teacherName = teacherName;
        this.coursePassword = coursePassword;
    }

    public void resetEditCourseLabels() {
        courseNameLabel.setText("Course Name: " + courseName);
        courseIDLabel.setText("Course ID: " + courseID);
        teacherNameLabel.setText("Teacher: " + teacherName);
    }
}
