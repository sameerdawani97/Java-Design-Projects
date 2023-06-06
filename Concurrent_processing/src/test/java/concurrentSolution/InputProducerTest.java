package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InputProducerTest {

  InputProducer inputProducer;
  BlockingQueue<List> q;
  AtomicBoolean pFinished;
  String csvFile="src/main/resources/student.csv";

  @BeforeEach
  void setUp() {
    q= new LinkedBlockingQueue<>();
    pFinished = new AtomicBoolean(false);
    inputProducer= new InputProducer(q,pFinished,csvFile);
  }

  @Test
  void testHashCode() {
    int hashCode= Objects.hash(q,csvFile,pFinished);
    assertEquals(hashCode,inputProducer.hashCode());
  }

  @Test
  void testEquals() {
    assertEquals(inputProducer,inputProducer);
    assertFalse(inputProducer.equals(null));
    assertEquals(inputProducer, new InputProducer(q,pFinished,csvFile));
  }
}