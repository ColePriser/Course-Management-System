import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;


public class User implements Runnable {
    private static Socket socket; //Socket object
    private static BufferedReader bfr; //BufferedReader object
    private static PrintWriter writer; //PrintWriter object
    private static int userID; //ID of user
    private static String userName; //Name of user
    private static String userEmail; //Email of user
    private static String userPassword; //Password of user

    private static JFrame mainMenu = new JFrame("Main Menu");
    private static JFrame teacherMenu = new JFrame("Teacher Menu");
    private static JFrame studentMenu = new JFrame("Student Menu");
    private static JFrame teacherSettingsMenu = new JFrame("Teacher Settings");
    private static JFrame studentSettingsMenu = new JFrame("Student Settings");
    private static JFrame studentAccountInfoMenu = new JFrame("Student Account Info");


    private JPanel mainMenuPanel;
    private JPanel panel1;
    private JButton signInToAccountButton;
    private JButton createNewAccountButton;

    public static void main(String[] args) {
        Thread user = new Thread(new User());
        user.start();
        try {
            /**
             * Creating socket and setting up reading and writing capabilities
             * to communicate with the server.
             */
            socket = new Socket("localhost", 2361);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            MainMenu myFrame = new MainMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String messageFromServer;
                try {
                    messageFromServer = bfr.readLine();
                } catch (Exception e) {
                    continue;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
