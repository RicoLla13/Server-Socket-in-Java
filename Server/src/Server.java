package Server.src;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

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

                Thread sockThread = new SocketInHandler(socket, sockList.indexOf(socket), exitLine);
                sockThread.start();

                Thread inThread = new InputHandler();
                inThread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class InputHandler extends Thread {
        @Override
        public void run() {
            String line = "";
            Scanner sysIn = new Scanner(System.in);

            while (!line.equals(closeAllLine)) {
                line = sysIn.nextLine();
            }

            sysIn.close();
            try {
                closeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeAll() throws IOException {
        for (int i = 0; i < sockList.size(); i++) {
            Socket socket = sockList.get(i);

            try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println(exitLine);
            } catch (IOException e) {
                e.printStackTrace();
            }

            socket.close();
        }
        sockList.clear();

        System.exit(1);
    }
}
