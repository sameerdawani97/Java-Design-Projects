import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * The Client class will open a socket to communicate with the server.
 * It will maintain the socket to listen to incoming messages from the server,
 * as well as listen to the UI (terminal) for messages from the user to send to the server.
 */
public class Client {

  private static int portForServer;
  private static String localhost;

  /**
   * The main method produces the entry of the application with input args
   * and calling the method for send text and receive text
   * @param args given input
   * @throws IOException throws IO exception
   */
  public static void main(String[] args) throws IOException {
    try {
      localhost = args[MessageIdentifier.ZERO];
      portForServer = Integer.parseInt(args[MessageIdentifier.ONE]);
      System.out.println("Localhost " + localhost + " port Number " + portForServer);
      System.out.println("Please enter your Name: ");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Please pass the IP address and Port Number via terminal");
      System.exit(MessageIdentifier.ZERO); // int 0
    }

    Scanner scan = new Scanner(System.in);
    String clientName = scan.nextLine();

    if (clientName.equals((Object)null) || (clientName.equals(""))) {
      System.out.println("Client Name is not provided!");
    }
    else{
      Socket clientSocket = new Socket(localhost, portForServer);
      Thread messageReceive = new Thread(new MessageReceiver(clientSocket, clientName));
      Thread messageSend = new Thread(new MessageSender(clientSocket, clientName));
      messageReceive.start();
      messageSend.start();
    }




  }
}
