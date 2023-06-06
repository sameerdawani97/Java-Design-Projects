import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * A class processes supported messages as per the response
 */
public class MessageSender extends Thread {

  private BufferedReader bufferedReader;
  private DataOutputStream outputStream;
  private boolean isActive;
  private byte[] usernameByte;
  private String username;

  /**
   * Constructor for MessageSender
   * @param socket socket
   * @param username username of user who has logged in
   */
  public MessageSender(Socket socket,String username)  {
    try {
      bufferedReader = new BufferedReader (new InputStreamReader(System.in));
      outputStream = new DataOutputStream(socket.getOutputStream());
      this.username = username;
      isActive = true;
      usernameByte = username.getBytes();
      String signIn = "SignIn";
      sendMessage(signIn);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (MessageTypeException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Constructor to test the override methods
   */
  public MessageSender(){}

  /**
   * Gets username
   * @return username
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Gets isActive
   * @return a boolean value
   */
  public boolean isActive() {
    return this.isActive;
  }

  /**
   * Gets UsernameByte array
   * @return UsernameByte
   */
  public byte[] getUsernameByte() {
    return this.usernameByte;
  }

  /**
   * Run method gets user input from user via readUserOption and calls sendMessage()
   */
  public void run() {
    while(isActive) {
      try {
        Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
        System.out.println("Please enter a message option");
        String userOption = bufferedReader.readLine();
        sendMessage(userOption);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      } catch (IOException e) {
        throw new RuntimeException(e);
      } catch (MessageTypeException e) {
        throw new RuntimeException(e);
      }
    }
  }

  /**
   * sendMessage checks the user input entered and calls respective functions
   * @param userOption user entered command
   * @throws IOException IOException
   * @throws MessageTypeException ChatroomException
   */
  public void sendMessage(String userOption) throws IOException, MessageTypeException {
    userOption = userOption.trim();
    if(userOption.equals("?")) {
      showOptions();
    } else if(userOption.equalsIgnoreCase("signIn")) {
      signIn(userOption);
    } else if(userOption.equalsIgnoreCase("logoff")) {
      logoff(userOption);
    } else if(userOption.equalsIgnoreCase("who")) {
      connectedUser(userOption);
    } else if(userOption.startsWith("@all")) {
      sendBroadcast(userOption);
    } else if(userOption.startsWith("@")) {
      sendToClient(userOption);
    } else if(userOption.startsWith("!")) {
      sendInsultGrammarText(userOption);
    } else {
      System.out.println("Unknown Command");
    }
  }

  /**
   * showOptions prints the messages that can be entered by the user
   */
  public void showOptions() {
    System.out.println("• logoff: sends a DISCONNECT_MESSAGE to the server" + '\n'+
        "• who: sends a QUERY_CONNECTED_USERS to the server" + '\n' +
        "• @user: sends a DIRECT_MESSAGE to the specified user to the server" + '\n' +
        "• @all: sends a BROADCAST_MESSAGE to the server, to be sent to all users connected" + '\n' +
        "• !user: sends a SEND_INSULT message to the server, to be sent to the specified user");
  }

  /**
   * signIn writes user details to the DataOutputStream and handles error
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void signIn(String inputString) throws MessageTypeException {
    int type = MessageIdentifier.CONNECT_MESSAGE;
    try {
      outputStream.writeInt(type);
      int userNameLength = usernameByte.length;
      outputStream.writeInt(userNameLength);
      outputStream.write(usernameByte);
      outputStream.flush();
    } catch (Exception e) {
      isActive = false;
      shutdown();
      String error = e.getMessage();
      throw new MessageTypeException(error);
    }
  }

  /**
   * logoff writes user details to the DataOutputStream and handles exception
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void logoff(String inputString) throws MessageTypeException {
    int type = MessageIdentifier.DISCONNECT_MESSAGE;
    try {
      outputStream.writeInt(type);
      int userNameLength = usernameByte.length;
      outputStream.writeInt(userNameLength);
      outputStream.write(usernameByte);
      outputStream.flush();
      isActive = false;
    } catch (Exception e) {
      isActive = false;
      shutdown();
      String error = e.getMessage();
      throw new MessageTypeException(error);
    }
  }

  /**
   * connectedUser writes user details to the DataOutputStream and handles exception
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void connectedUser(String inputString) throws MessageTypeException {
    int type = MessageIdentifier.QUERY_CONNECTED_USERS;
    try {
      outputStream.writeInt(type);
      int userNameLength = usernameByte.length;
      outputStream.writeInt(userNameLength);
      outputStream.write(usernameByte);
      outputStream.flush();
    } catch (Exception e) {
      isActive = false;
      shutdown();
      String error = e.getMessage();
      throw new MessageTypeException(error);
    }
  }

  /**
   * sendBroadcast writes sender and message details to the DataOutputStream and handles exception
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void sendBroadcast(String inputString) throws MessageTypeException {
    try {
      int type = MessageIdentifier.BROADCAST_MESSAGE;
      int FIVE = 5;
      int index = inputString.indexOf("@all") + FIVE;
      String message = inputString.substring(index).trim();
      byte[] messageBytes = message.getBytes();

      int nameLength = usernameByte.length;
      int messageLength = messageBytes.length;
      outputStream.writeInt(type);
      outputStream.writeInt(nameLength);
      outputStream.write(usernameByte);
      outputStream.writeInt(messageLength);
      outputStream.write(messageBytes);
      outputStream.flush();

      System.out.println("\"" + message + "\"" + " sent to all connected clients.");
    } catch (Exception e) {
      isActive =false;
      shutdown();
      String error= "Please enter a message";
      throw new MessageTypeException(error);
    }
  }

  /**
   * sendToClient writes sender, receiver and message details to the DataOutputStream and handles exception
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void sendToClient(String inputString) throws MessageTypeException {
    int type = MessageIdentifier.DIRECT_MESSAGE;
    int space = inputString.indexOf(" ");
    String username = inputString.substring(inputString.indexOf("@")+MessageIdentifier.ONE, space);
    String message = inputString.substring(space).trim();
    byte[] nameBytes = username.getBytes();
    byte[] messageBytes = message.getBytes();
    int receiverUserLength = nameBytes.length;
    int senderUserNameSize = usernameByte.length;
    int messageSize = messageBytes.length;

    try {
      outputStream.writeInt(type);
      outputStream.writeInt(senderUserNameSize);
      outputStream.write(usernameByte);
      outputStream.writeInt(receiverUserLength);
      outputStream.write(nameBytes);
      outputStream.writeInt(messageSize);
      outputStream.write(messageBytes);
      outputStream.flush();
      System.out.println("\""+message+"\" " + "is sent to user " + "\"" + username + "\"");
    } catch (Exception e) {
      isActive = false;
      shutdown();
      String error = "Please enter a message along with username";
      throw new MessageTypeException(error);
    }
  }

  /**
   * sendInsultGrammarText is called and message, receiver details are written to the DataOutputStream
   * @param inputString message
   * @throws MessageTypeException ChatroomException
   */
  public void sendInsultGrammarText(String inputString) throws MessageTypeException {
    try {
      int type = MessageIdentifier.SEND_INSULT;
      String sendTo = inputString.substring(inputString.indexOf("!") + MessageIdentifier.ONE).trim();
      if(sendTo == null || sendTo.length() == MessageIdentifier.ZERO) {
        System.out.println("Please enter a username");
      } else {
        byte[] receiverUsername = sendTo.getBytes();
        int receiverUserNameLength = receiverUsername.length;
        int senderUserNameLength = usernameByte.length;
        outputStream.writeInt(type);
        outputStream.writeInt(senderUserNameLength);
        outputStream.write(usernameByte);
        outputStream.writeInt(receiverUserNameLength);
        outputStream.write(receiverUsername);
        outputStream.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
      isActive = false;
      shutdown();
      throw new MessageTypeException("Incorrect format");
    }
  }

  /**
   * close method called in case of exception, it closes BufferReader and DataOutputStream
   */
  public void shutdown() {
    try {
      outputStream.close();
      bufferedReader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return "SendTextMessage{}";
  }
}
