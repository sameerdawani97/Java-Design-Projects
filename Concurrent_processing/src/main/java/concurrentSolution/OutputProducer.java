package concurrentSolution;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This class is responsible for producing output data packets and adding them to the output buffer.
 * The thread follows a producer-consumer style to push the data from concurrent hashmap
 * to a blocking queue.
 */
public class OutputProducer implements Runnable {

  private ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> outputMap;
  private BlockingQueue<OutputBufferPacket> outputBufferPackets;
  private AtomicBoolean producerFinished;

  /**
   * Constructor for class OutputProducer
   * @param outputMap - data from output map
   * @param outputBufferPackets - buffer for producer-consumer model
   * @param producerFinished - AtomicBoolean flag to indicate whether the producer is finished
   */
  public OutputProducer(
      ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> outputMap,
      BlockingQueue<OutputBufferPacket> outputBufferPackets, AtomicBoolean producerFinished) {
    this.outputMap = outputMap;
    this.outputBufferPackets = outputBufferPackets;
    this.producerFinished = producerFinished;
  }

  /**
   * Runs the OutputProducer object, retrieving data from the ConcurrentHashMap and adding it
   * to the BlockingQueue until the ConcurrentHashMap is empty and the processing of data is complete.
   * This method runs in its own thread and is designed to work alongside an InputProcessor
   * and an OutputConsumer to form a complete data processing pipeline.
   */
  @Override
  public void run() {
    for(String key: outputMap.keySet()){
      OutputBufferPacket bufferPacket = new OutputBufferPacket(key, outputMap.get(key));
      try {
        outputBufferPackets.put(bufferPacket);
        System.out.println("Adding " + key);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    producerFinished.set(true);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OutputProducer that = (OutputProducer) o;
    return Objects.equals(outputMap, that.outputMap) && Objects.equals(
        outputBufferPackets, that.outputBufferPackets) && Objects.equals(producerFinished,
        that.producerFinished);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputMap, outputBufferPackets, producerFinished);
  }

  @Override
  public String toString() {
    return "OutputProducer{" +
        "outputMap=" + outputMap +
        ", outputBufferPackets=" + outputBufferPackets +
        ", isFinished=" + producerFinished +
        '}';
  }
}
