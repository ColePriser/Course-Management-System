import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.studentFrame.setVisible(false);
                User.studentSettingsFrame.setVisible(true);
                User.studentSettingsFrame.resetStudentSettingLabels();
            }
        });

        returnToMainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.teacherFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
            }
        });

        createNewCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.teacherFrame.setVisible(false);
                User.createCourseFrame.setVisible(true);
            }
        });
    }
}
