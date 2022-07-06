import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class StudentViewCourses extends JFrame {
    private JPanel studentViewCoursesPanel;
    private JLabel tempLabel;
    private JButton course1;
    private JButton course2;
    private JButton course3;
    private JButton course4;
    private JButton course5;
    private JButton course6;
    private JButton course7;
    private JButton course8;
    private JButton backButton;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private ArrayList<Course> studentCourses = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();

    public StudentViewCourses() {
        setTitle("View Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        buttons.add(course1);
        buttons.add(course2);
        buttons.add(course3);
        buttons.add(course4);
        buttons.add(course5);
        buttons.add(course6);
        buttons.add(course7);
        buttons.add(course8);
        setContentPane(studentViewCoursesPanel);

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
                User.writer.write("Student View Enrolled Courses Back");
                User.writer.println();
                User.writer.flush();
            }
        });

        course1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(0).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(1).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(2).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(3).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(4).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(5).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(6).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Student Open Course");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(studentCourses.get(7).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });
    }

    public void resetCourseList() {
        for (int x = 0; x < studentCourses.size(); x++) {
            buttons.get(x).setText(studentCourses.get(x).getCourseName());
            buttons.get(x).setVisible(true);
        }
        for (int y = studentCourses.size(); y < 8; y++) {
            buttons.get(y).setVisible(false);
        }
        if (studentCourses.size() == 0) {
            tempLabel.setText("You currently have no courses registered to your account!");
        }
        else {
            tempLabel.setText("");
        }
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

    public void addStudentCourse(String courseName, int courseID, String coursePassword, String teacherName, int teacherID, String teacherEmail, String teacherPassword) {
        Teacher cur = new Teacher(teacherName, teacherID, teacherEmail, teacherPassword);
        Course newCourse = new Course(courseName, courseID, coursePassword, cur);
        studentCourses.add(newCourse);
    }
}