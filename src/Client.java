import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private static Socket socket; //Socket object
    private static BufferedReader bfr; //BufferedReader object
    private static PrintWriter writer; //PrintWriter object
    private static int userID; //ID of user

    private static JFrame mainMenu = new JFrame("Main Menu");
    private static JFrame teacherMenu = new JFrame("Teacher Menu");
    private static JFrame studentMenu = new JFrame("Student Menu");
    private static JFrame teacherSettingsMenu = new JFrame("Teacher Settings");
    private static JFrame studentSettingsMenu = new JFrame("Student Settings");
    private static JFrame studentAccountInfoMenu = new JFrame("Student Account Info");


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
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            mainMenu.setSize(screenSize.width, screenSize.height);
            ImageIcon logoIcon = new ImageIcon("book.png");
            Image logo = logoIcon.getImage();
            mainMenu.setIconImage(logo);
            JButton createAccountButton = new JButton("Create New Account");
            JButton signInButton = new JButton("Sign In To Account");
            mainMenuPanel.add(createAccountButton);
            mainMenuPanel.add(signInButton);
            mainMenuContainer.add(mainMenuPanel, BorderLayout.CENTER);
            mainMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            mainMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    writer.write("Main Menu Close");
                    writer.println();
                    writer.flush();
                }
            });
            mainMenu.setVisible(true);

            /**
             * Teacher menu GUI
             */
            JPanel teacherPanel = new JPanel();
            Container teacherMenuContainer = teacherMenu.getContentPane();
            teacherMenuContainer.setLayout(new BorderLayout());
            teacherPanel.setSize(screenSize.width, screenSize.height);
            JButton teacherSettingsButton = new JButton("Settings");
            JButton teacherManageCoursesButton = new JButton("Manage Courses");
            teacherPanel.add(teacherSettingsButton);
            teacherPanel.add(teacherManageCoursesButton);
            teacherMenuContainer.add(teacherPanel, BorderLayout.CENTER);
            teacherMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            teacherMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    mainMenu.setVisible(true);
                }
            });

            /**
            * Teacher Settings GUI
            */
            JPanel teacherSettingsPanel = new JPanel();
            Container teacherSettingsContainer = teacherSettingsMenu.getContentPane();
            teacherSettingsContainer.setLayout(new BorderLayout());
            teacherSettingsPanel.setSize(screenSize.width, screenSize.height);
            JButton teacherViewAccountInfoButton = new JButton("View Account Info");
            JButton teacherEditNameButton = new JButton("Edit Name");
            JButton teacherEditEmailButton = new JButton("Edit Email");
            JButton teacherEditPasswordButton = new JButton("Edit Password");
            JButton teacherDeleteAccountButton = new JButton("Delete Account");
            teacherSettingsPanel.add(teacherViewAccountInfoButton);
            teacherSettingsPanel.add(teacherEditNameButton);
            teacherSettingsPanel.add(teacherEditEmailButton);
            teacherSettingsPanel.add(teacherEditPasswordButton);
            teacherSettingsPanel.add(teacherDeleteAccountButton);
            teacherSettingsContainer.add(teacherSettingsPanel, BorderLayout.CENTER);
            teacherSettingsMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            teacherSettingsMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    teacherMenu.setVisible(true);
                }
            });

            /**
             * Student menu GUI
             */
            JPanel studentPanel = new JPanel();
            Container studentMenuContainer = studentMenu.getContentPane();
            studentMenuContainer.setLayout(new BorderLayout());
            studentPanel.setSize(screenSize.width, screenSize.height);
            JButton studentSettingsButton = new JButton("Settings");
            JButton studentManageCoursesButton = new JButton("Manage Courses");
            studentPanel.add(studentSettingsButton);
            studentPanel.add(studentManageCoursesButton);
            studentMenuContainer.add(studentPanel, BorderLayout.CENTER);
            studentMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            studentMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    mainMenu.setVisible(true);
                }
            });

            /**
             * Student Settings GUI
             */
            JPanel studentSettingsPanel = new JPanel();
            Container studentSettingsContainer = studentSettingsMenu.getContentPane();
            studentSettingsContainer.setLayout(new BorderLayout());
            studentSettingsPanel.setSize(screenSize.width, screenSize.height);
            JButton studentViewAccountInfoButton = new JButton("View Account Info");
            JButton studentEditNameButton = new JButton("Edit Name");
            JButton studentEditEmailButton = new JButton("Edit Email");
            JButton studentEditPasswordButton = new JButton("Edit Password");
            JButton studentDeleteAccountButton = new JButton("Delete Account");
            studentSettingsPanel.add(studentViewAccountInfoButton);
            studentSettingsPanel.add(studentEditNameButton);
            studentSettingsPanel.add(studentEditEmailButton);
            studentSettingsPanel.add(studentEditPasswordButton);
            studentSettingsPanel.add(studentDeleteAccountButton);
            studentSettingsContainer.add(studentSettingsPanel, BorderLayout.CENTER);
            studentSettingsMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            studentSettingsMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    studentMenu.setVisible(true);
                }
            });

            /**
             * Student Account Info GUI
             */
            studentAccountInfoMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            studentAccountInfoMenu.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                    studentSettingsMenu.setVisible(true);
                }
            });

            /**
             * When user clicks "Create Account" on the Main Menu, they are prompted to
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
             * When user clicks "Sign In" on the Main Menu, they are prompted to
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
                        userID = ID;
                        writer.write(password);
                        writer.println();
                        writer.flush();

                    }
                }
            };
            signInButton.addActionListener(signInListener);

            /**
             * When user clicks "Settings" on the Teacher Menu, they are showed
             * the settings that are specific to a Teacher.
             */
            ActionListener teacherSettingsListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == teacherSettingsButton) {
                        writer.write("Open Teacher Settings");
                        writer.println();
                        writer.flush();
                    }
                }
            };
            teacherSettingsButton.addActionListener(teacherSettingsListener);

            /**
             * When user clicks "Edit Name" in Teacher Settings Menu,
             * they will be prompted to input a new name.
             */
            ActionListener teacherEditNameListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == teacherEditNameButton) {
                        teacherSettingsMenu.setVisible(false);
                        String newName;
                        do {
                            newName = JOptionPane.showInputDialog(null, "Enter your new name.",
                                    "Edit Name", JOptionPane.QUESTION_MESSAGE);
                            if ((newName == null) || (newName.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Edit Name",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newName == null) || (newName.isBlank()));
                        writer.write("Teacher Edit Name");
                        writer.println();
                        writer.flush();
                        writer.write(newName);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            teacherEditNameButton.addActionListener(teacherEditNameListener);

            /**
             * When user clicks "Edit Email" in Teacher Settings Menu,
             * they will be prompted to input a new email.
             */
            ActionListener teacherEditEmailListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == teacherEditEmailButton) {
                        teacherSettingsMenu.setVisible(false);
                        String newEmail;
                        do {
                            newEmail = JOptionPane.showInputDialog(null, "Enter your new email.",
                                    "Edit Email", JOptionPane.QUESTION_MESSAGE);
                            if ((newEmail == null) || (newEmail.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Edit Email",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newEmail == null) || (newEmail.isBlank()));
                        writer.write("Teacher Edit Email");
                        writer.println();
                        writer.flush();
                        writer.write(newEmail);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            teacherEditEmailButton.addActionListener(teacherEditEmailListener);

            /**
             * When user clicks "Edit Password" in Teacher Settings Menu,
             * they will be prompted to input a new password.
             */
            ActionListener teacherEditPasswordListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == teacherEditPasswordButton) {
                        teacherSettingsMenu.setVisible(false);
                        String newPassword;
                        do {
                            newPassword = JOptionPane.showInputDialog(null, "Enter your new password.",
                                    "Edit Password", JOptionPane.QUESTION_MESSAGE);
                            if ((newPassword == null) || (newPassword.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Edit Password",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newPassword == null) || (newPassword.isBlank()));
                        writer.write("Teacher Edit Password");
                        writer.println();
                        writer.flush();
                        writer.write(newPassword);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            teacherEditPasswordButton.addActionListener(teacherEditPasswordListener);

            /**
             * When user clicks "Delete Account" in Teacher Settings Menu,
             * their account will be deleted and they will return to the main menu.
             */
            ActionListener teacherDeleteAccountListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == teacherDeleteAccountButton) {
                        teacherSettingsMenu.setVisible(false);
                        writer.write("Teacher Delete Account");
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            teacherDeleteAccountButton.addActionListener(teacherDeleteAccountListener);

            /**
             * When user clicks "Settings" on the Student Menu, they are showed
             * the settings that are specific to a Student.
             */
            ActionListener studentSettingsListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentSettingsButton) {
                        writer.write("Open Student Settings");
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentSettingsButton.addActionListener(studentSettingsListener);

            /**
             * When user clicks "Edit Name" in Student Settings Menu,
             * they will be prompted to input a new name.
             */
            ActionListener studentEditNameListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentEditNameButton) {
                        studentSettingsMenu.setVisible(false);
                        String newName;
                        do {
                            newName = JOptionPane.showInputDialog(null, "Enter your new name.",
                                    "Edit Name", JOptionPane.QUESTION_MESSAGE);
                            if ((newName == null) || (newName.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Name cannot be empty!", "Edit Name",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newName == null) || (newName.isBlank()));
                        writer.write("Student Edit Name");
                        writer.println();
                        writer.flush();
                        writer.write(newName);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentEditNameButton.addActionListener(studentEditNameListener);

            /**
             * When user clicks "Edit Email" in Student Settings Menu,
             * they will be prompted to input a new email.
             */
            ActionListener studentEditEmailListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentEditEmailButton) {
                        studentSettingsMenu.setVisible(false);
                        String newEmail;
                        do {
                            newEmail = JOptionPane.showInputDialog(null, "Enter your new email.",
                                    "Edit Email", JOptionPane.QUESTION_MESSAGE);
                            if ((newEmail == null) || (newEmail.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Edit Email",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newEmail == null) || (newEmail.isBlank()));
                        writer.write("Student Edit Email");
                        writer.println();
                        writer.flush();
                        writer.write(newEmail);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentEditEmailButton.addActionListener(studentEditEmailListener);

            /**
             * When user clicks "Edit Password" in Student Settings Menu,
             * they will be prompted to input a new password.
             */
            ActionListener studentEditPasswordListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentEditPasswordButton) {
                        studentSettingsMenu.setVisible(false);
                        String newPassword;
                        do {
                            newPassword = JOptionPane.showInputDialog(null, "Enter your new password.",
                                    "Edit Password", JOptionPane.QUESTION_MESSAGE);
                            if ((newPassword == null) || (newPassword.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Edit Password",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((newPassword == null) || (newPassword.isBlank()));
                        writer.write("Student Edit Password");
                        writer.println();
                        writer.flush();
                        writer.write(newPassword);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentEditPasswordButton.addActionListener(studentEditPasswordListener);

            /**
             * When user clicks "Delete Account" in Student Settings Menu,
             * their account will be deleted and they will return to the main menu.
             */
            ActionListener studentDeleteAccountListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentDeleteAccountButton) {
                        studentSettingsMenu.setVisible(false);
                        writer.write("Student Delete Account");
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentDeleteAccountButton.addActionListener(studentDeleteAccountListener);

            /**
             * When user clicks "View Account Info" in Student Settings Menu,
             * their ID, name, email, and password will be shown.
             */
            ActionListener studentViewAccountInfoListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == studentViewAccountInfoButton) {
                        studentSettingsMenu.setVisible(false);
                        writer.write("Student View Account Info");
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(userID));
                        writer.println();
                        writer.flush();
                    }
                }
            };
            studentViewAccountInfoButton.addActionListener(studentViewAccountInfoListener);

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
                switch (messageFromServer) {
                    case "Teacher Account Created Success", "Student Account Created Success" -> {
                        JOptionPane.showMessageDialog(null,
                                "Your account has been made successfully!",
                                "Create Account", JOptionPane.INFORMATION_MESSAGE);
                        mainMenu.setVisible(true);
                    }
                    case "Teacher ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a teacher with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenu.setVisible(true);
                    }
                    case "Teacher Sign In Success" -> {
                        JOptionPane.showMessageDialog(null,
                                "You have signed in successfully!",
                                "Sign In", JOptionPane.INFORMATION_MESSAGE);
                        mainMenu.setVisible(false);
                        teacherMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        teacherMenu.setVisible(true);
                    }
                    case "Teacher Sign In Failed" -> {
                        JOptionPane.showMessageDialog(null,
                                "No teacher account exists with the given ID and password.",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenu.setVisible(true);
                    }
                    case "Student ID Taken" -> {
                        JOptionPane.showMessageDialog(null,
                                "Failed to create account! There already exists a student with this ID!",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenu.setVisible(true);
                    }
                    case "Student Sign In Success" -> {
                        JOptionPane.showMessageDialog(null,
                                "You have signed in successfully!",
                                "Sign In", JOptionPane.INFORMATION_MESSAGE);
                        mainMenu.setVisible(false);
                        studentMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentMenu.setVisible(true);
                    }
                    case "Student Sign In Failed" -> {
                        JOptionPane.showMessageDialog(null,
                                "No student account exists with the given ID and password.",
                                "Create Account", JOptionPane.ERROR_MESSAGE);
                        mainMenu.setVisible(true);
                    }
                    case "Open Teacher Settings" -> {
                        teacherMenu.setVisible(false);
                        teacherSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        teacherSettingsMenu.setVisible(true);
                    }
                    case "Open Student Settings" -> {
                        studentMenu.setVisible(false);
                        studentSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentSettingsMenu.setVisible(true);
                    }
                    case "Teacher Edit Name" -> {
                        String newName = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "Hello " + newName + "! You have successfully changed your name!",
                                "Edit Name", JOptionPane.INFORMATION_MESSAGE);
                        teacherSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        teacherSettingsMenu.setVisible(true);
                    }
                    case "Student Edit Name" -> {
                        String newName = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "Hello " + newName + "! You have successfully changed your name!",
                                "Edit Name", JOptionPane.INFORMATION_MESSAGE);
                        studentSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentSettingsMenu.setVisible(true);
                    }
                    case "Teacher Edit Email" -> {
                        String newEmail = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have successfully changed your email to " + newEmail,
                                "Edit Email", JOptionPane.INFORMATION_MESSAGE);
                        teacherSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        teacherSettingsMenu.setVisible(true);
                    }
                    case "Student Edit Email" -> {
                        String newEmail = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have successfully changed your email to " + newEmail,
                                "Edit Email", JOptionPane.INFORMATION_MESSAGE);
                        studentSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentSettingsMenu.setVisible(true);
                    }
                    case "Teacher Edit Password" -> {
                        String newPassword = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have successfully changed your password to " + newPassword,
                                "Edit Password", JOptionPane.INFORMATION_MESSAGE);
                        teacherSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        teacherSettingsMenu.setVisible(true);
                    }
                    case "Student Edit Password" -> {
                        String newPassword = bfr.readLine();
                        JOptionPane.showMessageDialog(null,
                                "You have successfully changed your password to " + newPassword,
                                "Edit Password", JOptionPane.INFORMATION_MESSAGE);
                        studentSettingsMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentSettingsMenu.setVisible(true);
                    }
                    case "Teacher Delete Account" -> {
                        mainMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mainMenu.setVisible(true);
                    }
                    case "Student Delete Account" -> {
                        mainMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        mainMenu.setVisible(true);
                    }
                    case "Student View Account Info" -> {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        JPanel studentAccountInfoPanel = new JPanel();
                        Container studentAccountInfoContainer = studentAccountInfoMenu.getContentPane();
                        studentAccountInfoContainer.setLayout(new BorderLayout());
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        studentAccountInfoPanel.setSize(screenSize.width, screenSize.height);
                        JTextField studentID = new JTextField("Your ID is: " + Integer.toString(userID) + "\n");
                        JTextField studentName = new JTextField("Your name is: " + name + "\n");
                        JTextField studentEmail = new JTextField("Your email is: " + email + "\n");
                        JTextField studentPassword = new JTextField("Your password is: " + password + "\n");
                        studentAccountInfoPanel.add(studentID);
                        studentAccountInfoPanel.add(studentName);
                        studentAccountInfoPanel.add(studentEmail);
                        studentAccountInfoPanel.add(studentPassword);
                        studentAccountInfoContainer.add(studentAccountInfoPanel, BorderLayout.CENTER);
                        studentAccountInfoMenu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        studentAccountInfoMenu.setVisible(true);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
