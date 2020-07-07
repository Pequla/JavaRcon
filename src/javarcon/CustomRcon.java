package javarcon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;
import net.kronos.rkon.core.Rcon;
import net.kronos.rkon.core.ex.AuthenticationException;

public class CustomRcon extends Rcon {

    private static CustomRcon instance;

    private CustomRcon(String host, int port, byte[] password) throws IOException, AuthenticationException {
        super(host, port, password);
    }

    public static CustomRcon getInstance() throws IOException, AuthenticationException {
        if (instance == null) {

            //Variable declaration
            String host;
            int port;
            byte[] password;

            //Reading configuration from file
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
                host = prop.getProperty("rcon.host", "localhost");
                port = Integer.valueOf(prop.getProperty("rcon.port", "25575"));
                password = prop.getProperty("rcon.password", "password").getBytes();
            }

            //Constructing the instance
            instance = new CustomRcon(host, port, password);
        }
        //Returns the instance
        return instance;
    }

    //Short-circuit evaluation
    private static boolean empty(final String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public String command(String payload) throws IOException {
        String uc = "Unknown command";
        if (!empty(payload)) {
            String response = super.command(payload);
            if (empty(response)) {
                return "No command response";
            }
            if (response.startsWith(uc)) {
                return uc;
            }
            return response;
        }
        return uc;
    }

    @Override
    public String toString() {
        Socket s = getSocket();
        return s.getInetAddress() + ":" + s.getLocalPort();
    }

}
