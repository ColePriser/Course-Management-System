import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean idError = false;
                int ID = 0;
                try {
                    ID = Integer.parseInt(IDField.getText());
                } catch (NumberFormatException ime) {
                    idError = true;
                }
                if (studentCheckBox.isSelected() && teacherCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null,
                            "Choose either Student or Teacher!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                }
                else if (!studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null,
                            "Choose either Student or Teacher!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                }
                else if (nameField.getText() == null || nameField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                }
                else if (emailField.getText() == null || emailField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Email cannot be empty!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(passwordField.getPassword()).isBlank()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                }
                else if (idError) {
                    JOptionPane.showMessageDialog(null, "ID can only contain numbers!", "Create Account",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(ID).length() != 7) {
                    JOptionPane.showMessageDialog(null, "ID must have length of 7!", "Create Account",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (teacherCheckBox.isSelected() && String.valueOf(ID).charAt(0) != '2') {
                    JOptionPane.showMessageDialog(null, "Teacher Accounts must have ID number that starts with '2'!",
                            "Create Account",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (studentCheckBox.isSelected() && String.valueOf(ID).charAt(0) != '1') {
                    JOptionPane.showMessageDialog(null, "Student Accounts must have ID number that starts with '1'!",
                            "Create Account",
                            JOptionPane.ERROR_MESSAGE);

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
                    User.writer.write(String.valueOf(passwordField.getPassword()));
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
                    User.writer.write(String.valueOf(passwordField.getPassword()));
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.createActFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
            }
        });
    }
}
