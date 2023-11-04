package Server.src;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// personal imports
import Config.src.SettingsInHandler;

public class Server {
    int port;
    String exitLine;

    ServerSocket server;
    ArrayList<Socket> sockList = new ArrayList<Socket>();

    public Server() throws IOException {
        // run a settings handler
        SettingsInHandler config = new SettingsInHandler();
        port = config.getPort();
        exitLine = config.getExitLine();

        server = new ServerSocket(port);

        Thread sockOutThread = new SockOutHandler(sockList, exitLine);
        sockOutThread.start();

        while (true) {
            Socket socket = null;

            try {
                socket = server.accept();
                sockList.add(socket);

                System.out.println("New connection initiated. Client #" + sockList.indexOf(socket));

                Thread sockInThread = new SocketInHandler(socket, sockList, exitLine);
                sockInThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
