import javax.swing.*;
import java.awt.*;

public class LogInMenu extends JFrame {
    private JCheckBox teacherCheckBox;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JCheckBox studentCheckBox;
    private JPanel logInPanel;

    public LogInMenu() {
        setContentPane(logInPanel);
        setTitle("Log In");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
    }
}
