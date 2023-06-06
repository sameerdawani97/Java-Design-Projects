package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputProducerTest {

  private OutputProducer testOutputProducer;
  private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> testOutputMap;
  private BlockingQueue<OutputBufferPacket> testOutputBufferPackets;
  private AtomicBoolean testProducerFinished;

  @BeforeEach
  void setUp() {
    testOutputMap= new ConcurrentHashMap<>();
    testOutputBufferPackets = new LinkedBlockingQueue<>();
    testProducerFinished = new AtomicBoolean(false);
    testOutputProducer = new OutputProducer(testOutputMap, testOutputBufferPackets, testProducerFinished);
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testOutputProducer, testOutputProducer);

    // test with the null object
    assertFalse(testOutputProducer.equals(null));

    // test with an object belonging to a different class
    assertFalse(testOutputProducer.equals(testProducerFinished));

    // test with a different object
    assertFalse(testOutputProducer.equals(
        new OutputProducer(testOutputMap, testOutputBufferPackets, new AtomicBoolean(true))));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testOutputMap, testOutputBufferPackets, testProducerFinished);
    assertEquals(expectedHashcode, testOutputProducer.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "OutputProducer{" +
        "outputMap=" + testOutputMap +
        ", outputBufferPackets=" + testOutputBufferPackets +
        ", isFinished=" + testProducerFinished +
        '}';
    assertEquals(expectedString, testOutputProducer.toString());
  }
}