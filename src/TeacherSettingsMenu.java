import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TeacherSettingsMenu extends JFrame {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JButton editNameButton;
    private JButton editEmailButton;
    private JButton editPasswordButton;
    private JButton deleteAccountButton;
    private JButton backButton;
    private JPanel teacherSettingsPanel;

    public TeacherSettingsMenu() {
        setContentPane(teacherSettingsPanel);
        setTitle("Teacher Settings Menu");
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
                User.userName = tempName;
                resetTeacherSettingLabels();
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
                User.userEmail = tempEmail;
                resetTeacherSettingLabels();
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
                User.userPassword = tempPassword;
                resetTeacherSettingLabels();
            }
        });

        deleteAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Delete Teacher Account");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(User.userID));
                User.writer.println();
                User.writer.flush();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.teacherSettingsFrame.setVisible(false);
                User.teacherFrame.setVisible(true);
            }
        });
    }

    public void resetTeacherSettingLabels() {
        idLabel.setText("ID: " + User.userID);
        nameLabel.setText("Name: " + User.userName);
        emailLabel.setText("Email: " + User.userEmail);
    }
}
