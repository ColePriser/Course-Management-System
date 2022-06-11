import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;

public class MainMenu extends JFrame {
    private int userID; //ID of user
    private String userName; //Name of user
    private String userEmail; //Email of user
    private String userPassword; //Password of user

    private boolean close = false;

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

        createNewAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Main Menu Create Account");
                User.writer.println();
                User.writer.flush();
            }
        });

        logInToAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Main Menu Log In");
                User.writer.println();
                User.writer.flush();
            }
        });
    }

    public boolean getClose() {
        return close;
    }
}
