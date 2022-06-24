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

    private ArrayList<Course> courses = new ArrayList<>();

    public teacherViewCourses() {
        setContentPane(teacherViewCoursesPanel);
        setTitle("View Courses");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);

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
}
