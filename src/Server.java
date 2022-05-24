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
                    case "Create Teacher Account": {
                        String name = bfr.readLine();
                        String email = bfr.readLine();
                        String password = bfr.readLine();
                        int ID = Integer.parseInt(bfr.readLine());
                        boolean taken = false;
                        synchronized (race) {
                            for (Teacher teacher : teachers) {
                                if (teacher.getID() == ID) {
                                    taken = true;
                                    break;
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (taken) {
                            writer.write("Teacher ID Taken");
                        } else {
                            Teacher newAccount = new Teacher(name, ID, email, password);
                            teachers.add(newAccount);
                            writer.write("Teacher Account Created Success");
                        }
                        writer.println();
                        writer.flush();
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
                        } else {
                            Student newAccount = new Student(name, ID, email, password);
                            students.add(newAccount);
                            writer.write("Student Account Created Success");
                        }
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Sign In To Teacher Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        boolean accountFound = false;
                        synchronized (race) {
                            for (Teacher teacher : teachers) {
                                if (teacher.getID() == ID) {
                                    if (teacher.getPassword().equals(password)) {
                                        accountFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (accountFound) {
                            writer.write("Teacher Sign In Success");
                        } else {
                            writer.write("Teacher Sign In Failed");
                        }
                        writer.println();
                        writer.flush();
                        break;
                    }
                    case "Sign In To Student Account": {
                        int ID = Integer.parseInt(bfr.readLine());
                        String password = bfr.readLine();
                        boolean accountFound = false;
                        synchronized (race) {
                            for (Student student : students) {
                                if (student.getID() == ID) {
                                    if (student.getPassword().equals(password)) {
                                        accountFound = true;
                                        break;
                                    }
                                }
                            }
                        }
                        writer = new PrintWriter(this.socket.getOutputStream());
                        if (accountFound) {
                            writer.write("Student Sign In Success");
                        } else {
                            writer.write("Student Sign In Failed");
                        }
                        writer.println();
                        writer.flush();
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
