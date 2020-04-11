package javarcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Scanner;
import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;

/**
 *
 * @author Petar Kresoja
 */
public class JavaRcon {

    private static Rcon rcon = null;

    public static void main(String[] args) {
        System.out.println("JavaRcon started...");
        System.out.println("To exit type in logout");

        Scanner scn = new Scanner(System.in);
        String clientInput;
        String response;
        String host = null;
        int port = 0;
        byte[] password = null;

        try {
            File config = new File("config.properties");
            Properties prop = new Properties();
            if (!config.exists()) {
                try (OutputStream output = new FileOutputStream(config)) {
                    prop.setProperty("rcon.host", "localhost");
                    prop.setProperty("rcon.port", "25575");
                    prop.setProperty("rcon.password", "password");
                    prop.store(output, "JavaRcon configuration file." + System.lineSeparator() + "Created by: Pequla ( https://pequla.github.io/ )");
                }
            }
            try (InputStream input = new FileInputStream(config)) {
                prop.load(input);
                host = prop.getProperty("rcon.host");
                port = Integer.valueOf(prop.getProperty("rcon.port"));
                password = prop.getProperty("rcon.password").getBytes();
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

        try {
            rcon = new Rcon(host, port, password);
            while (true) {
                System.out.print("> ");
                clientInput = scn.nextLine().trim();
                switch (clientInput) {
                    case "logout":
                        disconnect();
                        break;
                    case "stop":
                        System.out.println("Do you really want to stop the server ?");
                        System.out.print("Answer: (Y/N) > ");
                        if (scn.nextLine().trim().toUpperCase().equals("Y")) {
                            rcon.command(clientInput);
                            disconnect();
                        }
                        break;
                    default:
                        response = rcon.command(clientInput);
                        if (response.length() != 0) {
                            System.out.println(response);
                        }
                        break;
                }
            }
        } catch (IOException ex) {
            System.err.println("There was a communication error, " + ex.getMessage());
        } catch (AuthenticationException ex) {
            System.err.println("Wrong rcon password, " + ex.getMessage());
        }
    }

    private static void disconnect() throws IOException {
        rcon.disconnect();
        System.exit(0);
    }
}
