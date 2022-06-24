import javax.swing.*;
import java.awt.*;
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
    private JLabel test;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private ArrayList<Course> teacherCourses = new ArrayList<>();
    private ArrayList<JLabel> nameLabels = new ArrayList<>();

    public teacherViewCourses() {
        setContentPane(teacherViewCoursesPanel);
        setTitle("View Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        nameLabels.add(courseNameOne);
        nameLabels.add(courseNameTwo);
        nameLabels.add(courseNameThree);
        nameLabels.add(courseNameFour);
        nameLabels.add(courseNameFive);
        nameLabels.add(courseNameSix);
        nameLabels.add(courseNameSeven);
        nameLabels.add(courseNameEight);
        resetCourseList();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });
    }

    public void resetCourseList() {
        this.teacherCourses = Server.getCourses(userID);
        for (int x = 0; x < teacherCourses.size(); x++) {
            nameLabels.get(x).setText(teacherCourses.get(x).getCourseName());
        }
        int diff = nameLabels.size() - teacherCourses.size();
        if (diff > 0) {
            for (int y = teacherCourses.size(); y < nameLabels.size(); y++) {
                nameLabels.get(y).setText("");
                //find a way to hind these panels??
            }
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
}
