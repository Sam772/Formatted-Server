package FormattedServer;

import java.io.*;
import javax.net.ssl.*;
import java.util.Scanner;

/**
 * This class' purpose is to handle clients and their interactions to the server
 */
public class FormattedClient {
    static final String TRUSTSTOREFILEPATH = "Keys/client.truststore";
    static final String TRUSTSTOREPASSWORD = "49218163";
    static int portNumber = 17777;

    public static void main(String[] args) throws IOException {
        // For SSL debugging purposes:
        //System.setProperty("javax.net.debug", "ssl");

        // Tells the client where the truststore and certificate is and sets it to be used for the client side ssl
        System.setProperty("javax.net.ssl.trustStore", TRUSTSTOREFILEPATH);
        System.setProperty("javax.net.ssl.trustStorePassword", TRUSTSTOREPASSWORD);

        // Implementing client side ssl
        SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket s = (SSLSocket) ssf.createSocket("localhost", portNumber);
        s.setEnabledProtocols(new String[] {"TLSv1.3", "TLSv1.2"});
        s.startHandshake(); // the SSL handshake

        System.out.println("Connected to server...");

        // Used to communicate via input and output and initiate TCP
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a message (type 'exit' to quit): ");
            String message = scanner.nextLine();

            if ("exit".equalsIgnoreCase(message)) {
                break;
            }

            // Send the client's message to the server
            pw.println(message);

            // Receive the prompt for the format and send the format to the server
            String formatPrompt = br.readLine();
            System.out.print(formatPrompt);
            String format = scanner.nextLine();
            pw.println(format);

            // Receive the formatted message from the server
            String formattedMessage = br.readLine();
            System.out.println("Received from server: " + formattedMessage);
        }
        s.close();
    }
}
