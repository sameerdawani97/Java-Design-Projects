package concurrentSolution;

import static concurrentSolution.IntegerAndString.*;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The OutputConsumer class is responsible for consuming output data from the output buffer
 * and writing it to CSV files.
 */
public class OutputConsumer implements Runnable, IntegerAndString {

  private BlockingQueue<OutputBufferPacket> outputBufferPackets;
  private AtomicBoolean producerFinished;

  /**
   * Constructor for class OutputConsumer
   * @param outputBufferPackets buffer for producer-consumer model
   * @param producerFinished AtomicBoolean flag to indicate whether the producer is finished
   */
  public OutputConsumer(BlockingQueue<OutputBufferPacket> outputBufferPackets,
      AtomicBoolean producerFinished) {
    this.outputBufferPackets = outputBufferPackets;
    this.producerFinished = producerFinished;
  }

  /**
   * Consumes data from the output buffer and writes it to a file.
   */
  @Override
  public void run() {
    while (!(outputBufferPackets.isEmpty() && producerFinished.get())) {
      OutputBufferPacket bufferPacket = null;
      try {
        bufferPacket = outputBufferPackets.poll(ONE, TimeUnit.SECONDS);

        if (bufferPacket != null) {
          CSVWriter csvWriter = new CSVWriter(
              new FileWriter(Paths.get(OUTPUT_DIRECTORY + bufferPacket.getFileName() + CSV_EXTENSION).toFile()));
          csvWriter.writeNext(new String[]{LABELS.get(ZERO), LABELS.get(ONE)});

          for (Map.Entry<String, AtomicInteger> mapElement : bufferPacket.getMapOfDateAndSumClick()
              .entrySet()) {
            String key = mapElement.getKey();
            Integer integerValue = (mapElement.getValue().get());
            String stringValue = String.valueOf(integerValue);
            csvWriter.writeNext(new String[]{key, stringValue});
            csvWriter.flush();
          }
        }
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
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
    OutputConsumer that = (OutputConsumer) o;
    return Objects.equals(outputBufferPackets, that.outputBufferPackets)
        && Objects.equals(producerFinished, that.producerFinished);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputBufferPackets, producerFinished);
  }

  @Override
  public String toString() {
    return "OutputConsumer{" +
        "outputBufferPackets=" + outputBufferPackets +
        ", producerFinished=" + producerFinished +
        '}';
  }
}
