package Server.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    int port = 3000;
    String exitLine = "exit()";
    String closeAllLine = "EXIT()";

    ServerSocket server;
    ArrayList<Socket> sockList = new ArrayList<Socket>();

    public Server() throws IOException {
        server = new ServerSocket(port);

        while (true) {
            Socket socket = null;

            try {
                socket = server.accept();
                sockList.add(socket);

                System.out.println("New connection initiated. Client #" + sockList.indexOf(socket));

                Thread sockThread = new SocketHandler(socket, sockList.indexOf(socket));
                sockThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class SocketHandler extends Thread {
        Socket socket;
        int sockNum;

        public SocketHandler(Socket socket, int sockNum) {
            this.socket = socket;
            this.sockNum = sockNum;
        }

        @Override
        public void run() {
            String line = "";

            while (!line.equals(exitLine)) {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    line = reader.readLine();
                    System.out.println("Client #" + sockNum + ": " + line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
