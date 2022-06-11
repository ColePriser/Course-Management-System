import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountMenu extends JFrame {
    private JTextField nameField;
    private JTextField emailField;
    private JTextField IDField;
    private JPasswordField passwordField;
    private JCheckBox studentCheckBox;
    private JCheckBox teacherCheckBox;
    private JPanel createAccountPanel;
    private JButton submitButton;
    private JButton backButton;

    public CreateAccountMenu() {
        setContentPane(createAccountPanel);
        setTitle("Create Account");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentCheckBox.isSelected() && teacherCheckBox.isSelected()) {
                    User.writer.write("Create Account Both Checked");
                    User.writer.println();
                    User.writer.flush();
                }
                else if (!studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                    User.writer.write("Create Account Neither Checked");
                    User.writer.println();
                    User.writer.flush();
                }
                else if (studentCheckBox.isSelected()) {
                    User.writer.write("Create Student Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(nameField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(emailField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(passwordField.getPassword());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                }
                else if (teacherCheckBox.isSelected()) {
                    User.writer.write("Create Teacher Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(nameField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(emailField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(passwordField.getPassword());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                }
            }
        });
    }
}
