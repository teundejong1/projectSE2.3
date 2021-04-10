package networking.connection;

import java.io.IOException;
import java.net.Socket;

public class SocketFactory {

    private static final int DEFAULT_PORT = 7789;
//    private static final String DEFAULT_IP = "145.33.225.170";
    

    public static Socket createSocket(String ip, int port) throws IOException {
        return new Socket(ip, port);
    }
    
    public static Socket createDefaultSocket(String ip) throws IOException {
        return new Socket(ip, DEFAULT_PORT);
    }

    public static Socket createDefaultLocalhostSocket() throws IOException {
        return new Socket("localhost", DEFAULT_PORT);
    }

    public static Socket createLocalhostSocket(int port) throws IOException {
        return new Socket("localhost", port);
    }

}
