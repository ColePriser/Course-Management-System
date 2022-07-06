import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentOpenCourse extends JFrame {

    private JPanel studentOpenCoursePanel;
    private JLabel courseNameLabel;
    private JLabel courseIDLabel;
    private JLabel teacherNameLabel;
    private JButton backButton;
    private JButton unEnrollInCourseButton;
    private JButton viewGradesButton;
    private JButton viewHomeworkButton;
    private JButton viewQuizzesButton;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String courseName;
    private int courseID;
    private String teacherName;
    private String coursePassword;

    public StudentOpenCourse() {
        setTitle("Student Open Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        setContentPane(studentOpenCoursePanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course Back");
                User.writer.println();
                User.writer.flush();
            }
        });

        unEnrollInCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Unenroll In Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(courseID));
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(userID));
                User.writer.println();
                User.writer.flush();
                JOptionPane.showMessageDialog(null,
                        "Successfully Unenrolled! Returning to Student Menu.",
                        "Unenroll In Course", JOptionPane.INFORMATION_MESSAGE);
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
