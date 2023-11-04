package Client.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SockInHandler extends Thread {
    private Socket socket;
    private String exitLine;

    public SockInHandler(Socket socket, String exitLine) throws IOException {
        this.socket = socket;
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
                System.out.println("SockInHandler try catch");
            }
        }

        System.exit(0);
    }
}
