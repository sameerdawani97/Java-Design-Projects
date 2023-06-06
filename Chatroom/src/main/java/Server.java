import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server is responsible for allowing clients to connect,
 * get a list of all other clients connected, and disconnect.
 */
public class Server extends Thread {

  /**
   * The main method to declare ports, receive inputs and validate args from the command line
   * @param args provided via the command line
   * @throws IOException IOException
   */
  public static void main(String[] args) throws IOException {
    int connectingPortForServer = 6464;
    ServerSocket socket = new ServerSocket(connectingPortForServer);
    System.out.println("Port Number " + socket.getLocalPort() + " Inet Address " + socket.getInetAddress());
    while (true) {
      Socket newClient = socket.accept();
      System.out.println("Request received from the Client: " + newClient.getInetAddress().getHostName());
      System.out.println("Creating a channel for the recent Client.");
      InputOutputHandler clientChannel = new InputOutputHandler(newClient);
      InputOutputHandler.clientList.add(clientChannel);
      new Thread(clientChannel).start();
      System.out.println("There are " + InputOutputHandler.clientList.size() + " users in total.");
    }
  }
}
