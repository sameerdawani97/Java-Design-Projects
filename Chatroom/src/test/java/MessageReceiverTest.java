import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


class MessageReceiverTest {

  private DataOutputStream dataOutputStreamObject;
  private ServerSocket serverTest;
  private Socket clientTest;
  private Socket messageReceiver;

  MessageReceiver user = new MessageReceiver();

  @BeforeEach
  void setUp() throws IOException {
    serverTest = new ServerSocket(6878);
    clientTest = new Socket("localhost", 6878);
    messageReceiver = serverTest.accept();
    dataOutputStreamObject = new DataOutputStream(messageReceiver.getOutputStream());
  }

  @Test
  void establishConnectionTest() throws IOException, InterruptedException {
    byte[] msg = "test".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.CONNECT_RESPONSE);
    dataOutputStreamObject.writeBoolean(true);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void suspendConnectionTest() throws IOException, InterruptedException {
    byte[] msg = "sample".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.DISCONNECT_MESSAGE);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void queryResponseTest() throws IOException, InterruptedException {
    byte[] msg = "sample".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.QUERY_USER_RESPONSE);
    dataOutputStreamObject.writeInt(2);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void broadcastResponseTest() throws IOException, InterruptedException {
    byte[] msg = "sample".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.BROADCAST_MESSAGE);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void directResponseTest() throws IOException, InterruptedException {
    byte[] msg = "sample".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.DIRECT_MESSAGE);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void failureMessageTest() throws IOException, InterruptedException {
    byte[] msg = "sample".getBytes(StandardCharsets.UTF_8);
    dataOutputStreamObject.writeInt(MessageIdentifier.FAILED_MESSAGE);
    dataOutputStreamObject.writeInt(msg.length);
    dataOutputStreamObject.write(msg);
    dataOutputStreamObject.flush();
    MessageReceiver ReceiveMessage = new MessageReceiver(clientTest, "usernameTest");
    new Thread(ReceiveMessage).start();
    Thread.sleep(100);
  }

  @Test
  void testToString() {
    MessageReceiver userInputTest = new MessageReceiver();
    Assertions.assertNotEquals(user, userInputTest);
    Assertions.assertEquals(userInputTest.toString(), user.toString());
  }

  @Test
  void testToHashcode(){
    MessageReceiver userInputTest = new MessageReceiver();
    Assertions.assertNotEquals(userInputTest.hashCode(), user.hashCode());
  }

  @Test
  void testEquals(){
    MessageReceiver userInputTest = new MessageReceiver();
    Assertions.assertNotEquals(userInputTest, user);
  }

  @AfterEach
  void close() throws IOException {
    clientTest.close();
    serverTest.close();
    messageReceiver.close();
    dataOutputStreamObject.close();
  }
}