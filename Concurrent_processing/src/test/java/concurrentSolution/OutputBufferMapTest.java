package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputBufferMapTest {

  private OutputBufferMap testOutputBufferMap, testOutputBufferMap1;
  private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> testBufferData;
  private StudentVleBuffer testStudentVleBuffer;
  private ConcurrentHashMap<String, AtomicInteger> testMapOfDateAndSumClick;

  @BeforeEach
  void setUp() {
    testStudentVleBuffer = new StudentVleBuffer("AAA","2014J","-10",10);
    testMapOfDateAndSumClick = new ConcurrentHashMap<>();
    testMapOfDateAndSumClick.put("-10", new AtomicInteger(10));
    testBufferData = new ConcurrentHashMap<>();
    testBufferData.put("AAA_2014J", testMapOfDateAndSumClick);
    testOutputBufferMap = new OutputBufferMap();
    testOutputBufferMap1 = new OutputBufferMap(testBufferData);
  }

  @Test
  void getBufferData() {
    assertEquals(testBufferData, testOutputBufferMap1.getBufferData());
  }

  @Test
  void putIntoBuffer() {
    testOutputBufferMap.putIntoBuffer(testStudentVleBuffer);
    testOutputBufferMap.putIntoBuffer(testStudentVleBuffer);
    assertEquals(testBufferData.get("AAA_2014J").get("-10").get()*2,
        testOutputBufferMap.getBufferData().get("AAA_2014J").get("-10").get());
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testOutputBufferMap1, testOutputBufferMap1);

    // test with the null object
    assertFalse(testOutputBufferMap1.equals(null));

    // test with an object belonging to a different class
    assertFalse(testOutputBufferMap1.equals(testStudentVleBuffer));

    // test with a different object
    assertFalse(testOutputBufferMap.equals(testOutputBufferMap1));
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testBufferData);
    assertEquals(expectedHashcode, testOutputBufferMap1.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "OutputBufferMap{" +
        "bufferData=" + testBufferData +
        '}';
    assertEquals(expectedString, testOutputBufferMap1.toString());
  }
}