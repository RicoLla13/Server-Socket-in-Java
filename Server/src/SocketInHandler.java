package Server.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketInHandler extends Thread {
    private Socket socket;
    private int sockNum;
    private String exitLine;

    public SocketInHandler(Socket socket, int sockNum, String exitLine) throws IOException {
        this.socket = socket;
        this.sockNum = sockNum;
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
                System.out.println("Server: " + line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Client #" + sockNum + " disconnected.");

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
