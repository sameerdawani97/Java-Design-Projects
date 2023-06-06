package concurrentSolution;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class represents a buffer of output data that has been produced or populated from
 * input processor threads and consumed or accessed by file driver threads
 */
public class OutputBufferMap {

  private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> bufferData;

  /**
   * Constructor with the given buffer data.
   * @param bufferData - the buffer data as a ConcurrentHashMap
   */
  public OutputBufferMap(
      ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> bufferData) {
    this.bufferData = bufferData;
  }

  /**
   * Default Constructor
   */
  public OutputBufferMap() {
    this.bufferData = new ConcurrentHashMap<>();
  }

  /**
   * Gets the buffer data.
   * @return the buffer data
   */
  public ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> getBufferData() {
    return this.bufferData;
  }

  /**
   * A method to add {@code studentVleBuffer} to the buffer, atomically designed to avoid race conditions
   * @param studentVleBuffer - student data for output Map
   */
  public void putIntoBuffer(StudentVleBuffer studentVleBuffer) {

    String key = studentVleBuffer.getCodeModule() + "_" + studentVleBuffer.getCodePresentation();
    ConcurrentHashMap<String, AtomicInteger> dateAndSumClick = new ConcurrentHashMap<>();

    dateAndSumClick.put(studentVleBuffer.getDate(), new AtomicInteger(studentVleBuffer.getSumClick()));

    if(bufferData.putIfAbsent(key,dateAndSumClick) != null) {
      ConcurrentHashMap<String, AtomicInteger> prevData = bufferData.get(key);
      if(prevData.putIfAbsent(studentVleBuffer.getDate(),
          new AtomicInteger(studentVleBuffer.getSumClick())) != null) {
        prevData.computeIfPresent(studentVleBuffer.getDate(),(k,value) ->
            new AtomicInteger(value.addAndGet(studentVleBuffer.getSumClick())));
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutputBufferMap that = (OutputBufferMap) o;
    return Objects.equals(bufferData, that.bufferData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bufferData);
  }

  @Override
  public String toString() {
    return "OutputBufferMap{" +
        "bufferData=" + bufferData +
        '}';
  }
}

