package FormattedServer;

import javax.net.ssl.*;
import java.io.IOException;

/**
 This class' purpose is to allow clients to establish connections with the server and create new threads each time a new client attempts a connection
*/
public class FormattedServer {
    static final String KEYSTOREFILEPATH = "Keys/server.jks";
    static final String KEYSTOREPASSWORD = "49218163";
    static int portNumber = 17777;

    public static void main(String[] args) throws IOException {
        // For SSL debugging purposes:
        //System.setProperty("javax.net.debug", "ssl");

        // Tells the server where the keystore and certificate is and sets it to be used for the server side ssl
        System.setProperty("javax.net.ssl.keyStore", KEYSTOREFILEPATH);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTOREPASSWORD);

        // Implementing server side ssl
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket ss = (SSLServerSocket) ssf.createServerSocket(portNumber);
        ss.setEnabledProtocols(new String[] {"TLSv1.3", "TLSv1.2"});

        // Waiting for clients connections
        System.out.println("Server listening on port " + portNumber + "...");

        while (true) {
            // Server accepts requested connection from the client
            SSLSocket cs = (SSLSocket) ss.accept();
            System.out.println("Client connected: " + cs);

            // A new thread is created for each new client
            FormattedClientHandler fch = new FormattedClientHandler(cs);
            new Thread(fch).start();
        }
    }
}
