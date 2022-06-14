import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainMenu extends JFrame {
    private int userID; //ID of user
    private String userName; //Name of user
    private String userEmail; //Email of user
    private String userPassword; //Password of user

    private JPanel mainMenuPanel;
    private JButton createNewAccountButton;
    private JButton logInToAccountButton;

    public MainMenu() {
        setContentPane(mainMenuPanel);
        setTitle("Main Menu");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });

        ActionListener createNewAccountListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == createNewAccountButton) {
                    User.mainMenuFrame.setVisible(false);
                    User.createActFrame.setVisible(true);
                }
            }
        };
        createNewAccountButton.addActionListener(createNewAccountListener);

        ActionListener logInToAccountListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == logInToAccountButton) {
                    User.mainMenuFrame.setVisible(false);
                    User.logInFrame.setVisible(true);
                }
            }
        };
        logInToAccountButton.addActionListener(logInToAccountListener);
    }
}
