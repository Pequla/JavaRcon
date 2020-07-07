package javarcon;

import java.io.IOException;
import java.util.Scanner;
import net.kronos.rkon.core.ex.AuthenticationException;

public class JavaRcon {

    private static CustomRcon rcon;

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String clientInput;

        try {
            rcon = CustomRcon.getInstance();
            System.out.println("Connected to: " + rcon);
            System.out.println("To exit type in logout!");

            while (true) {
                System.out.print("> ");
                clientInput = scn.nextLine().trim();
                switch (clientInput) {
                    case "logout":
                        disconnect();
                        break;
                    case "stop":
                        System.out.println("Do you really want to stop the server?");
                        System.out.print("Answer: (Y/N) > ");
                        String answer = scn.nextLine().trim();
                        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                            rcon.command(clientInput);
                            disconnect();
                        } else {
                            System.out.println("Will not stop the server!");
                        }
                        break;
                    default:
                        System.out.println(rcon.command(clientInput));
                        break;
                }
            }
        } catch (IOException ex) {
            System.err.println("There was an I/O error, " + ex.getMessage());
        } catch (AuthenticationException ex) {
            System.err.println("Wrong rcon password, " + ex.getMessage());
        }
    }

    private static void disconnect() throws IOException {
        rcon.disconnect();
        System.exit(0);
    }
}
