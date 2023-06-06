//import Exceptions.UndefinedInputException;
import Exceptions.UndefinedInputException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
//import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
//import org.json.simple.parser.ParseException;

/**
 * this class for handling input and output of a client
 */
public class InputOutputHandler extends Thread{
  private boolean isActive;
  private String userName;
  private Socket client;
  DataInputStream inputStream;
  DataOutputStream outputStream;

  /**
   * Client list which contains the objects of InputOutput handler, and it is common to all thread objects.
   * It is thread safe.
   */
  public static List<InputOutputHandler> clientList = new CopyOnWriteArrayList<InputOutputHandler>();

  /**
   * Constructor of InputOutputHandler
   * @param client client
   * @throws IOException IOException
   */
  public InputOutputHandler(Socket client) throws IOException {
    try{
      this.isActive = true;
      this.client = client;
      inputStream = new DataInputStream(client.getInputStream());
      outputStream = new DataOutputStream(client.getOutputStream());
      chatHandler();
    }
    catch (IOException e){
      connectionClose();
      throw new RuntimeException(e);
    }
  }

  /**
   * default constructor
   */
  public InputOutputHandler() {}

  /**
   * thread run method that is calling the chat handler function
   */
  @Override
  public void run(){
    while(isActive){
      chatHandler();
    }
  }

  /**
   * Chat handler method to decide which is the input from client and which decision to choose
   */
  private void chatHandler(){
    try {
      int dataFrameType=inputStream.readInt();
      System.out.println(dataFrameType);
      if(dataFrameType== MessageIdentifier.CONNECT_MESSAGE) {
        connect();
      } else if(dataFrameType== MessageIdentifier.DISCONNECT_MESSAGE) {
        disconnect();
        System.out.println("The user "+userName+" is disconnected.");
        clientList.remove(this);
        isActive=false;
      } else if(dataFrameType== MessageIdentifier.QUERY_CONNECTED_USERS) {
        connectedUsers();
      }
      else if(dataFrameType== MessageIdentifier.BROADCAST_MESSAGE) {
        broadcast();
      } else if(dataFrameType== MessageIdentifier.DIRECT_MESSAGE) {
        sendDirect();
      } else if(dataFrameType== MessageIdentifier.SEND_INSULT) {
        insultMessage();
      } else {
        fail();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (UndefinedInputException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method to get username of client
   * @return userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * connect function to connect to chatroom and connect response is gone
   */
  private synchronized void connect(){
    try {
      String user = getValue(inputStream,inputStream.readInt());
      userName=user;
      outputStream.writeInt(MessageIdentifier.CONNECT_RESPONSE);
      int clientsCount = InputOutputHandler.clientList.size();
      String message;
      //checking already exists a client
      boolean clientExist = false;
      for(InputOutputHandler client: clientList){
        if(client.getUserName()==userName){
          clientExist = true;
          break;
        }
      }
      //end
      if(clientsCount>10)
      {
        message="maximum users count limit reached";
        outputStream.writeBoolean(false);
        isActive=false;
      }
      else if(clientExist)
      {
        message="The given user is already present";
        outputStream.writeBoolean(false);
        isActive=false;
      }
      else {
        outputStream.writeBoolean(true);
        message="There are "+clientsCount+" connected clients";
        isActive=true;
      }
      byte[] messageBytes = message.getBytes();
      int messageLength = messageBytes.length;
      outputStream.writeInt(messageLength);
      outputStream.write(messageBytes);

      outputStream.flush();
      System.out.println(message);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * disconnect function
   */
  private synchronized void disconnect(){
    try {
      String user = getValue(inputStream, inputStream.readInt());
      userName=user;
      outputStream.writeInt(MessageIdentifier.DISCONNECT_MESSAGE);
      String message = "You are no longer connected " + userName;
      byte[] messageBytes = message.getBytes();
      int messageLength = messageBytes.length;
      outputStream.writeInt(messageLength);
      outputStream.write(messageBytes);

      outputStream.flush();
      System.out.println(message);

    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * function to show connected users
   */
  private synchronized void connectedUsers(){
    try {
      String user = getValue(inputStream, inputStream.readInt());
      userName = user;
      outputStream.writeInt(MessageIdentifier.QUERY_USER_RESPONSE);
      outputStream.writeInt(clientList.size());

      for (InputOutputHandler i : clientList) {
        byte[] usernameBytes = i.userName.getBytes();
        int usernameLength = usernameBytes.length;
        outputStream.writeInt(usernameLength);
        outputStream.write(usernameBytes);
      }
      outputStream.flush();
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method to broadcast message to all users except itself
   * @throws IOException IOException
   */
  private synchronized void broadcast() throws IOException{
    String sender = getValue(inputStream,inputStream.readInt());
    String message = getValue(inputStream,inputStream.readInt());
    for (InputOutputHandler memberClient : clientList) {
      if (memberClient != this) {
        byte[] usernameLength = sender.getBytes();
        byte[] chatMessage = message.getBytes();
        memberClient.outputStream.writeInt(MessageIdentifier.BROADCAST_MESSAGE);
        memberClient.outputStream.writeInt(usernameLength.length);
        memberClient.outputStream.write(usernameLength);
        memberClient.outputStream.writeInt(chatMessage.length);
        memberClient.outputStream.write(chatMessage);
        outputStream.flush();
      }
    }
  }

  /**
   * Method to send message to particular user.
   * @throws IOException IOException
   */
  private synchronized void sendDirect() throws IOException{
    String sender = getValue(inputStream,inputStream.readInt());
    String receiver = getValue(inputStream, inputStream.readInt());
    String message = getValue(inputStream, inputStream.readInt());
    byte[] senderBytes = sender.getBytes();
    byte[] chatMessage = message.getBytes();
    byte[] receiverBytes = receiver.getBytes();
    for(InputOutputHandler chat: InputOutputHandler.clientList){
      if(chat.userName.equals(receiver)){
        chat.outputStream.writeInt(MessageIdentifier.DIRECT_MESSAGE);
        chat.outputStream.writeInt(senderBytes.length);
        chat.outputStream.write(senderBytes);
        chat.outputStream.writeInt(receiverBytes.length);
        chat.outputStream.write(receiverBytes);
        chat.outputStream.writeInt(chatMessage.length);
        chat.outputStream.write(chatMessage);
        chat.outputStream.flush();
        return;
      }
    }
  }

  /**
   * Method to send an insult message to all
   */
  private synchronized void insultMessage()
      throws IOException, ParseException, UndefinedInputException, ParseException {
    String sender = getValue(inputStream,inputStream.readInt());
    String receiver = getValue(inputStream,inputStream.readInt());
    byte[] usernameLength =  sender.getBytes();
    byte[] receiverNameLength = receiver.getBytes(StandardCharsets.UTF_8);
    Path insultGrammarFilePath = FileSystems.getDefault().getPath("src/main/resources/insult_grammar.json");
    String fileName = insultGrammarFilePath.toString();
    System.out.println("FileName: "+fileName);
    JsonParser jsonParser = new JsonParser();
    JSONObject parseOutput = jsonParser.load(fileName);
    System.out.println("parseOutput: "+parseOutput);
    //new HashMap();
    HashMap<String, List<String>> grammarMap = jsonParser.grammarParse(parseOutput);
    System.out.println("grammarMap: "+grammarMap);
    GrammarProcessor grammarProcessor = new GrammarProcessor();
    String insultSentence = grammarProcessor.contentProcessor("start", grammarMap);
    byte[] messageBytes = insultSentence.getBytes(StandardCharsets.UTF_8);
    for(InputOutputHandler textValue: InputOutputHandler.clientList){
      textValue.outputStream.writeInt(MessageIdentifier.DIRECT_MESSAGE);
      textValue.outputStream.writeInt(usernameLength.length);
      textValue.outputStream.write(usernameLength);
      textValue.outputStream.writeInt(receiverNameLength.length);
      textValue.outputStream.write(receiverNameLength);
      textValue.outputStream.writeInt(messageBytes.length);
      textValue.outputStream.write(messageBytes);
      textValue.outputStream.flush();
    }
  }

  /**
   * Method to respond to fail of chat
   * @throws IOException IOException
   */
  private synchronized void fail() throws IOException{
    System.out.println("Message failed");
    byte[] textValueBytes = "fail Message".getBytes();
    this.outputStream.writeInt(MessageIdentifier.FAILED_MESSAGE);
    this.outputStream.writeInt(textValueBytes.length);
    this.outputStream.write(textValueBytes);
  }

  /**
   * Method to close the connection of streams
   */
  private void connectionClose(){
    try {
      this.isActive = false;
      outputStream.close();
      inputStream.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Method to get string bytes of size from the input stream
   * @param inputStream inputStream
   * @param size byte size
   * @return valueString
   * @throws IOException IOException
   */
  private String getValue(DataInputStream inputStream, int size) throws IOException{
    byte[] bytes = new byte[size];
    inputStream.readFully(bytes);
    String valueString= new String(bytes, StandardCharsets.UTF_8);
    return valueString;
  }

  @Override
  public String toString() {
    return "InputOutputHandler{}";
  }
  @Override
  public int hashCode() {
    return super.hashCode();
  }
  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }


}
