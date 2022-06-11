import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;


public class User implements Runnable {
    public static Socket socket; //Socket object
    public static BufferedReader bfr; //BufferedReader object
    public static PrintWriter writer; //PrintWriter object
    private static int userID; //ID of user
    private static String userName; //Name of user
    private static String userEmail; //Email of user
    private static String userPassword; //Password of user

    private static MainMenu mainMenuFrame;
    private static CreateAccountMenu createActFrame;
    private static LogInMenu logInFrame;

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
            mainMenuFrame = new MainMenu();
            createActFrame = new CreateAccountMenu();
            logInFrame = new LogInMenu();

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
                switch (messageFromServer) {
                    case "Create Account" -> {
                        mainMenuFrame.setVisible(false);
                        createActFrame.setVisible(true);
                        System.out.println("create act");
                    }
                    case "Log In" -> {
                        mainMenuFrame.setVisible(false);
                        logInFrame.setVisible(true);
                    }
                    case "Teacher Account Created Success", "Student Account Created Success" -> {
                        JOptionPane.showMessageDialog(null,
                                "Your account has been made successfully!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Teacher ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a teacher with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Teacher Sign In Success" -> {
                        userName = bfr.readLine();
                        userEmail = bfr.readLine();
                        userPassword = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have signed in successfully!",
                                "Sign In", JOptionPane.INFORMATION_MESSAGE);
                        mainMenuFrame.setVisible(false);
                        //teacherMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //teacherMenu.setVisible(true);
                    }
                    case "Teacher Sign In Failed" -> {
                        JOptionPane.showMessageDialog(null,
                                "No teacher account exists with the given ID and password.",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Student ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a student with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenuFrame.setVisible(true);
                    }
                    case "Student Sign In Success" -> {
                        userName = bfr.readLine();
                        userEmail = bfr.readLine();
                        userPassword = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have signed in successfully!",
                                "Sign In", JOptionPane.INFORMATION_MESSAGE);
                        mainMenuFrame.setVisible(false);
                        //studentMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        //studentMenu.setVisible(true);
                    }
                    case "Student Sign In Failed" -> {
                        JOptionPane.showMessageDialog(null,
                                "No student account exists with the given ID and password.",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenuFrame.setVisible(true);
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}

