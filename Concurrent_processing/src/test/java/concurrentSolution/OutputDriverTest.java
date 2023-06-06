package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OutputDriverTest {

  OutputDriver testOutputDriver, testOutputDriver1;
  String[] testArgs = {"src/main/resources/", "11111"};

  @BeforeEach
  void setUp() {
    testOutputDriver = new OutputDriver(testArgs);
    testOutputDriver1 = new OutputDriver(new String[]{testArgs[0]});
  }

  @Test
  void createSummaryCSV() throws InterruptedException, IOException {
    testOutputDriver.createSummaryCSV();
    File directory=new File("src/main/resources/concurrent_files/");
    assertEquals(23,directory.list().length);
  }
  @Test
  void equalsTest() {
    assertTrue(testOutputDriver.equals(testOutputDriver));
    assertFalse(testOutputDriver.equals(null));
    assertNotEquals(testOutputDriver,new OutputDriver(testArgs));
    assertFalse(testOutputDriver.equals(testOutputDriver1));
  }
  @Test
  void hashCodeTest() {
    assertEquals(Objects.hashCode(testOutputDriver), testOutputDriver.hashCode());
  }

}