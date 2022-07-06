import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class teacherViewCourses extends JFrame {
    private JPanel teacherViewCoursesPanel;
    private JButton course1;
    private JButton course2;
    private JButton course3;
    private JButton course4;
    private JButton course5;
    private JButton course6;
    private JButton course7;
    private JButton course8;
    private JButton backButton;
    private JLabel tempLabel;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    public static ArrayList<Course> teacherCourses = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();

    public teacherViewCourses() {
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
        setContentPane(teacherViewCoursesPanel);

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
                User.writer.write("Teacher View Courses Back");
                User.writer.println();
                User.writer.flush();
            }
        });

        course1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(0).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(1).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(2).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(3).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(4).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(5).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(6).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        course8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher Edit Course Button");
                User.writer.println();
                User.writer.flush();
                ArrayList<Course> curCourses = getCoursesByID(userID);
                User.writer.write(Integer.toString(curCourses.get(7).getCourseID()));
                User.writer.println();
                User.writer.flush();
            }
        });
    }

    public void resetCourseList() {
        ArrayList<Course> curCourses = getCoursesByID(userID);
        for (int x = 0; x < curCourses.size(); x++) {
            buttons.get(x).setText(curCourses.get(x).getCourseName());
            buttons.get(x).setVisible(true);
        }
        for (int y = curCourses.size(); y < 8; y++) {
            buttons.get(y).setVisible(false);
        }
        if (curCourses.size() == 0) {
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

    public void addTeacherCourse(String name, int courseID, String password) {
            Teacher cur = new Teacher(this.userName, this.userID, this.userEmail, this.userPassword);
            Course newCourse = new Course(name, courseID, password, cur);
            teacherCourses.add(newCourse);
    }

    public void removeTeacherCourse(int courseID) {
        for (int x = 0; x < teacherCourses.size(); x++) {
            if (teacherCourses.get(x).getCourseID() == courseID) {
                teacherCourses.remove(x);
                break;
            }
        }
    }

    public ArrayList<Course> getCoursesByID(int teacherID) {
        ArrayList<Course> temp = new ArrayList<>();
        for (int x = 0; x < teacherCourses.size(); x++) {
            if (teacherCourses.get(x).getTeacher().getID() == teacherID) {
                temp.add(teacherCourses.get(x));
            }
        }
        return temp;
    }
}
