package concurrentSolution;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class for packaging output buffer data
 */
public class OutputBufferPacket {

  private String fileName;
  private ConcurrentHashMap<String, AtomicInteger> mapOfDateAndSumClick;

  /**
   * Constructor with the given name of a file and buffer data.
   * @param fileName - the name of a file, String
   * @param mapOfDateAndSumClick - date and sumClick, the buffer data as a ConcurrentHashMap
   */
  public OutputBufferPacket(String fileName, ConcurrentHashMap<String, AtomicInteger> mapOfDateAndSumClick) {
    this.fileName = fileName;
    this.mapOfDateAndSumClick = mapOfDateAndSumClick;
  }

  /**
   * Gets the name of a file.
   * @return the name of a file
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * Gets the buffer data.
   * @return the buffer data
   */
  public ConcurrentHashMap<String, AtomicInteger> getMapOfDateAndSumClick() {
    return this.mapOfDateAndSumClick;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutputBufferPacket that = (OutputBufferPacket) o;
    return Objects.equals(fileName, that.fileName) && Objects.equals(mapOfDateAndSumClick,
        that.mapOfDateAndSumClick);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileName, mapOfDateAndSumClick);
  }

  @Override
  public String toString() {
    return "OutputBufferPacket{" +
        "fileName='" + fileName + '\'' +
        ", bufferData=" + mapOfDateAndSumClick +
        '}';
  }
}
