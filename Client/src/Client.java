package Client.src;

import java.io.IOException;
import java.net.Socket;

// Client instance class
public class Client {
    // connection variables
    private int port = 3000; // Server port
    private String hostname = "localhost"; // Server hostname/ip address

    // program variables
    private String exitLine = "exit()";

    public Client() {
        try {
            Socket socket = new Socket(hostname, port);

            Thread sockInThread = new SockInHandler(socket, exitLine);
            sockInThread.start();

            SockOutHandler sockOutStream = new SockOutHandler(socket, exitLine);
            sockOutStream.outputStream();
        } catch (IOException e) {
            System.out.println("Try catch Client");
        }
    }
}
