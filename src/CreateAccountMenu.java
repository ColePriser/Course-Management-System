import javax.swing.*;
import java.awt.*;

public class CreateAccountMenu extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField IDField;
    private JPasswordField passwordField;
    private JCheckBox studentCheckBox;
    private JCheckBox teacherCheckBox;
    private JPanel createAccountPanel;

    public CreateAccountMenu() {
        setContentPane(createAccountPanel);
        setTitle("Create Account");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(true);
    }
}
