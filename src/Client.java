import java.net.*;
import java.io.*;

public class Client implements Runnable {
    private static Socket socket; //Socket object
    private static BufferedReader bfr; //BufferedReader object
    private static PrintWriter writer; //PrintWriter object

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {

    }
}
