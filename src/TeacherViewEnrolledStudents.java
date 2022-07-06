import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class TeacherViewEnrolledStudents extends JFrame {
    private JPanel teacherViewEnrolledStudentsPanel;
    private JLabel tempLabel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton backButton;

    private int userID;
    private String userName;
    private String userEmail;
    private String userPassword;
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<JButton> buttons = new ArrayList<>();

    public TeacherViewEnrolledStudents() {
        setTitle("View Enrolled Students");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        ImageIcon logoIcon = new ImageIcon("book.png");
        Image logo = logoIcon.getImage();
        setIconImage(logo);
        setVisible(false);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        buttons.add(button8);
        buttons.add(button9);
        buttons.add(button10);
        buttons.add(button11);
        buttons.add(button12);
        buttons.add(button13);
        buttons.add(button14);
        buttons.add(button15);
        buttons.add(button16);
        buttons.add(button17);
        buttons.add(button18);
        buttons.add(button19);
        buttons.add(button20);
        setContentPane(teacherViewEnrolledStudentsPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                User.writer.write("Close");
                User.writer.println();
                User.writer.flush();
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(0).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(1).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(2).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(3).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(4).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(5).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(6).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(7).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(8).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(9).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(10).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(11).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(12).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(13).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(14).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(15).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(16).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(17).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(18).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        button20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Student Button");
                User.writer.println();
                User.writer.flush();
                User.writer.write(Integer.toString(students.get(19).getID()));
                User.writer.println();
                User.writer.flush();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.writer.write("Teacher View Enrolled Students Back");
                User.writer.println();
                User.writer.flush();
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

    public void resetStudentList() {
        for (int x = 0; x < students.size(); x++) {
            buttons.get(x).setText(students.get(x).getName());
            buttons.get(x).setVisible(true);
        }
        for (int y = students.size(); y < buttons.size(); y++) {
            buttons.get(y).setVisible(false);
        }
        if (students.size() == 0) {
            tempLabel.setText("You currently have no students enrolled in your course!");
        }
        else {
            tempLabel.setText("");
        }
    }

    public void addStudent(String name, int ID, String email, String password) {
        students.add(new Student(name, ID, email, password));
    }

    public void removeStudent(int userID) {
        for (int x = 0; x < students.size(); x++) {
            if (students.get(x).getID() == userID) {
                students.remove(x);
                break;
            }
        }
    }
}
