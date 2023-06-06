package concurrentSolution;
import static concurrentSolution.IntegerAndString.*;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is for consumer to consume data from input buffer and acts as producer to produce data in output buffer.
 **/
public class InputConsumer implements Runnable {

  private BlockingQueue<List> inputBuffer;
  private AtomicBoolean producerFinished;
  private OutputBufferMap outputBufferMap;


  /**
   * Constructor for
   * @param inputBuffer inputBuffer
   * @param producerFinished producerFinished
   * @param outputBufferMap outputBufferMap
   */
  public InputConsumer(BlockingQueue<List> inputBuffer, AtomicBoolean producerFinished,
      OutputBufferMap outputBufferMap) {
    this.inputBuffer = inputBuffer;
    this.outputBufferMap = outputBufferMap;
    this.producerFinished = producerFinished;
  }

  /**
   * This run object takes the values from input buffer and map that data to output buffer
   */
  @Override
  public void run() {
    while (!(producerFinished.get() && inputBuffer.isEmpty())) {
      List values = null;

      try {
        values = inputBuffer.poll(ONE, TimeUnit.SECONDS);

        if (values != null) {
          String codeModule = (String) values.get(ZERO);
          String codePresentation = (String) values.get(ONE);
          String day = (String) values.get(FOUR);
          Integer clicks = Integer.parseInt((String) values.get(FIVE));
          StudentVleBuffer studentVleBuffer = new StudentVleBuffer(codeModule, codePresentation, day, clicks);
          outputBufferMap.putIntoBuffer(studentVleBuffer);
        }

      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputBuffer, producerFinished, outputBufferMap);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InputConsumer that)) {
      return false;
    }
    return inputBuffer.equals(that.inputBuffer) && producerFinished.equals(that.producerFinished)
        && outputBufferMap.equals(that.outputBufferMap);
  }

}
