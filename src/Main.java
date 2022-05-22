import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2361); //ServerSocket object
        /*
         * Creates an infinite loop for multiple clients to connect
         * to the server. This is necessary for a multithreaded server
         */
        while (true) {
            Socket socket = serverSocket.accept();
            Server server = new Server(socket);
            new Thread(server).start();
        }
    }
}
