package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentVleBufferTest {

  private StudentVleBuffer testStudentVleBuffer;
  private String testCodeModule;
  private String testCodePresentation;
  private String testDate;
  private Integer testSumClick;

  @BeforeEach
  void setUp() {
    testCodeModule = "AAA";
    testCodePresentation = "2013J";
    testDate = "-10";
    testSumClick = 1;
    testStudentVleBuffer = new StudentVleBuffer(testCodeModule, testCodePresentation, testDate, testSumClick);
  }

  @Test
  void getCodeModule() {
    assertEquals(testCodeModule, testStudentVleBuffer.getCodeModule());
  }

  @Test
  void getCodePresentation() {
    assertEquals(testCodePresentation, testStudentVleBuffer.getCodePresentation());
  }

  @Test
  void getDate() {
    assertEquals(testDate, testStudentVleBuffer.getDate());
  }

  @Test
  void getSumClick() {
    assertEquals(testSumClick, testStudentVleBuffer.getSumClick());
  }

  @Test
  void testEquals() {
    // test with the same object
    assertEquals(testStudentVleBuffer, testStudentVleBuffer);

    // test with the null object
    assertFalse(testStudentVleBuffer.equals(null));

    // test with an object belonging to a different class
    assertFalse(testStudentVleBuffer.equals(testDate));

    // test with a different object
    assertNotEquals(testStudentVleBuffer,
        new StudentVleBuffer("BBB", testCodePresentation, testDate, testSumClick));
    assertNotEquals(testStudentVleBuffer,
        new StudentVleBuffer(testCodeModule, "2014J", testDate, testSumClick));
    assertNotEquals(testStudentVleBuffer,
        new StudentVleBuffer(testCodeModule, testCodePresentation,"testDate", testSumClick));
    assertFalse(testStudentVleBuffer.equals(
        new StudentVleBuffer(testCodeModule, testCodePresentation, testDate, -111)));
    /*assertTrue(testStudentVleBuffer.equals(
        new StudentVleBuffer(testCodeModule, testCodePresentation, testDate, testSumClick)));*/
  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testCodeModule, testCodePresentation, testDate, testSumClick);
    assertEquals(expectedHashcode, testStudentVleBuffer.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "StudentVleBuffer{" +
        "codeModule='" + testCodeModule + '\'' +
        ", codePresentation='" + testCodePresentation + '\'' +
        ", date='" + testDate + '\'' +
        ", sumClick=" + testSumClick +
        '}';
    assertEquals(expectedString, testStudentVleBuffer.toString());
  }
}