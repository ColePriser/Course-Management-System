import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class StudentSettingsMenu extends JFrame {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JButton editNameButton;
    private JButton editEmailButton;
    private JButton editPasswordButton;
    private JButton deleteAccountButton;
    private JButton backButton;
    private JPanel studentSettingsPanel;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;

    public StudentSettingsMenu() {
        setContentPane(studentSettingsPanel);
        setTitle("Student Settings Menu");
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

        editNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempName = "";
                do {
                    tempName = (JOptionPane.showInputDialog(null, "What is your new name?",
                            "Edit Name",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempName == null) || (tempName.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Edit Name",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempName == null) || (tempName.isBlank()));
                JOptionPane.showMessageDialog(null,
                        "Your name has been changed successfully!",
                        "Edit Name", JOptionPane.INFORMATION_MESSAGE);
                User.writer.write("Student Edit Name");
                User.writer.println();
                User.writer.flush();
                User.writer.write(tempName);
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(userID));
                User.writer.println();
                User.writer.flush();
                resetStudentSettingLabels();
            }
        });

        editEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempEmail = "";
                do {
                    tempEmail = (JOptionPane.showInputDialog(null, "What is your new email?",
                            "Edit Name",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempEmail == null) || (tempEmail.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Edit Email",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempEmail == null) || (tempEmail.isBlank()));
                JOptionPane.showMessageDialog(null,
                        "Your email has been changed successfully!",
                        "Edit Email", JOptionPane.INFORMATION_MESSAGE);
                User.writer.write("Student Edit Email");
                User.writer.println();
                User.writer.flush();
                User.writer.write(tempEmail);
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(userID));
                User.writer.println();
                User.writer.flush();
            }
        });

        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempPassword = "";
                do {
                    tempPassword = (JOptionPane.showInputDialog(null, "What is your new password?",
                            "Edit Password",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempPassword == null) || (tempPassword.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Edit Password",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempPassword == null) || (tempPassword.isBlank()));
                JOptionPane.showMessageDialog(null,
                        "Your password has been changed successfully!",
                        "Edit Password", JOptionPane.INFORMATION_MESSAGE);
                User.writer.write("Student Edit Password");
                User.writer.println();
                User.writer.flush();
                User.writer.write(tempPassword);
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(userID));
                User.writer.println();
                User.writer.flush();
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Delete Student Account");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(userID));
                User.writer.println();
                User.writer.flush();
                JOptionPane.showMessageDialog(null,
                        "Account successfully deleted! Returning to Main Menu.",
                        "Delete Account", JOptionPane.INFORMATION_MESSAGE);
                User.studentSettingsFrame.setVisible(false);
                User.mainMenuFrame.setVisible(true);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.studentSettingsFrame.setVisible(false);
                User.studentFrame.setVisible(true);
            }
        });
    }

    public void resetStudentSettingLabels() {
        idLabel.setText("ID: " + userID);
        nameLabel.setText("Name: " + userName);
        emailLabel.setText("Email: " + userEmail);
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

