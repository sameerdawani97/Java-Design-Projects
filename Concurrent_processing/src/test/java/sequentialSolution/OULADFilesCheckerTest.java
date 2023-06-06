package sequentialSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OULADFilesCheckerTest {

  private OULADFilesChecker testOULADFilesChecker;
  private String testNameOfDirectory;

  @BeforeEach
  void setUp() {
    testNameOfDirectory = "src/main/resources/";
    testOULADFilesChecker = new OULADFilesChecker(testNameOfDirectory);
  }

  @Test
  void getNameOfDirectory() {
    assertEquals(testNameOfDirectory, testOULADFilesChecker.getNameOfDirectory());
  }

  @Test
  void setNameOfDirectory() {
    testNameOfDirectory = "src/main/java/";
    testOULADFilesChecker.setNameOfDirectory(testNameOfDirectory);
    assertEquals(testNameOfDirectory, testOULADFilesChecker.getNameOfDirectory());
  }

  @Test
  void checkContaining() {

    // test with directory contains both required files
    boolean isContained = testOULADFilesChecker.checkContaining(testNameOfDirectory);
    assertTrue(isContained);

    // test with directory missing both required files
    isContained = testOULADFilesChecker.checkContaining("src/main/java/");
    assertFalse(isContained);

    // test with non-existing directory
    isContained = testOULADFilesChecker.checkContaining("non-existing/directory");
    assertFalse(isContained);

  }

  @Test
  void testEquals() {

    // test with the same object
    assertEquals(testOULADFilesChecker, testOULADFilesChecker);

    // test with the null object
    assertFalse(testOULADFilesChecker.equals(null));

    // test with an object belonging to a different class
    assertFalse(testOULADFilesChecker.equals(testNameOfDirectory));

    // test with a different object
    assertNotEquals(testOULADFilesChecker, new OULADFilesChecker("java"));

  }

  @Test
  void testHashCode() {
    int expectedHashcode = Objects.hash(testOULADFilesChecker.getNameOfDirectory());
    assertEquals(expectedHashcode, testOULADFilesChecker.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "OULADFilesChecker{" +
        "nameOfDirectory='" + testNameOfDirectory + '\'' +
        '}';
    assertEquals(expectedString, testOULADFilesChecker.toString());
  }
}