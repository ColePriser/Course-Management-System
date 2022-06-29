import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements Runnable {
    public Socket socket; //Socket object
    public BufferedReader bfr; //BufferedReader object
    public PrintWriter writer; //PrintWriter object

    private static ArrayList<Socket> clients = new ArrayList<Socket>(); //ArrayList of clients
    private static List<Teacher> teachers = new CopyOnWriteArrayList<>(); //ArrayList of teachers
    private static List<Student> students = new CopyOnWriteArrayList<>(); //ArrayList of students
    private static List<Course> courses = new CopyOnWriteArrayList<>();


    public static final Object race = new Object(); //Object used in synchronized blocks to prevent race conditions

    public Server(Socket inputSocket) {
        this.socket = inputSocket;
        clients.add(inputSocket);
        try {
            this.bfr = new BufferedReader(new InputStreamReader(inputSocket.getInputStream()));
            this.writer = new PrintWriter(inputSocket.getOutputStream());
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
                    case "Create Course": {
                        String name = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        String coursePassword = bfr.readLine();
                        int teacherID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        writer = new PrintWriter(this.socket.getOutputStream());
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    taken = true;
                                    writer.write("Course ID Taken");
                                    writer.println();
                                    writer.flush();
                                    break;
                                }
                            }
                            if (!taken) {
                                for (int y = 0; y < teachers.size(); y++) {
                                    if (teachers.get(y).getID() == teacherID) {
                                        if (teachers.get(y).getNumEnrolledCourses() >= 8) {
                                            writer.write("Teacher Too Many Courses");
                                            writer.println();
                                            writer.flush();
                                            break;
                                        } else {
                                            Course newCourse = new Course(name, courseID, coursePassword, teachers.get(y));
                                            courses.add(newCourse);
                                            teachers.get(y).incrementNumEnrolledCourses();
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
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    case "Enroll In Course": {
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
                                                break;
                                            }
                                        }
                                        if (!alreadyEnrolled) {
                                            success = true;
                                            for (int z = 0; z < students.size(); z++) {
                                                if (students.get(z).getID() == userID) {
                                                    courses.get(x).getEnrolledStudents().add(students.get(z));
                                                    students.get(z).incrementNumEnrolledCourses();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (alreadyEnrolled) {
                            writer.write("Already Enrolled In Course");
                            writer.println();
                            writer.flush();
                            break;
                        } else if (success) {
                            writer.write("Enroll In Course Success");
                            writer.println();
                            writer.flush();
                            break;
                        } else {
                            writer.write("Enroll In Course Error");
                            writer.println();
                            writer.flush();
                            break;
                        }
                    }
                    case "Reset Course List": {
                        String num = bfr.readLine();
                        System.out.println(num);
                        for (int x = 0; x < courses.size(); x++) {
                            System.out.println(courses.get(x).getCourseName());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Course> getCourses(int teacherID) {
        ArrayList<Course> tempCourse = new ArrayList<>();
        for (int x = 0; x < courses.size(); x++) {
            if (courses.get(x).getTeacher().getID() == teacherID) {
                tempCourse.add(courses.get(x));
                System.out.println(courses.get(x).getCourseName());
            }
        }
        return tempCourse;
    }
}
