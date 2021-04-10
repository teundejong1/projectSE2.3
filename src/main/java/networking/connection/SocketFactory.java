package networking.connection;

import java.io.IOException;
import java.net.Socket;
/**
 * Factory for creating Sockets
 * @author Jeroen Lammersma
 */
public class SocketFactory {

    private static final int DEFAULT_PORT = 7789;
//    private static final String DEFAULT_IP = "145.33.225.170";
    
    /**
     * @param ip address server
     * @param port for input
     * @return 
     * @throws IOException
     */
    public static Socket createSocket(String ip, int port) throws IOException {
        return new Socket(ip, port);
    }
    
    /**
     * uses default socket
     * @param ip addres of server
     * @return a socket for the connection
     * @throws IOException
     */
    public static Socket createDefaultSocket(String ip) throws IOException {
        return new Socket(ip, DEFAULT_PORT);
    }

    /**
     * uses localhost and default port
     * @return a socket for the connection
     * @throws IOException
     */
    public static Socket createDefaultLocalhostSocket() throws IOException {
        return new Socket("localhost", DEFAULT_PORT);
    }

    /**
     * uses localhost
     * @param port for input
     * @return a socket for the connection
     * @throws IOException
     */
    public static Socket createLocalhostSocket(int port) throws IOException {
        return new Socket("localhost", port);
    }

}
