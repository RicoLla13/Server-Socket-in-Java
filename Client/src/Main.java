package Client.src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int port = 3000;
        String ip = "localhost";
        String exitLine = "exit";

        Socket client;

        try {
            client = new Socket(ip, port);

            InputStreamReader clInStr = new InputStreamReader(client.getInputStream());
            OutputStreamWriter clOutStr = new OutputStreamWriter(client.getOutputStream());

            BufferedReader clReader = new BufferedReader(clInStr);
            PrintWriter clWriter = new PrintWriter(clOutStr, true);

            String line = clReader.readLine() + "\n";
            line = line + clReader.readLine();
            System.out.println(line);

            Scanner sysIn = new Scanner(System.in);
            while (!line.equals(exitLine)) {
                line = sysIn.nextLine();
                clWriter.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
