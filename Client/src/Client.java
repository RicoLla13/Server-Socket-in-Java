package Client.src;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private int port = 3000;
    private String ip = "localhost";
    private String exitLine = "exit()";

    public Client() throws IOException {
        Socket socket = new Socket(ip, port);

        Thread sockInThread = new SockInHandler(socket, exitLine);
        sockInThread.start();

        Thread sockOutThread = new SockOutHandler(socket, exitLine);
        sockOutThread.start();
    }
}
