package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputConsumerTest {

  private OutputConsumer testOutputConsumer;
  private BlockingQueue<OutputBufferPacket> testOutputBufferPackets;
  private AtomicBoolean testProducerFinished;

  @BeforeEach
  void setUp() {
    testOutputBufferPackets = new LinkedBlockingQueue<>();
    testProducerFinished = new AtomicBoolean(false);
    testOutputConsumer = new OutputConsumer(testOutputBufferPackets, testProducerFinished);
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testOutputConsumer, testOutputConsumer);

    // test with the null object
    assertFalse(testOutputConsumer.equals(null));

    // test with an object belonging to a different class
    assertFalse(testOutputConsumer.equals(testProducerFinished));

    // test with a different object
    assertFalse(testOutputConsumer.equals(
        new OutputConsumer(testOutputBufferPackets, new AtomicBoolean(true))));
    assertFalse(testOutputConsumer.equals(
        new OutputConsumer(new LinkedBlockingQueue<>(),testProducerFinished)));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testOutputBufferPackets, testProducerFinished);
    assertEquals(expectedHashcode, testOutputConsumer.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "OutputConsumer{" +
        "outputBufferPackets=" + testOutputBufferPackets +
        ", producerFinished=" + testProducerFinished +
        '}';
    assertEquals(expectedString, testOutputConsumer.toString());
  }
}