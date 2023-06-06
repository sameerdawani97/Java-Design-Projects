package sequentialSolution;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * Class to create new summary files if directory contains the OULAD csv files
 */
public class SummaryCSVCreator {

  private String directory;
  private CourseParser courseParser;
  private StudentParser studentParser;
  private OULADFilesChecker ouladFilesChecker = new OULADFilesChecker();

  /**
   * Constructor for class SummaryCSVCreator
   * @param directory - Name of the directory
   * @param courseParser - Parsed course csv file
   * @param studentParser - Parsed student csv file
   * @param ouladFilesChecker - Check if the directory contains required OULAD csv files
   */
  public SummaryCSVCreator(String directory, CourseParser courseParser, StudentParser studentParser,
      OULADFilesChecker ouladFilesChecker) {
    this.directory = directory;
    this.courseParser = courseParser;
    this.studentParser = studentParser;
    this.ouladFilesChecker = ouladFilesChecker;
  }

  /**
   * Constructor for class SummaryCSVCreator
   */
  public SummaryCSVCreator() {}

  /**
   * Gets the directory
   * @return directory
   */
  public String getDirectory() {
    return this.directory;
  }

  /**
   * Sets the directory
   * @param directory - directory
   */
  public void setDirectory(String directory) {
    this.directory = directory;
  }

  /**
   * A method to create summary csv files
   * @param directory - Name of the directory, take the directory as an argument
   * @throws IOException Input/Output exception
   * @throws FileNotFoundException thrown when the file is not found
   */
  public void createSummaryCSV(String directory) throws IOException, FileNotFoundException {

    studentParser = new StudentParser(directory + "/studentVle.csv");
    courseParser = new CourseParser(directory + "/courses.csv");
    
    String codeModule = "code_module";
    String codePresentation = "code_presentation";

    if (ouladFilesChecker.checkContaining(directory)) {
      HashMap<String, ArrayList<String>> storedCourse = courseParser.parseCourse();
      ArrayList<String> codeModuleList = storedCourse.get(codeModule);
      ArrayList<String> codePresentationList = storedCourse.get(codePresentation);
      HashMap<String, HashMap<String, Integer>> courseStorage = new HashMap<>();

      for (int i = 0; i < codeModuleList.size(); i++) {
        courseStorage.put(codeModuleList.get(i) + "_" + codePresentationList.get(i), new HashMap<>());
      }

      HashMap<String, HashMap<String, Integer>> finalStorage = studentParser.parseStudent(courseStorage);
      String outputDirectory = "src/main/resources/sequential_files/";

      for (Entry<String, HashMap<String, Integer>> mapElement : finalStorage.entrySet()) {
        String key = mapElement.getKey();
        Files.createDirectories(Paths.get(outputDirectory));
        CSVWriter csvWriter = new CSVWriter(new FileWriter(outputDirectory + key + ".csv"));
        csvWriter.writeNext(new String[]{"date", "total_clicks"});

        for(Entry<String,Integer> dateHit :mapElement.getValue().entrySet()) {
          csvWriter.writeNext(new String[]{dateHit.getKey(), String.valueOf(dateHit.getValue())});
          csvWriter.flush();
        }
      }

    } else {
      throw new FileNotFoundException("File is not found!");
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
    SummaryCSVCreator that = (SummaryCSVCreator) o;
    return Objects.equals(directory, that.directory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(directory);
  }

  @Override
  public String toString() {
    return "SummaryCSVCreator{" +
        "directory='" + directory + '\'' +
        '}';
  }
}
