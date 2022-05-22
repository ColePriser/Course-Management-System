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

    public static void main(String[] args) {
        Thread client = new Thread(new Client());
        client.start();
        try {
            socket = new Socket("localhost", 2361);
            writer = new PrintWriter(socket.getOutputStream());
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            /*
             * Creating socket and setting up reading and writing capabilities
             * to communicate with the server.
             */
            JPanel mainMenuPanel = new JPanel();
            JFrame mainMenu = new JFrame("Course Management System Main Menu");
            Container mainMenuContainer = mainMenu.getContentPane();
            mainMenuContainer.setLayout(new BorderLayout());
            mainMenu.setSize(300, 200);
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
             * When user clicks "Create Account", they are prompted to
             * input their account type (Student or Teacher), name,
             * email, password, and ID. This information is sent to the server.
             */
            ActionListener createAccountListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == createAccountButton) {
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
                        boolean numError = false;
                        do {
                            IDError = false;
                            IDLengthError = false;
                            IDStartNumError = false;
                            try {
                                ID = Integer.parseInt(JOptionPane.showInputDialog(null, "What is your student ID?",
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
                        writer.write(ID);
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
                        int ID = 0;
                        boolean IDError = true;
                        boolean IDLengthError = true;
                        boolean IDStartNumError = true;
                        boolean numError = false;
                        do {
                            IDError = false;
                            IDLengthError = false;
                            IDStartNumError = false;
                            try {
                                ID = Integer.parseInt(JOptionPane.showInputDialog(null, "What is your student ID?",
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
                        } while (IDError || IDLengthError);
                        String password;
                        do {
                            password = JOptionPane.showInputDialog(null, "Enter your desired password.",
                                    "Create Account", JOptionPane.QUESTION_MESSAGE);
                            if ((password == null) || (password.isBlank())) {
                                JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Create Account",
                                        JOptionPane.ERROR_MESSAGE);

                            }
                        } while ((password == null) || (password.isBlank()));
                        writer.write("Sign In");
                        writer.println();
                        writer.flush();
                        writer.write(ID);
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
                String messageFromServer = "temp";
                try {
                    messageFromServer = bfr.readLine();
                } catch (IOException ex) {
                    continue;
                }
                System.out.println(messageFromServer);
                if (messageFromServer.equals("Teacher ID Taken")) {
                    JOptionPane.showMessageDialog(null,
                            "Failed to create account! There already exists a teacher with this ID!",
                            "Create Account",
                            JOptionPane.ERROR_MESSAGE);
                } else if (messageFromServer.equals("Teacher Account Created Success")) {
                    JOptionPane.showMessageDialog(null,
                            "Your account has been made successfully!",
                            "Create Account",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
