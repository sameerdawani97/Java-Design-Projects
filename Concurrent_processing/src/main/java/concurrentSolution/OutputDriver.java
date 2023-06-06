package concurrentSolution;

import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The OutputDriver class is responsible for driving the output file generation process.
 */
public class OutputDriver implements IntegerAndString {

  private String studentVIeFile;
  private OutputBufferMap outputBufferMap;
  private String courseFileName;

  private InputParser inputParser;

  private Integer threshold;

  private CourseCSVProcessor courseCSVProcessor;

  /**
   * Constructor for OutputDriver
   * @param args - string[] args from the main method
   */
  public OutputDriver(String[] args) {
    this.studentVIeFile = args[ZERO] + "/studentVle.csv";
    this.courseFileName = args[ZERO] + "/courses.csv";
    if (args.length > ONE) {
      this.threshold = Integer.parseInt(args[ONE]);
    } else {
      this.threshold = null;
    }
    this.outputBufferMap = new OutputBufferMap();
    this.inputParser = new InputParser(outputBufferMap, studentVIeFile, NUMBER_OF_FILE_WRITER_THREADS);
    courseCSVProcessor = new CourseCSVProcessor(courseFileName);
  }

  /**
   * A method that drives multiple threads to solve the problem of analysing the csv files
   * @throws InterruptedException when threads are interrupted unexpectedly
   * @throws IOException when file reading issues come up
   */
  public void createSummaryCSV() throws InterruptedException, IOException {

    inputParser.parseInput();
    courseCSVProcessor.createOutputFiles();

    BlockingQueue<OutputBufferPacket> outputBuffer = new LinkedBlockingQueue<>(BUFFER_SIZE);
    AtomicBoolean producerFinished = new AtomicBoolean(false);
    Thread producer = new Thread(
        new OutputProducer(outputBufferMap.getBufferData(), outputBuffer, producerFinished));
    producer.start();

    List<Thread> fileWriterThreads = new ArrayList<>();
    for(int i = ZERO; i < NUMBER_OF_FILE_WRITER_THREADS; i++){
      Thread fileWriter = new Thread(new OutputConsumer(outputBuffer,producerFinished));
      fileWriter.start();
      fileWriterThreads.add(fileWriter);
    }

    producer.join();
    for(int i = ZERO; i < NUMBER_OF_FILE_WRITER_THREADS; i++) {
      fileWriterThreads.get(i).join();
    }
    if(threshold != null) {
      Thread threshold= new Thread(new Threshold(outputBufferMap.getBufferData(), this.threshold));
      threshold.start();
      threshold.join();
    }
  }
}
