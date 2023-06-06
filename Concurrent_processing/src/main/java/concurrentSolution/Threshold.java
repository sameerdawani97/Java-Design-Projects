package concurrentSolution;

import static concurrentSolution.IntegerAndString.*;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class of Thread for threshold file
 */
public class Threshold implements Runnable{
  private ConcurrentHashMap<String,ConcurrentHashMap<String, AtomicInteger>> outputData;
  private Integer thresholdValue;

  /**
   * labels in threshold csv file
   */
  public final List<String> LABEL= Arrays.asList("module_presentation","date","total_clicks");

  /**
   * Constructor for Threshold handling
   * @param outputData outputData
   * @param threshold threshold value
   */
  public Threshold(
      ConcurrentHashMap<String, ConcurrentHashMap<String, AtomicInteger>> outputData,
      Integer threshold) {
    this.outputData = outputData;
    this.thresholdValue = threshold;
  }

  @Override
  public void run() {
    CSVWriter csvWriter = null;
    String fileName="activity-"+thresholdValue.toString()+CSV_EXTENSION;

    try {
      csvWriter = new CSVWriter(new FileWriter(
          Paths.get(OUTPUT_DIRECTORY + fileName).toFile()));

      csvWriter.writeNext(new String[]{LABEL.get(ZERO),LABEL.get(ONE),LABEL.get(TWO)});

      for (Map.Entry<String, ConcurrentHashMap<String, AtomicInteger>> mapElement : outputData.entrySet()) {
        String key = mapElement.getKey();
        for(Map.Entry<String,AtomicInteger> dateHit :mapElement.getValue().entrySet()) {
          if (dateHit.getValue().get() > thresholdValue) {
            csvWriter.writeNext(
                new String[]{key, dateHit.getKey(), String.valueOf(dateHit.getValue().get())});
            csvWriter.flush();
          }
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public int hashCode() {
    return Objects.hash(outputData, thresholdValue);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Threshold that)) {
      return false;
    }
    return outputData.equals(that.outputData) && thresholdValue.equals(that.thresholdValue);
  }
}





