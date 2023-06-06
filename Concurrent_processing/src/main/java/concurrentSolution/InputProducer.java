package concurrentSolution;

import static concurrentSolution.IntegerAndString.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Input producer class that reads csv file and puts the data into input buffer to process by consumer
 **/
public class InputProducer implements Runnable {
  private BlockingQueue inputBuffer;
  private String studentsCsvFile;
  private AtomicBoolean producerFinished;


  /**
   * Parameterized Constructor for InputProducer class
   * @param inputBuffer inputBuffer
   * @param producerFinished producerFinished
   * @param studentsCsvFile studentsCsvFile file name
   */
  public InputProducer(BlockingQueue inputBuffer, AtomicBoolean producerFinished,
      String studentsCsvFile) {
    this.inputBuffer = inputBuffer;
    this.studentsCsvFile = studentsCsvFile;
    this.producerFinished = producerFinished;
  }

  /**
   * Run method is defined in thread class to be created by developers. This run method reads the file and store
   * in input buffer
   */
  @Override
  public void run() {
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(studentsCsvFile));
      br.readLine();
      String line;
      while ((line = br.readLine()) != null) {

        String[] studentValues = line.split(SEPARATOR);
        for (int i = ZERO; i < studentValues.length; i++) {
          studentValues[i] = studentValues[i].replace("\"", "");
        }
        inputBuffer.put(Arrays.asList(studentValues));

      }
      producerFinished.set(true);

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public int hashCode() {
    return Objects.hash(inputBuffer, studentsCsvFile, producerFinished);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InputProducer that)) {
      return false;
    }
    return inputBuffer.equals(that.inputBuffer) && studentsCsvFile.equals(that.studentsCsvFile)
        && producerFinished.equals(that.producerFinished);
  }
}
