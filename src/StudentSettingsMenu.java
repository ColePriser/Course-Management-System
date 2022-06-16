import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

    public StudentSettingsMenu() {
        nameLabel.setText(User.getUserName());
        emailLabel.setText(User.getUserEmail());
        idLabel.setText(Integer.toString(User.getUserID()));
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
                User.userName = tempName;
            }
        });

        editEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempEmail = "";
                do {
                    tempEmail = (JOptionPane.showInputDialog(null, "What is your new name?",
                            "Edit Name",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempEmail == null) || (tempEmail.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Edit Name",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempEmail == null) || (tempEmail.isBlank()));
                User.userEmail = tempEmail;
            }
        });

        editPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tempPassword = "";
                do {
                    tempPassword = (JOptionPane.showInputDialog(null, "What is your new name?",
                            "Edit Name",
                            JOptionPane.QUESTION_MESSAGE));
                    if ((tempPassword == null) || (tempPassword.isBlank())) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Edit Name",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } while ((tempPassword == null) || (tempPassword.isBlank()));
                User.userPassword = tempPassword;
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
}

