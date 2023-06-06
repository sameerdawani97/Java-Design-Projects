package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InputConsumerTest {

  InputConsumer inputConsumer;
  BlockingQueue<List> q;
  AtomicBoolean pFinished;
  OutputBufferMap outputMap;
  @BeforeEach
  void setUp() {
    q= new LinkedBlockingQueue();
    pFinished= new AtomicBoolean(false);
    outputMap= new OutputBufferMap();
    inputConsumer= new InputConsumer(q,pFinished,outputMap);
  }

  @Test
  void testHashCode() {
    int hashCode= Objects.hash(q,pFinished,outputMap);
    assertEquals(hashCode,inputConsumer.hashCode());
  }

  @Test
  void testEquals() {
    assertEquals(inputConsumer,inputConsumer);
    assertFalse(inputConsumer.equals(null));
    assertTrue(inputConsumer.equals(new InputConsumer(q,pFinished,outputMap)));
  }
}