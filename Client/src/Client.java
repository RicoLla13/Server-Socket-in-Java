package Client.src;

import java.io.IOException;
import java.net.Socket;

// personal imports
import Config.src.SettingsInHandler;

// Client instance class
public class Client {
    // connection variables
    private int port; // Server port
    private String hostname; // Server hostname/ip address

    // run variables
    private String exitLine;

    public Client() {
        // run a settings handler
        SettingsInHandler config = new SettingsInHandler();
        port = config.getPort();
        hostname = config.getHostName();
        exitLine = config.getExitLine();

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
