package Server.src;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class SockOutHandler extends Thread {
    private String exitLine;
    private ArrayList<Socket> sockList;

    private Scanner sysIn = new Scanner(System.in);

    public SockOutHandler(ArrayList<Socket> sockList, String exitLine) {
        this.sockList = sockList;
        this.exitLine = exitLine;
    }

    @Override
    public void run() {
        PrintWriter writer = null;

        String line = "";
        while (!line.equals(this.exitLine)) {
            line = sysIn.nextLine();

            for (int i = 0; i < this.sockList.size(); i++) {
                try {
                    Socket socket = this.sockList.get(i);

                    writer = new PrintWriter(socket.getOutputStream(), true);
                    writer.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.exit(0);
    }
}
