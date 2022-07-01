import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CreateNewCourseMenu extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JButton backButton;
    private JButton createButton;
    private JPanel createNewCoursePanel;
    private JPasswordField passwordField;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;

    public CreateNewCourseMenu() {
        setContentPane(createNewCoursePanel);
        setTitle("Create Course Menu");
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

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Create New Course Back");
                User.writer.println();
                User.writer.flush();
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean idError = false;
                int ID = 0;
                try {
                    ID = Integer.parseInt(idField.getText());
                } catch (NumberFormatException ime) {
                    idError = true;
                }
                if (nameField.getText() == null || nameField.getText().isBlank()) {
                    JOptionPane.showMessageDialog(null, "Name cannot be empty!",
                            "Create Course", JOptionPane.ERROR_MESSAGE);
                }
                else if (idError) {
                    JOptionPane.showMessageDialog(null, "ID can only contain numbers!", "Create Course",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(ID).length() != 5) {
                    JOptionPane.showMessageDialog(null, "Course ID must have length of 5!", "Create Course",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if (String.valueOf(passwordField.getPassword()).isBlank()) {
                    JOptionPane.showMessageDialog(null, "Password cannot be empty!",
                            "Create Course", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    User.writer.write("Create New Course Submit");
                    User.writer.println();
                    User.writer.flush();
                    User.writer.write(nameField.getText());
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
                    resetCreateCourseLabels();
                }
            }
        });
    }

    public void resetCreateCourseLabels() {
        nameField.setText("");
        idField.setText("");
        passwordField.setText("");
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
