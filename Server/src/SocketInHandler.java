package Server.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class SocketInHandler extends Thread {
    private Socket socket;
    private ArrayList<Socket> sockList;
    private String exitLine;

    public SocketInHandler(Socket socket, ArrayList<Socket> sockList, String exitLine) {
        this.socket = socket;
        this.sockList = sockList;
        this.exitLine = exitLine;
    }

    @Override
    public void run() {
        BufferedReader reader = null;

        String line = "";

        while (!line.equals(exitLine)) {
            try {
                reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

                line = reader.readLine();
                System.out.println("Client #" + this.sockList.indexOf(socket) + ": " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Client #" + this.sockList.indexOf(socket) + " disconnected.");
        this.sockList.remove(socket);

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
