package Config.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SettingsInHandler {
    // connection variables
    private int port;
    private String hostname;

    // run variables
    private String exitLine;

    // program variables
    private ArrayList<String> options;
    private BufferedReader fileReader;

    public SettingsInHandler() {
        // initialise options array
        options = new ArrayList<String>();

        try {
            // try to make a reader from file sv_config.txt
            fileReader = new BufferedReader(new FileReader("sv_config.txt"));

            // read from file until EOF
            String currentLine;
            while ((currentLine = fileReader.readLine()) != null) {
                String[] parseArr = currentLine.split(":"); // split line with ':', and store in array
                options.add(parseArr[1]); // add parsed option to options array
            }

            // close reader
            try {
                fileReader.close();
            } catch (IOException e) {
                // Error while closing reader
                System.out.println("Could not close reader");
            }

            // add each option to it's variable
            port = Integer.parseInt(options.get(0)); // first option in file is port
            hostname = options.get(1); // second option in file is hostname
            exitLine = options.get(2); // thirs option in file is exit line
        } catch (FileNotFoundException e) {
            // Exception for FileNotFound
            System.out.println("sv_config.txt file not found!");
            e.printStackTrace();
        } catch (IOException e) {
            // Exception for IOException from fileReader object
            System.out.println("Error while reading from sv_config");
            e.printStackTrace();
        }
    }

    // getters
    // port
    public int getPort() {
        return this.port;
    }

    public String getHostName() {
        return this.hostname;
    }

    public String getExitLine() {
        return this.exitLine;
    }
}
