import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.DataOutputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.IOException;

class MessageSenderTest {

  MessageSender testMessageSender, testMessageSender1;
  DataOutputStream outputStream;
  private ServerSocket testServer;
  private Socket testClient;
  private int testPortForSever;

  @BeforeEach
  void setup() throws IOException, InterruptedException {
    testPortForSever = 7474;
    testServer = new ServerSocket(testPortForSever);
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
    testClient = new Socket("localhost", testPortForSever);
    outputStream = new DataOutputStream(testClient.getOutputStream());
    testMessageSender = new MessageSender();
    testMessageSender1 = new MessageSender(testClient, "testUser");
  }

  @Test
  void testSendDirectMessage() throws InterruptedException {
    String messageValue = "@test Hello World!\n";
    InputStream inputBuffer = new ByteArrayInputStream(messageValue.getBytes());
    System.setIn(inputBuffer);
    MessageSender sendValue = new MessageSender(testClient, "x");
    new Thread(sendValue).start();
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
  }

  @Test
  void testSendBroadcastMessage() throws InterruptedException, MessageTypeException {
    String testString = "@test Hello World!\n";
    testMessageSender1.sendBroadcast(testString);

    String value = "@all How are you\n";
    InputStream inputBufferValue = new ByteArrayInputStream(value.getBytes());
    System.setIn(inputBufferValue);
    MessageSender sendValue = new MessageSender(testClient, "x");
    new Thread(sendValue).start();
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
  }

  @Test
  void testSendInsultMessage() throws InterruptedException {
    String messageValue = "!test\n";
    InputStream inputBuffer = new ByteArrayInputStream(messageValue.getBytes());
    System.setIn(inputBuffer);
    MessageSender sendValue = new MessageSender(testClient, "x");
    new Thread(sendValue).start();
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
  }

  @Test
  public void testLogoff() throws MessageTypeException {
    testMessageSender1.logoff(testMessageSender1.getUsername());
    assertFalse(testMessageSender1.isActive());
  }

  @Test
  public void testConnectedUser() throws Exception, MessageTypeException {
    testMessageSender1.connectedUser("who");
    // assert that the expected message was sent to the output stream
    int expectedType = MessageIdentifier.QUERY_CONNECTED_USERS;
    outputStream.writeInt(expectedType);
    int userNameLength = testMessageSender1.getUsernameByte().length;
    outputStream.writeInt(userNameLength);
    outputStream.write(testMessageSender1.getUsernameByte());
    outputStream.flush();
  }

  @Test
  public void testSentToClient() throws Exception, MessageTypeException {
    String testString = "@all Hello World!\n";
    testMessageSender1.sendToClient(testString);
  }

  @Test
  void testUserOptionMenu() throws InterruptedException {
    String value = "?\n";
    InputStream inputBuffer = new ByteArrayInputStream(value.getBytes());
    System.setIn(inputBuffer);
    MessageSender sendValue = new MessageSender(testClient, "x");
    new Thread(sendValue).start();
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
  }

  @Test
  void testAskQuery() throws InterruptedException {
    String query = "who\n";
    InputStream inputBuffer = new ByteArrayInputStream(query.getBytes());
    System.setIn(inputBuffer);
    MessageSender value = new MessageSender(testClient, "x");
    new Thread(value).start();
    Thread.sleep(MessageIdentifier.MESSAGE_DELAY_MS);
  }

  @Test
  void testEquals(){
    MessageSender userInputTest = new MessageSender();
    Assertions.assertNotEquals(userInputTest, testMessageSender);
  }

  @Test
  void testToHashcode(){
    MessageSender userInputTest = new MessageSender();
    Assertions.assertNotEquals(userInputTest.hashCode(), testMessageSender.hashCode());
  }

  @Test
  void testToString() {
    MessageSender userInputTest = new MessageSender();
    Assertions.assertNotEquals(testMessageSender, userInputTest);
    Assertions.assertEquals(userInputTest.toString(), testMessageSender.toString());
  }
  @AfterEach
  void close() throws IOException {
    testClient.close();
    //testClient1.close();
    testServer.close();
  }
}
