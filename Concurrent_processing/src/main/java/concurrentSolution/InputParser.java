package concurrentSolution;

import static concurrentSolution.IntegerAndString.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class follows producer consumer approach to access data from csv file
 **/
public class InputParser {

  private OutputBufferMap outputBufferMap;
  private int numberOfProcessors;
  private String studentCsvFile;

  /**
   * parameterized constructor
   * @param outputBufferMap OUTPUT MAPPING
   * @param studentCsvFile csv file name
   * @param numberOfProcessors number of threads
   */
  public InputParser(OutputBufferMap outputBufferMap, String studentCsvFile, int numberOfProcessors) {
    this.outputBufferMap = outputBufferMap;
    this.numberOfProcessors = numberOfProcessors;
    this.studentCsvFile = studentCsvFile;
  }

  /**
   * This method parses the input from csv using producer consumer approach of multi threading
   * @throws InterruptedException InterruptedException
   */
  void parseInput() throws InterruptedException {
    BlockingQueue<List> inputBuffer = new LinkedBlockingQueue<>(BUFFER_SIZE);
    AtomicBoolean producerFinished = new AtomicBoolean(false);
    Thread producer = new Thread(new InputProducer(inputBuffer, producerFinished, this.studentCsvFile));

    producer.start();

    List<Thread> consumerThreads = new ArrayList<>();
    for (int i = ZERO; i < numberOfProcessors; i++) {
      Thread consumer = new Thread(
          new InputConsumer(inputBuffer, producerFinished, this.outputBufferMap));
      consumer.start();

      consumerThreads.add(consumer);
    }
    producer.join();
    for (int i = ZERO; i < numberOfProcessors; i++) {
      consumerThreads.get(i).join();
    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InputParser that)) {
      return false;
    }
    return numberOfProcessors == that.numberOfProcessors && outputBufferMap.equals(that.outputBufferMap)
        && studentCsvFile.equals(that.studentCsvFile);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputBufferMap, numberOfProcessors, studentCsvFile);
  }





}
