import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends JFrame {
    private JButton viewCoursesButton;
    private JButton createNewCourseButton;
    private JButton settingsButton;
    private JButton returnToMainMenuButton;
    private JPanel teacherMenuPanel;

    public TeacherMenu() {
        setContentPane(teacherMenuPanel);
        setTitle("Teacher Menu");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);

        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.teacherFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
            }
        });
    }
}
