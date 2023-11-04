package Client.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SockOutHandler {
    private Socket socket;
    private String exitLine;

    private Scanner sysIn = new Scanner(System.in);

    public SockOutHandler(Socket socket, String exitLine) {
        this.socket = socket;
        this.exitLine = exitLine;
    }

    public void outputStream() {
        PrintWriter writer = null;

        String line = "";
        while (!line.equals(this.exitLine)) {
            try {
                writer = new PrintWriter(this.socket.getOutputStream(), true);

                line = sysIn.nextLine();
                writer.println(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sysIn.close();
        writer.close();
        System.exit(0);
    }
}
