import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private static Socket socket; //Socket object
    private static BufferedReader bfr; //BufferedReader object
    private static PrintWriter writer; //PrintWriter object
    private static int ID; //ID of client

    private static JFrame mainMenu = new JFrame("Main Menu");
    private static JFrame teacherMenu = new JFrame("Teacher Menu");
    private static JFrame studentMenu = new JFrame("Student Menu");
    private static JFrame teacherSettingsMenu = new JFrame("Settings");
    private static JFrame studentSettingsMenu = new JFrame("Settings");


    public static void main(String[] args) {
        Thread client = new Thread(new Client());
        client.start();
        try {
            /**
             * Creating socket and setting up reading and writing capabilities
             * to communicate with the server.
             */
            socket = new Socket("localhost", 2361);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());

            /**
             * Main menu GUI
             */
            JPanel mainMenuPanel = new JPanel();
            Container mainMenuContainer = mainMenu.getContentPane();
            mainMenuContainer.setLayout(new BorderLayout());
            mainMenu.setSize(1000, 800);
            ImageIcon logoIcon = new ImageIcon("book.png");
            Image logo = logoIcon.getImage();
            mainMenu.setIconImage(logo);
            JButton createAccountButton = new JButton("Create New Account");
            JButton signInButton = new JButton("Sign In To Account");
            mainMenuPanel.add(createAccountButton);
            mainMenuPanel.add(signInButton);
            mainMenuContainer.add(mainMenuPanel, BorderLayout.CENTER);
            mainMenu.setVisible(true);

            /**
             * Teacher menu GUI
             */
            JPanel teacherPanel = new JPanel();
            Container teacherMenuContainer = teacherMenu.getContentPane();
            teacherMenuContainer.setLayout(new BorderLayout());
            teacherPanel.setSize(1000, 800);
            JButton teacherSettings = new JButton("Settings");
            JButton teacherManageCourses = new JButton("Manage Courses");
            teacherPanel.add(teacherSettings);
            teacherPanel.add(teacherManageCourses);
            teacherMenuContainer.add(teacherPanel, BorderLayout.CENTER);

            /**
            * Teacher Settings GUI
            */
            JPanel teacherSettingsPanel = new JPanel();
            Container teacherSettingsContainer = teacherSettingsMenu.getContentPane();
            teacherSettingsContainer.setLayout(new BorderLayout());
            teacherSettingsPanel.setSize(1000, 800);
            JButton teacherEditID = new JButton("Edit ID");
            JButton teacherEditName = new JButton("Edit Name");
            JButton teacherEditEmail = new JButton("Edit Email");
            JButton teacherEditPassword = new JButton("Edit Password");
            JButton teacherDeleteAccount = new JButton("Delete Account");
            teacherSettingsPanel.add(teacherEditID);
            teacherSettingsPanel.add(teacherEditName);
            teacherSettingsPanel.add(teacherEditEmail);
            teacherSettingsPanel.add(teacherEditPassword);
            teacherSettingsPanel.add(teacherDeleteAccount);
            teacherSettingsContainer.add(teacherSettingsPanel, BorderLayout.CENTER);

            /**
             * Student menu GUI
             */
            JPanel studentPanel = new JPanel();
            Container studentMenuContainer = studentMenu.getContentPane();
            studentMenuContainer.setLayout(new BorderLayout());
            studentPanel.setSize(1000, 800);
            JButton studentSettings = new JButton("Settings");
            JButton studentManageCourses = new JButton("Manage Courses");
            studentPanel.add(studentSettings);
            studentPanel.add(studentManageCourses);
            studentMenuContainer.add(studentPanel, BorderLayout.CENTER);

            /**
             * Student Settings GUI
             */
            JPanel studentSettingsPanel = new JPanel();
            Container studentSettingsContainer = studentSettingsMenu.getContentPane();
            studentSettingsContainer.setLayout(new BorderLayout());
            studentSettingsPanel.setSize(1000, 800);
            JButton studentEditID = new JButton("Edit ID");
            JButton studentEditName = new JButton("Edit Name");
            JButton studentEditEmail = new JButton("Edit Email");
            JButton studentEditPassword = new JButton("Edit Password");
            JButton studentDeleteAccount = new JButton("Delete Account");
            studentSettingsPanel.add(studentEditID);
            studentSettingsPanel.add(studentEditName);
            studentSettingsPanel.add(studentEditEmail);
            studentSettingsPanel.add(studentEditPassword);
            studentSettingsPanel.add(studentDeleteAccount);
            studentSettingsContainer.add(studentSettingsPanel, BorderLayout.CENTER);

            /**
             * When user clicks "Create Account", they are prompted to
             * input their account type (Student or Teacher), name,
             * email, password, and ID. This information is sent to the server.
             */
            ActionListener createAccountListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == createAccountButton) {
                        mainMenu.setVisible(false);
                        String[] accountType = {"Student", "Teacher"};
                        String type = (String) JOptionPane.showInputDialog(null, "Select your account type",
                                "Create Account", JOptionPane.QUESTION_MESSAGE, null, accountType,
                                accountType[0]);
                        String name;
                        do {
                            name = JOptionPane.showInputDialog(null, "What is your full name?",
                                    "Create Account", JOptionPane.QUESTION_MESSAGE);
                            if ((name == null) || (name.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((name == null) || (name.isBlank()));
                        String email;
                        do {
                            email = JOptionPane.showInputDialog(null, "What is your email?",
                                    "Create Account", JOptionPane.QUESTION_MESSAGE);
                            if ((email == null) || (email.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((email == null) || (email.isBlank()));
                        String password;
                        do {
                            password = JOptionPane.showInputDialog(null, "Enter your desired password.",
                                    "Create Account", JOptionPane.QUESTION_MESSAGE);
                            if ((password == null) || (password.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((password == null) || (password.isBlank()));
                        int ID = 0;
                        boolean IDError = true;
                        boolean IDLengthError = true;
                        boolean IDStartNumError = true;
                        do {
                            IDError = false;
                            IDLengthError = false;
                            IDStartNumError = false;
                            try {
                                ID = Integer.parseInt(JOptionPane.showInputDialog(null, "What is your ID?",
                                        "Create Account",
                                        JOptionPane.QUESTION_MESSAGE));
                            } catch (NumberFormatException ime) {
                                JOptionPane.showMessageDialog(null, "ID can only contain numbers!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);
                                IDError = true;
                            }
                            if (!IDError && String.valueOf(ID).length() != 7) {
                                JOptionPane.showMessageDialog(null, "ID must have length of 7!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);
                                IDLengthError = true;
                            }
                            if (!IDError && !IDLengthError && type.equals("Teacher")) {
                                if (String.valueOf(ID).charAt(0) != '2') {
                                    JOptionPane.showMessageDialog(null, "Teacher Accounts must have ID number that starts with '2'!",
                                            "Create Account",
                                            JOptionPane.ERROR_MESSAGE);
                                    IDStartNumError = true;
                                }
                            }
                            if (!IDError && !IDLengthError && type.equals("Student")) {
                                if (String.valueOf(ID).charAt(0) != '1') {
                                    JOptionPane.showMessageDialog(null, "Student Accounts must have ID number that starts with '1'!",
                                            "Create Account",
                                            JOptionPane.ERROR_MESSAGE);
                                    IDStartNumError = true;
                                }
                            }
                        } while (IDError || IDLengthError || IDStartNumError);
                        if (type.equals("Teacher")) {
                            writer.write("Create Teacher Account");
                        } else {
                            writer.write("Create Student Account");
                        }
                        writer.println();
                        writer.flush();
                        writer.write(name);
                        writer.println();
                        writer.flush();
                        writer.write(email);
                        writer.println();
                        writer.flush();
                        writer.write(password);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            createAccountButton.addActionListener(createAccountListener);

            /**
             * When user clicks "Sign In", they are prompted to
             * input their, ID and password. This information is sent to the server.
             */
            ActionListener signInListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == signInButton) {
                        mainMenu.setVisible(false);
                        String[] accountType = {"Student", "Teacher"};
                        String type = (String) JOptionPane.showInputDialog(null, "Select your account type",
                                "Create Account", JOptionPane.QUESTION_MESSAGE, null, accountType,
                                accountType[0]);
                        int ID = 0;
                        boolean IDError = true;
                        boolean IDLengthError = true;
                        do {
                            IDError = false;
                            IDLengthError = false;
                            try {
                                ID = Integer.parseInt(JOptionPane.showInputDialog(null, "What is your ID?",
                                        "Sign In",
                                        JOptionPane.QUESTION_MESSAGE));
                            } catch (NumberFormatException ime) {
                                JOptionPane.showMessageDialog(null, "ID can only contain numbers!", "Sign In",
                                        JOptionPane.ERROR_MESSAGE);
                                IDError = true;
                            }
                            if (!IDError && String.valueOf(ID).length() != 7) {
                                JOptionPane.showMessageDialog(null, "ID must have length of 7!", "Sign In",
                                        JOptionPane.ERROR_MESSAGE);
                                IDLengthError = true;
                            }
                        } while (IDError || IDLengthError);
                        String password = "";
                        do {
                            password = JOptionPane.showInputDialog(null, "Enter your password.",
                                    "Sign In", JOptionPane.QUESTION_MESSAGE);
                            if ((password == null) || (password.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Sign In",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while ((password == null) || (password.isBlank()));
                        if (type.equals("Teacher")) {
                            writer.write("Sign In To Teacher Account");
                        } else {
                            writer.write("Sign In To Student Account");
                        }
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                        writer.write(password);
                        writer.println();
                        writer.flush();

                    }
                }
            };
            signInButton.addActionListener(signInListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String messageFromServer;
                try {
                    messageFromServer = bfr.readLine();
                } catch (Exception e) {
                    continue;
                }
                if (messageFromServer.equals("Teacher ID Taken")) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to create account! There already exists a teacher with this ID!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                    mainMenu.setVisible(true);
                } else if (messageFromServer.equals("Teacher Account Created Success")) {
                    JOptionPane.showMessageDialog(null,
                            "Your account has been made successfully!",
                            "Create Account", JOptionPane.INFORMATION_MESSAGE);
                    mainMenu.setVisible(true);
                } else if (messageFromServer.equals("Student ID Taken")) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to create account! There already exists a student with this ID!",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                    mainMenu.setVisible(true);
                } else if (messageFromServer.equals("Student Account Created Success")) {
                    JOptionPane.showMessageDialog(null,
                            "Your account has been made successfully!",
                            "Create Account", JOptionPane.INFORMATION_MESSAGE);
                    mainMenu.setVisible(true);
                } else if (messageFromServer.equals("Teacher Sign In Success")) {
                    JOptionPane.showMessageDialog(null,
                            "You have signed in successfully!",
                            "Sign In", JOptionPane.INFORMATION_MESSAGE);
                    mainMenu.setVisible(false);
                    teacherMenu.setVisible(true);
                }
                else if (messageFromServer.equals("Teacher Sign In Failed")) {
                    JOptionPane.showMessageDialog(null,
                            "No teacher account exists with the given ID and password.",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                    mainMenu.setVisible(true);
                }
                else if (messageFromServer.equals("Student Sign In Success")) {
                    JOptionPane.showMessageDialog(null,
                            "You have signed in successfully!",
                            "Sign In", JOptionPane.INFORMATION_MESSAGE);
                    mainMenu.setVisible(false);
                    studentMenu.setVisible(true);
                }
                else if (messageFromServer.equals("Student Sign In Failed")) {
                    JOptionPane.showMessageDialog(null,
                            "No student account exists with the given ID and password.",
                            "Create Account", JOptionPane.ERROR_MESSAGE);
                    mainMenu.setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
