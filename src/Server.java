import java.net.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server implements Runnable {
    private Socket socket; //Socket object
    private BufferedReader bfr; //BufferedReader object
    private PrintWriter writer; //PrintWriter object
    private static ArrayList<Socket> clients = new ArrayList<>(); //ArrayList of clients
    private static ArrayList<Teacher> teachers = new ArrayList<>(); //ArrayList of teachers
    private static ArrayList<Student> students = new ArrayList<>(); //ArrayList of students
    private static Object race = new Object(); //Object used in synchronized blocks to prevent race conditions

    public Server(Socket inputSocket) {
        this.socket = inputSocket;
        clients.add(this.socket);
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
                if (messageFromClient.equals("Create Teacher Account")) {
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
                        continue;
                    }
                    else {
                        Teacher newAccount = new Teacher(name, ID, email, password);
                        teachers.add(newAccount);
                        writer.write("Teacher Account Created Success");
                        writer.println();
                        writer.flush();
                    }
                }
                else if (messageFromClient.equals("Create Student Account")) {

                }
                else if (messageFromClient.equals("Sign In")) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
