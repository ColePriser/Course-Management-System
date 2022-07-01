import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnrollCourse extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton backButton;
    private JButton submitButton;
    private JPanel enrollCoursePanel;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;

    public EnrollCourse() {
        setContentPane(enrollCoursePanel);
        setTitle("Enroll In Course Menu");
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
                    ID = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ime) {
                    idError = true;
                }
                if (idError) {
                    JOptionPane.showMessageDialog(null, "Course ID can only contain numbers!", "Enroll In Course",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(ID).length() != 5) {
                    JOptionPane.showMessageDialog(null, "Course ID must have length of 5!", "Enroll In Course",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(passwordField.getPassword()).isBlank()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                            "Log In", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    User.writer.write("Student Enroll In New Course");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(idField.getText());
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(String.valueOf(passwordField.getPassword()));
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(Integer.toString(userID));
                    User.writer.println();
                    User.writer.flush();
                    resetEnrollCourseLabels();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Enroll Course Back");
                User.writer.println();
                User.writer.flush();
                resetEnrollCourseLabels();
            }
        });
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

    public void resetEnrollCourseLabels() {
        idField.setText("");
        passwordField.setText("");
    }

}
