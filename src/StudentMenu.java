import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentMenu extends JFrame {
    private JButton viewEnrolledCoursesButton;
    private JButton enrollInNewCourseButton;
    private JButton viewGradesButton;
    private JPanel studentMenuPanel;
    private JButton returnToMainMenuButton;
    private JButton settingsButton;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;

    public StudentMenu() {
        setContentPane(studentMenuPanel);
        setTitle("Student Menu");
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

        enrollInNewCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.studentFrame.setVisible(false);
                User.enrollCourseFrame.setVisible(true);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.studentFrame.setVisible(false);
                User.studentSettingsFrame.setVisible(true);
            }
        });

        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.studentFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
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
}
