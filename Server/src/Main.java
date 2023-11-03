package Server.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 3000;
        String exitLine = "exit";

        ServerSocket server;
        Socket client;

        try {
            server = new ServerSocket(port);

            client = server.accept();

            InputStreamReader clInStr = new InputStreamReader(client.getInputStream());
            OutputStreamWriter clOutStr = new OutputStreamWriter(client.getOutputStream());

            BufferedReader clReader = new BufferedReader(clInStr);
            PrintWriter clWriter = new PrintWriter(clOutStr, true);

            String line1 = "Client connected succesfully!\n";
            String line2 = "Type exit to close connection.";

            String line = line1 + line2;
            clWriter.println(line);
            System.out.println(line);

            while (!line.equals(exitLine)) {
                line = clReader.readLine();
                System.out.println("Client: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
