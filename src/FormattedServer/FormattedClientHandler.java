package FormattedServer;

import javax.net.ssl.*;
import java.io.*;

/**
 * This class' purpose is to handle the interactions
 * from the server to the client
 */
public class FormattedClientHandler extends Thread {
    private final SSLSocket clientSocket;

    // Initialises the client's socket
    public FormattedClientHandler(SSLSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Used to communicate via input and output and initiate TCP
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);

            String clientMessage;
            while ((clientMessage = br.readLine()) != null) {
                System.out.println("Received from client: " + clientMessage);

                // Ask the client for their desired format (uppercase/lowercase)
                pw.println("Specify the format (uppercase/lowercase): ");
                String format = br.readLine();

                // Convert the message based on the client's specified format
                String formattedMessage = "Invalid format specified.";
                if ("uppercase".equalsIgnoreCase(format)) {
                    formattedMessage = clientMessage.toUpperCase();
                } else if ("lowercase".equalsIgnoreCase(format)) {
                    formattedMessage = clientMessage.toLowerCase();
                }

                // Send the formatted message back to the client
                pw.println(formattedMessage);
            }

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
