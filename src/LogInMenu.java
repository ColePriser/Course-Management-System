import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LogInMenu extends JFrame {
    private JCheckBox teacherCheckBox;
    private JTextField IDField;
    private JPasswordField passwordField;
    private JPanel logInPanel;
    private JButton backButton;
    private JButton submitButton;
    private JCheckBox studentCheckBox;

    public LogInMenu() {
        setContentPane(logInPanel);
        setTitle("Log In");
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
                            "Log In", JOptionPane.ERROR_MESSAGE);
                }
                else if (!studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                    JOptionPane.showMessageDialog(null,
                            "Choose either Student or Teacher!",
                            "Log In", JOptionPane.ERROR_MESSAGE);
                }
                else if (idError) {
                    JOptionPane.showMessageDialog(null, "ID can only contain numbers!", "Log In",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(ID).length() != 7) {
                    JOptionPane.showMessageDialog(null, "ID must have length of 7!", "Log In",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (teacherCheckBox.isSelected() && String.valueOf(ID).charAt(0) != '2') {
                    JOptionPane.showMessageDialog(null, "Teacher Accounts must have ID number that starts with '2'!",
                            "Log In",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (studentCheckBox.isSelected() && String.valueOf(ID).charAt(0) != '1') {
                    JOptionPane.showMessageDialog(null, "Student Accounts must have ID number that starts with '1'!",
                            "Log In",
                            JOptionPane.ERROR_MESSAGE);

                }
                else if (String.valueOf(passwordField.getPassword()).isBlank()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                            "Log In", JOptionPane.ERROR_MESSAGE);
                }
                else if (studentCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Student Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(String.valueOf(passwordField.getPassword()));
                    User.writer.println();
                    User.writer.flush();
                }
                else if (teacherCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Teacher Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(String.valueOf(passwordField.getPassword()));
                    User.writer.println();
                    User.writer.flush();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.logInFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
            }
        });


    }
}
