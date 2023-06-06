package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InputParserTest {
  InputParser inputParser;

  InputParser inputParser1;
  OutputBufferMap outputBufferMap;
  final String csvFile="src/main/resources/student.csv";

  @BeforeEach
  void setUp() {
    outputBufferMap= new OutputBufferMap();
    inputParser = new InputParser(outputBufferMap,csvFile,3);
    inputParser1 = new InputParser(outputBufferMap,csvFile,1);
  }

  @Test
  void parseInput() throws InterruptedException {
    inputParser.parseInput();
    assertEquals(outputBufferMap.getBufferData().size(),22);
  }
  @Test
  void testEquals(){
    assertTrue(inputParser.equals(inputParser));
    assertFalse(inputParser.equals(null));
    assertFalse(inputParser.equals(inputParser1));
  }
  @Test
  void testHashCode(){
    assertEquals(Objects.hash(outputBufferMap,3,csvFile),inputParser.hashCode());
  }

}