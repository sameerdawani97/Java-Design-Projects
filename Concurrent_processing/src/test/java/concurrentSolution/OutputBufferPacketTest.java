package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputBufferPacketTest {

  private OutputBufferPacket testOutputBufferPacket;
  private String testFileName;
  private ConcurrentHashMap<String, AtomicInteger> testMapOfDateAndSumClick;

  @BeforeEach
  void setUp() {
    testFileName = "A_2014";
    testMapOfDateAndSumClick = new ConcurrentHashMap<>();
    testMapOfDateAndSumClick.put("-10", new AtomicInteger(10));
    testOutputBufferPacket = new OutputBufferPacket(testFileName,testMapOfDateAndSumClick);
  }

  @Test
  void getFileName() {
    assertEquals(testFileName, testOutputBufferPacket.getFileName());
  }

  @Test
  void getMapOfDateAndSumClick() {
    assertEquals(testMapOfDateAndSumClick, testOutputBufferPacket.getMapOfDateAndSumClick());
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testOutputBufferPacket, testOutputBufferPacket);

    // test with the null object
    assertFalse(testOutputBufferPacket.equals(null));

    // test with an object belonging to a different class
    assertFalse(testOutputBufferPacket.equals(testFileName));

    // test with a different object
    assertNotEquals(testOutputBufferPacket,
        new OutputBufferPacket("2013", testMapOfDateAndSumClick));
    assertFalse(testOutputBufferPacket.equals(
        new OutputBufferPacket(testFileName, new ConcurrentHashMap<>())));
    assertTrue(testOutputBufferPacket.equals(
        new OutputBufferPacket(testFileName, testMapOfDateAndSumClick)));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testFileName, testMapOfDateAndSumClick);
    assertEquals(expectedHashcode, testOutputBufferPacket.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "OutputBufferPacket{" +
        "fileName='" + testFileName + '\'' +
        ", bufferData=" + testMapOfDateAndSumClick +
        '}';
    assertEquals(expectedString, testOutputBufferPacket.toString());
  }
}