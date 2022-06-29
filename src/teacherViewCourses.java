import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class teacherViewCourses extends JFrame {
    private JPanel teacherViewCoursesPanel;
    private JPanel courseOne;
    private JPanel courseTwo;
    private JPanel courseThree;
    private JPanel courseFour;
    private JPanel courseFive;
    private JPanel courseSix;
    private JPanel courseSeven;
    private JPanel courseEight;
    private JLabel courseNameOne;
    private JLabel courseNameTwo;
    private JLabel courseNameThree;
    private JLabel courseNameFour;
    private JLabel courseNameFive;
    private JLabel courseNameSix;
    private JLabel courseNameSeven;
    private JLabel courseNameEight;
    private JButton backButton;

    private int numCourses;
    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private ArrayList<Course> teacherCourses = new ArrayList<>();
    private ArrayList<JPanel> panels = new ArrayList<>();
    private ArrayList<JLabel> nameLabels = new ArrayList<>();

    public teacherViewCourses() {
        setTitle("View Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        numCourses = 0;
        nameLabels.add(courseNameOne);
        nameLabels.add(courseNameTwo);
        nameLabels.add(courseNameThree);
        nameLabels.add(courseNameFour);
        nameLabels.add(courseNameFive);
        nameLabels.add(courseNameSix);
        nameLabels.add(courseNameSeven);
        nameLabels.add(courseNameEight);
        panels.add(courseOne);
        panels.add(courseTwo);
        panels.add(courseThree);
        panels.add(courseFour);
        panels.add(courseFive);
        panels.add(courseSix);
        panels.add(courseSeven);
        panels.add(courseEight);
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
    }

    public void resetCourseList() {
        User.writer.write("Reset Course List");
        User.writer.println();
        User.writer.flush();
        User.writer.write(Integer.toString(Server.getCourses(userID).size()));
        User.writer.println();
        User.writer.flush();
        for (int x = 0; x < numCourses; x++) {
            nameLabels.get(x).setText(teacherCourses.get(x).getCourseName());
        }
        for (int y = numCourses; y < 8; y++) {
            nameLabels.get(y).setText("");
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
        this.numCourses++;
    }
}
