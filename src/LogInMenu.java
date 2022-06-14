import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInMenu extends JFrame {
    private JCheckBox teacherCheckBox;
    private JTextField IDField;
    private JPasswordField passwordField1;
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
/*
        ActionListener submitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == submitButton) {
                    if (studentCheckBox.isSelected() && teacherCheckBox.isSelected()) {
                        User.writer.write("Log In Menu Both Checked");
                        User.writer.println();
                        User.writer.flush();
                    } else if (!studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                        User.writer.write("Log In Menu Neither Checked");
                        User.writer.println();
                        User.writer.flush();
                    } else if (studentCheckBox.isSelected()) {
                        User.writer.write("Log In Menu Student Account");
                        User.writer.println();
                        User.writer.flush();
                        User.writer.write(IDField.getText());
                        User.writer.println();
                        User.writer.flush();
                        User.writer.write(passwordField1.getPassword());
                        User.writer.println();
                        User.writer.flush();
                    } else if (teacherCheckBox.isSelected()) {
                        User.writer.write("Log In Menu Teacher Account");
                        User.writer.println();
                        User.writer.flush();
                        User.writer.write(IDField.getText());
                        User.writer.println();
                        User.writer.flush();
                        User.writer.write(passwordField1.getPassword());
                        User.writer.println();
                        User.writer.flush();
                    }
                }
            }
        };
        submitButton.addActionListener(submitListener);*/

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentCheckBox.isSelected() && teacherCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Both Checked");
                    User.writer.println();
                    User.writer.flush();
                } else if (!studentCheckBox.isSelected() && !teacherCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Neither Checked");
                    User.writer.println();
                    User.writer.flush();
                } else if (studentCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Student Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(passwordField1.getPassword());
                    User.writer.println();
                    User.writer.flush();
                } else if (teacherCheckBox.isSelected()) {
                    User.writer.write("Log In Menu Teacher Account");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(IDField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(passwordField1.getPassword());
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
