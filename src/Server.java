import java.net.*;
import java.io.*;
import java.util.*;

public class Server implements Runnable {
    private Socket socket; //Socket object
    private BufferedReader bfr; //BufferedReader object
    private PrintWriter writer; //PrintWriter object
    private static ArrayList<Socket> clients = new ArrayList<>(); //ArrayList of clients
    private static ArrayList<Teacher> teachers = new ArrayList<>(); //ArrayList of teachers
    private static ArrayList<Student> students = new ArrayList<>(); //ArrayList of students
    private static ArrayList<Course> courses = new ArrayList<>();
    private static final Object race = new Object(); //Object used in synchronized blocks to prevent race conditions

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
                        }
                        break;
                    }
                    case "Log In Menu Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        /**
                         * Check to see if a student with given ID and password exists.
                         */
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
                                        studentName = students.get(x).getName();
                                        studentEmail = students.get(x).getEmail();
                                        studentPassword = students.get(x).getPassword();
                                        studentID = Integer.toString(students.get(x).getID());
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
                    case "Log In Menu Teacher Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        /**
                         * Check to see if a student with given ID and password exists.
                         */
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
                                        teacherName = teachers.get(x).getName();
                                        teacherEmail = teachers.get(x).getEmail();
                                        teacherPassword = teachers.get(x).getPassword();
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
                    case "Delete Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.remove(x);
                                    break;
                                }
                            }
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
                    case "Teacher Edit Name": {
                        String newName = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.get(x).setName(newName);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Student Edit Name": {
                        String newName = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.get(x).setName(newName);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Teacher Edit Email": {
                        String newEmail = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.get(x).setEmail(newEmail);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Student Edit Email": {
                        String newEmail = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.get(x).setEmail(newEmail);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Teacher Edit Password": {
                        String newPassword = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.get(x).setPassword(newPassword);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Student Edit Password": {
                        String newPassword = bfr.readLine();
                        String temp = "";
                        int ID = Integer.parseInt(bfr.readLine());
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.get(x).setPassword(newPassword);
                                    temp = students.get(x).getPassword();
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "Create Course": {
                        String name = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        int teacherID = Integer.parseInt(bfr.readLine());
                        Teacher tempTeacher = null;
                        boolean taken = false;
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    taken = true;
                                    break;
                                }
                            }
                            for (int y = 0; y < teachers.size(); y++) {
                                if (teachers.get(y).getID() == teacherID) {
                                    tempTeacher = teachers.get(y);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (taken) {
                            writer.write("Course ID Taken");
                            writer.println();
                            writer.flush();
                        } else {
                            Course newCourse = new Course(name, courseID, tempTeacher);
                            courses.add(newCourse);
                            writer.write("Course Created Success");
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    /*
                    case "Sign In To Teacher Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        String name = "";
                        String email = "";
                        boolean accountFound = false;
                        synchronized (race) {
                            for (Teacher teacher : teachers) {
                                if (teacher.getID() == ID) {
                                    if (teacher.getPassword().equals(password)) {
                                        name = teacher.getName();
                                        email = teacher.getEmail();
                                        accountFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (accountFound) {
                            writer.write("Teacher Sign In Success");
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
                        } else {
                            writer.write("Teacher Sign In Failed");
                            writer.println();
                            writer.flush();
                        }
                        break;
                    }
                    case "Sign In To Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        String name = "";
                        String email = "";
                        boolean accountFound = false;
                        synchronized (race) {
                            for (Student student : students) {
                                if (student.getID() == ID) {
                                    if (student.getPassword().equals(password)) {
                                        name = student.getName();
                                        email = student.getEmail();
                                        accountFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (accountFound) {
                            writer.write("Student Sign In Success");
                            writer.println();
                            writer.flush();
                            writer.write(name);
                            writer.println();
                            writer.flush();
                            writer.write(email);
                            writer.println();
                            writer.flush();
                            writer.write(password);
                        } else {
                            writer.write("Student Sign In Failed");
                        }
                        writer.println();
                        writer.flush();
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Create New Course": {
                        String courseName = bfr.readLine();
                        int courseID = Integer.parseInt(bfr.readLine());
                        int teacherID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        synchronized (race) {
                            for (int x = 0; x < courses.size(); x++) {
                                if (courses.get(x).getCourseID() == courseID) {
                                    taken = true;
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (taken) {
                            writer.write("Course ID Taken");
                        } else {
                            Course newCourse = new Course(courseName, courseID);
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == teacherID) {
                                    teachers.get(x).addCourse(newCourse);
                                    courses.add(newCourse);
                                }
                            }
                            writer.write("New Course Created");
                        }
                        writer.println();
                        writer.flush();
                        writer.write(courseName);
                        writer.println();
                        writer.flush();
                        writer.write(Integer.toString(courseID));
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Open Teacher Settings": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Open Teacher Settings");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Open Student Settings": {
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Open Student Settings");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Teacher Delete Account": {
                        String stringID = bfr.readLine();
                        int ID = Integer.parseInt(stringID);
                        synchronized (race) {
                            for (int x = 0; x < teachers.size(); x++) {
                                if (teachers.get(x).getID() == ID) {
                                    teachers.remove(x);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Teacher Delete Account");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student Delete Account": {
                        String stringID = bfr.readLine();
                        int ID = Integer.parseInt(stringID);
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    students.remove(x);
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student Delete Account");
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Student View Account Info": {
                        String stringID = bfr.readLine();
                        int ID = Integer.parseInt(stringID);
                        String name = "";
                        String email = "";
                        String password = "";
                        synchronized (race) {
                            for (int x = 0; x < students.size(); x++) {
                                if (students.get(x).getID() == ID) {
                                    name = students.get(x).getName();
                                    email = students.get(x).getEmail();
                                    password = students.get(x).getPassword();
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        writer.write("Student View Account Info");
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
                        break;
                    }*/
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
