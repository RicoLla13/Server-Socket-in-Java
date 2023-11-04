package Client.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SockOutHandler extends Thread {
    private Socket socket;
    private String exitLine;

    private Scanner sysIn = new Scanner(System.in);

    public SockOutHandler(Socket socket, String exitLine) throws IOException {
        this.socket = socket;
        this.exitLine = exitLine;
    }

    @Override
    public void run() {
        String line = "";
        while (!line.equals(exitLine)) {
            try {
                PrintWriter writer = new PrintWriter(this.socket.getOutputStream(), true);

                line = sysIn.nextLine();
                writer.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sysIn.close();

        System.exit(0);
    }
}
