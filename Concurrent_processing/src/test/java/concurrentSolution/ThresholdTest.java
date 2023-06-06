package concurrentSolution;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ThresholdTest {
  Threshold threshold;
  ConcurrentHashMap<String,ConcurrentHashMap<String, AtomicInteger>> map1;
  int thresholdValue=1111;

  @BeforeEach
  void setUp() {
    map1= new ConcurrentHashMap<>();
    threshold= new Threshold(map1,thresholdValue);
  }

  @Test
  void testHashCode() {
    assertEquals(Objects.hash(map1,thresholdValue),threshold.hashCode());
  }

  @Test
  void testEquals() {
    assertEquals(threshold,threshold);
    assertFalse(threshold.equals(null));
    assertEquals(threshold, new Threshold(map1,thresholdValue));
  }
}