import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * this class for message receiving from the server to the client
 */
public class MessageReceiver implements Runnable {
  private String userName;
  BufferedReader bufferReader;
  DataOutputStream outputStream;
  private DataInputStream inputStreamBuffer;
  private boolean isActive;
  private Socket client;

  /**
   * constructor of MessageReceiver which receives the message and print to clients terminal.
   * @param client is the client which receives message from the server.
   * @param userName userName.
   */
  public MessageReceiver(Socket client, String userName) {
    try {
      inputStreamBuffer = new DataInputStream(client.getInputStream());
      this.client = client;
      this.userName = userName;
      this.isActive = true;
    } catch (IOException e) {
      e.printStackTrace();
      shutdown();
    }
  }

  /**
   * default constructor
   */
  public MessageReceiver(){

  }

  @Override
  public void run() {
    while (isActive) {
      getMessage();
      if (!isActive) {
        System.out.println("Now you can close the program");
      }
    }
  }

  /**
   * Method to get message from the server and classify what to do with the message.
   */
  public void getMessage(){
    int messageIdentifier = -1;
    try{
      messageIdentifier = inputStreamBuffer.readInt();
      if (messageIdentifier==20){
        establishConnection();
      }
      else if (messageIdentifier==21) {
        suspendConnection();
      }
      else if (messageIdentifier==23) {
        queryResponse();
      }
      else if (messageIdentifier==24) {
        sendBroadcast();
      }
      else if (messageIdentifier==25) {
        sendDirect();
      }
      else if (messageIdentifier==26) {
        messageFailure();
      }
      else {
        System.out.println("Enter valid message");
      }

    }
    catch (IOException e){
      e.printStackTrace();
      shutdown();
    }
  }

  /**
   * this method establishing the connection
   * @throws IOException IOException
   */
  public void establishConnection() throws IOException{
    boolean checkStatus = inputStreamBuffer.readBoolean();
    if (checkStatus){
      System.out.println("Successfully Connection Established");
    }
    else{
      System.out.println("Connection is not established!");
    }
    System.out.println(getValue(inputStreamBuffer,inputStreamBuffer.readInt()));
  }

  /**
   * This method terminates the connection
   * @throws IOException IOException
   */

  public void suspendConnection() throws IOException {
    System.out.println("Connection terminated successfully");
    System.out.println(getValue(inputStreamBuffer,inputStreamBuffer.readInt()));
    this.isActive = false;
  }

  /**
   * This methods get the query response of users connected
   * @throws IOException throws IO exception
   */
  public void queryResponse() throws IOException {
    int usersCount = inputStreamBuffer.readInt();
    String query = "Active Users in the chatroom: ";
    for(int count=0;count<usersCount;count++){
      query=query+getValue(inputStreamBuffer,inputStreamBuffer.readInt()) + ",";
    }
    System.out.println(query);
  }

  /**
   * This method is sending the message to all clients
   * @throws IOException IOException
   */

  public void sendBroadcast() throws IOException {
    String outputText = "";
    outputText=outputText+getValue(inputStreamBuffer,inputStreamBuffer.readInt())+" "+"to all: ";
    outputText=outputText+getValue(inputStreamBuffer,inputStreamBuffer.readInt());
    System.out.println(outputText);
  }

  /**
   * this method is sending message directly to another client
   * @throws IOException IOException
   */
  public void sendDirect() throws IOException {
    String textInput="";
    textInput=textInput+getValue(inputStreamBuffer,inputStreamBuffer.readInt())+" to ";
    textInput=textInput+getValue(inputStreamBuffer,inputStreamBuffer.readInt())+" is sent: ";
    textInput=textInput+getValue(inputStreamBuffer,inputStreamBuffer.readInt());
    System.out.println(textInput);
  }

  /**
   * This method prints the failure message
   * @throws IOException IOException
   */
  public void messageFailure() throws IOException {
    System.out.println(getValue(inputStreamBuffer,inputStreamBuffer.readInt()));
  }

  /**
   * Method to get value of bytes by passing input stream and its size value
   * @param inputStreamBuffer inputStreamBuffer
   * @param size size
   * @return valueString
   * @throws IOException IOException
   */
  private String getValue(DataInputStream inputStreamBuffer, int size) throws IOException {
    byte[] bytes = new byte[size];
    inputStreamBuffer.readFully(bytes);
    String valueString= new String(bytes, StandardCharsets.UTF_8);
    return valueString;
  }

  /**
   * Method to close active streams and active state
   */
  public void shutdown(){
    try {
      outputStream.close();
      bufferReader.close();
      isActive = false;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String toString() {
    return "ReceiveTextMessage{}";
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
