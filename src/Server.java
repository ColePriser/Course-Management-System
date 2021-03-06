import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements Runnable, Serializable {
    private Socket socket; //Socket object
    private BufferedReader bfr; //BufferedReader object
    private PrintWriter writer; //PrintWriter object
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream; //OutputStream object

    private static ArrayList<Socket> clients = new ArrayList<>(); //ArrayList of Clients
    private static List<Teacher> teachers = new CopyOnWriteArrayList<>(); //ArrayList of Teachers
    private static List<Student> students = new CopyOnWriteArrayList<>(); //ArrayList of Students
    private static List<Course> courses = new CopyOnWriteArrayList<>(); //ArrayList of Courses

    public static Object race = new Object(); //Object used in synchronized blocks to prevent race conditions

    public Server(Socket inputSocket) {
        this.socket = inputSocket;
        clients.add(inputSocket);
        try {
            bfr = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
            writer = new PrintWriter(inputSocket.getOutputStream());
            //objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            //objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                String messageFromClient = bfr.readLine();
                switch (messageFromClient) {
                    case "Close": {
                        /**
                         * Since the client closes the Course Management System
                         * and wants to end the program, they are removed from the list of clients.
                         */
                        synchronized (race) {
                            for (int x = 0; x < clients.size(); x++) {
                                if (clients.get(x) == this.socket) {
                                    clients.remove(x);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Create Teacher Account": {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    taken = true;
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (taken) {
                            writer.write("Teacher ID Taken");
                            writer.println();
                            writer.flush();
                        } else {
                            Teacher newAccount = new Teacher(name, ID, email, password);
                            teachers.add(newAccount);
                            writer.write("Teacher Account Created Success");
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
                        break;
                    }
                    case "Create Student Account": {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        synchronized (race) {
                            for (Student student : students) {
                                if (student.getID() == ID) {
                                    taken = true;
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (taken) {
                            writer.write("Student ID Taken");
                            writer.println();
                            writer.flush();
                        } else {
                            Student newAccount = new Student(name, ID, email, password);
                            students.add(newAccount);
                            writer.write("Student Account Created Success");
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
                        break;
                    }
                    case "Log In Menu Teacher Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        boolean teacherFound = false;
                        String teacherName = "";
                        String teacherEmail = "";
                        String teacherPassword = "";
                        String teacherID = "";
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    if (teachers.get(x).getPassword().equals(password)) {
                                        teacherFound = true;
                                        teacherID = Integer.toString(teachers.get(x).getID());
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (teacherFound) {
                            writer.write("Log In Teacher Success");
                            writer.println();
                            writer.flush();
                            writer.write(teacherName);
                            writer.println();
                            writer.flush();
                            writer.write(teacherEmail);
                            writer.println();
                            writer.flush();
                            writer.write(teacherPassword);
                            writer.println();
                            writer.flush();
                            writer.write(teacherID);
                            writer.println();
                            writer.flush();
                        }
                        else {
                            writer.write("Log In Failure");
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    case "Log In Menu Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        boolean studentFound = false;
                        String studentName = "";
                        String studentEmail = "";
                        String studentPassword = "";
                        String studentID = "";
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    if (students.get(x).getPassword().equals(password)) {
                                        studentFound = true;
                                        studentID = Integer.toString(students.get(x).getID());
                                        studentName = students.get(x).getName();
                                        studentEmail = students.get(x).getEmail();
                                        studentPassword = students.get(x).getPassword();
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (studentFound) {
                            writer.write("Log In Student Success");
                            writer.println();
                            writer.flush();
                            writer.write(studentName);
                            writer.println();
                            writer.flush();
                            writer.write(studentEmail);
                            writer.println();
                            writer.flush();
                            writer.write(studentPassword);
                            writer.println();
                            writer.flush();
                            writer.write(studentID);
                            writer.println();
                            writer.flush();
                        }
                        else {
                            writer.write("Log In Failure");
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    case "Delete Teacher Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.remove(x);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Delete Teacher Account User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Delete Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x< courses.size(); x++) {
                                for (int y = 0; y < courses.get(x).getEnrolledStudents().size(); y++) {
                                    if (courses.get(x).getEnrolledStudents().get(y).getID() == ID) {
                                        courses.get(x).getEnrolledStudents().remove(y);
                                        break;
                                    }
                                }
                            }
                            for (int z = 0; z < students.size(); z++) {
                                if (students.get(z).getID() == ID) {
                                    students.remove(z);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Delete Student Account User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Edit Name": {
                        String newName = bfr.readLine();
                        String email = "";
                        String password = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.get(x).setName(newName);
                                    email = teachers.get(x).getEmail();
                                    password = teachers.get(x).getPassword();
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(newName);
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
                        break;
                    }
                    case "Student Edit Name": {
                        String newName = bfr.readLine();
                        String email = "";
                        String password = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    email = students.get(x).getEmail();
                                    password = students.get(x).getPassword();
                                    students.get(x).setName(newName);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(newName);
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
                        break;
                    }
                    case "Teacher Edit Email": {
                        String newEmail = bfr.readLine();
                        String name = "";
                        String password = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    name = teachers.get(x).getName();
                                    password = teachers.get(x).getPassword();
                                    teachers.get(x).setEmail(newEmail);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(name);
                        writer.println();
                        writer.flush();
                        writer.write(newEmail);
                        writer.println();
                        writer.flush();
                        writer.write(password);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Edit Email": {
                        String newEmail = bfr.readLine();
                        String name = "";
                        String password = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    name = students.get(x).getName();
                                    password = students.get(x).getPassword();
                                    students.get(x).setEmail(newEmail);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(name);
                        writer.println();
                        writer.flush();
                        writer.write(newEmail);
                        writer.println();
                        writer.flush();
                        writer.write(password);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Edit Password": {
                        String newPassword = bfr.readLine();
                        String email = "";
                        String name = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    name = teachers.get(x).getName();
                                    email = teachers.get(x).getEmail();
                                    teachers.get(x).setPassword(newPassword);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(name);
                        writer.println();
                        writer.flush();
                        writer.write(email);
                        writer.println();
                        writer.flush();
                        writer.write(newPassword);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Edit Password": {
                        String newPassword = bfr.readLine();
                        String email = "";
                        String name = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.get(x).setPassword(newPassword);
                                    name = students.get(x).getName();
                                    email = students.get(x).getEmail();
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("User Info Edit");
                        writer.println();
                        writer.flush();
                        writer.write(name);
                        writer.println();
                        writer.flush();
                        writer.write(email);
                        writer.println();
                        writer.flush();
                        writer.write(newPassword);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(ID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Create New Course Submit": {
                        String name = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String coursePassword = bfr.readLine();
                        int teacherID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        boolean tooMany = false;
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    taken = true;
                                    break;
                                }
                            }
                            if (!taken) {
                                for (int y = 0; y < teachers.size(); y++) {
                                    if (teachers.get(y).getID() == teacherID) {
                                        if (teachers.get(y).getNumEnrolledCourses() >= 8) {
                                            tooMany = true;
                                            break;
                                        } else {
                                            Course newCourse = new Course(name, courseID, coursePassword, teachers.get(y));
                                            courses.add(newCourse);
                                            teachers.get(y).incrementNumEnrolledCourses();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (taken) {
                            writer.write("Course ID Taken");
                            writer.println();
                            writer.flush();
                        } else if (tooMany) {
                            writer.write("Teacher Too Many Courses");
                            writer.println();
                            writer.flush();
                        } else {
                            writer.write("Course Created Success");
                            writer.println();
                            writer.flush();
                            writer.write(name);
                            writer.println();
                            writer.flush();
                            writer.write(Integer.toString(courseID));
                            writer.println();
                            writer.flush();
                            writer.write(coursePassword);
                            writer.println();
                            writer.flush();
                            writer.write(Integer.toString(teacherID));
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    case "Student Enroll In New Course": {
                        int courseID = Integer.parseInt(bfr.readLine());
                        String coursePassword = bfr.readLine();
                        int userID = Integer.parseInt(bfr.readLine());
                        boolean alreadyEnrolled = false;
                        boolean success = false;
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    if (courses.get(x).getCoursePassword().equals(coursePassword)) {
                                        for (int y = 0; y < courses.get(x).getEnrolledStudents().size(); y++) {
                                            if (courses.get(x).getEnrolledStudents().get(y).getID() == userID) {
                                                alreadyEnrolled = true;
                                                writer.write("Already Enrolled In Course");
                                                writer.println();
                                                writer.flush();
                                                break;
                                            }
                                        }
                                        if (!alreadyEnrolled) {
                                            success = true;
                                            writer.write("Enroll In Course Success");
                                            writer.println();
                                            writer.flush();
                                            writer.write(courses.get(x).getCourseName());
                                            writer.println();
                                            writer.flush();
                                            writer.write(Integer.toString(courseID));
                                            writer.println();
                                            writer.flush();
                                            writer.write(coursePassword);
                                            writer.println();
                                            writer.flush();
                                            writer.write(courses.get(x).getTeacher().getName());
                                            writer.println();
                                            writer.flush();
                                            writer.write(Integer.toString(courses.get(x).getTeacher().getID()));
                                            writer.println();
                                            writer.flush();
                                            writer.write(courses.get(x).getTeacher().getEmail());
                                            writer.println();
                                            writer.flush();
                                            writer.write(courses.get(x).getTeacher().getPassword());
                                            writer.println();
                                            writer.flush();
                                            for (int z = 0; z < students.size(); z++) {
                                                if (students.get(z).getID() == userID) {
                                                    courses.get(x).getEnrolledStudents().add(students.get(z));
                                                    students.get(z).incrementNumEnrolledCourses();
                                                    writer.write(students.get(z).getName());
                                                    writer.println();
                                                    writer.flush();
                                                    writer.write(Integer.toString(students.get(z).getID()));
                                                    writer.println();
                                                    writer.flush();
                                                    writer.write(students.get(z).getEmail());
                                                    writer.println();
                                                    writer.flush();
                                                    writer.write(students.get(z).getPassword());
                                                    writer.println();
                                                    writer.flush();
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        if (!success && !alreadyEnrolled) {
                            writer.write("Enroll In Course Error");
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    case "Create New Course Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Create New Course Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Create Account Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Create Account Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Enroll Course Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Enroll Course Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Log In Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Log In Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Settings Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Settings Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Settings Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Settings Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher View Courses Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher View Courses Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Main Log In": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Main Menu Log In");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Settings": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Settings User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Create New Course Button": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Create New Course Button User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher View Courses Button": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher View Courses Button User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Main Menu Create New Account": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Main Menu Create New Account User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Settings": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Settings User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Menu Enroll In Course": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Menu Enroll In Course User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Edit Courses Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Edit Courses Menu Back");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Edit Course Button": {
                        int courseID = Integer.parseInt(bfr.readLine());
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    writer.write("Teacher Edit Course Button User");
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getCourseName());
                                    writer.println();
                                    writer.flush();
                                    writer.write(Integer.toString(courseID));
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getTeacher().getName());
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getCoursePassword());
                                    writer.println();
                                    writer.flush();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Edit Course Name": {
                        String newName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String teacherName = "";
                        String coursePassword = "";
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    courses.get(x).setCourseName(newName);
                                    teacherName = courses.get(x).getTeacher().getName();
                                    coursePassword = courses.get(x).getCoursePassword();
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Edit Course Name or Password User");
                        writer.println();
                        writer.flush();
                        writer.write(newName);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(courseID));
                        writer.println();
                        writer.flush();
                        writer.write(teacherName);
                        writer.println();
                        writer.flush();
                        writer.write(coursePassword);
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Edit Course Password": {
                        String newPass = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String courseName = "";
                        String teacherName = "";
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    courses.get(x).setCoursePassword(newPass);
                                    courseName = courses.get(x).getCourseName();
                                    teacherName = courses.get(x).getTeacher().getName();
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Edit Course Name or Password User");
                        writer.println();
                        writer.flush();
                        writer.write(courseName);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(courseID));
                        writer.println();
                        writer.flush();
                        writer.write(teacherName);
                        writer.println();
                        writer.flush();
                        writer.write(newPass);
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Delete Course Button": {
                        int courseID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    courses.remove(x);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Delete Course User");
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(courseID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student View Enrolled Courses": {
                        int studentID = Integer.parseInt(bfr.readLine());
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student View Enrolled Courses User");
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(studentID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student View Enrolled Courses Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student View Enrolled Courses Back User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Open Course Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Open Course Back User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Open Course": {
                        int courseID = Integer.parseInt(bfr.readLine());
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    writer.write("Student Open Course User");
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getCourseName());
                                    writer.println();
                                    writer.flush();
                                    writer.write(Integer.toString(courseID));
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getTeacher().getName());
                                    writer.println();
                                    writer.flush();
                                    writer.write(courses.get(x).getCoursePassword());
                                    writer.println();
                                    writer.flush();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Unenroll In Course": {
                        int courseID = Integer.parseInt(bfr.readLine());
                        int studentID = Integer.parseInt(bfr.readLine());
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    for (int y = 0; y < courses.get(x).getEnrolledStudents().size(); y++) {
                                        if (courses.get(x).getEnrolledStudents().get(y).getID() == studentID) {
                                            courses.get(x).getEnrolledStudents().remove(y);
                                            writer.write("Unenroll In Course User");
                                            writer.println();
                                            writer.flush();
                                            writer.write(Integer.toString(courseID));
                                            writer.println();
                                            writer.flush();
                                            writer.write(Integer.toString(studentID));
                                            writer.println();
                                            writer.flush();
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "Teacher View Enrolled Students": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher View Enrolled Students User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher View Enrolled Students Back": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher View Enrolled Students Back User");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "meow": {
                        int id = Integer.parseInt(bfr.readLine());
                        System.out.println(id);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
