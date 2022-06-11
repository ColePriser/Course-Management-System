import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    private int userID; //ID of user
    private String userName; //Name of user
    private String userEmail; //Email of user
    private String userPassword; //Password of user

    private JPanel mainMenuPanel;
    private JButton createNewAccountButton;
    private JButton signInToAccountButton;

    public MainMenu() {
        setContentPane(mainMenuPanel);
        setTitle("Main Menu");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(true);

        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                CreateAccountMenu createAct = new CreateAccountMenu();
            }
        });

        signInToAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
