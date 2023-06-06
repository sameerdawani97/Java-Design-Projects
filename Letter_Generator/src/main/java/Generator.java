import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;


/**
 * This is generator class to generate output files for each customer
 */
public class Generator {
  private String outputFileName = "[[email]]";
  private CsvReader csvReader = new CsvReader();
  private TemplateProcessor templateProcessor = new TemplateProcessor();
  private Set<Map<String,String>> customerCollection = new HashSet<>();

  /**
   * Method to generate files for every customer in our csv
   * @param templateName templateName
   * @param outDir outDir
   * @param csvFile csvFile
   * @throws IOException throws exception for File
   * @throws Exception throws general exception
   */
  public void generateFiles(String templateName, String outDir, String csvFile) throws Exception, IOException {
    customerCollection = csvReader.getCustomerCollection(csvFile);
    try{
      for (Map<String, String> customerMap : customerCollection) {
        String parsedTemplate = templateProcessor.parseTemplate(templateName,customerMap);
        String outputFileName = customerMap.get(this.outputFileName) + ".txt";
        BufferedWriter outputFile = new BufferedWriter(
            new OutputStreamWriter(
                new FileOutputStream(outDir + File.separator + outputFileName), "UTF8"));
        outputFile.write(parsedTemplate);
        outputFile.flush();
        outputFile.close();
      }
    }
    catch (IOException e){
      // Handle the IOException here
      System.out.println("An error occurred while accessing the file: " + e.getMessage());
    }
    catch (Exception e){
      // Handle the Exception here
      System.out.println("An error occurred: " + e.getMessage());
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
    Generator generator = (Generator) o;
    return Objects.equals(outputFileName, generator.outputFileName)
        && Objects.equals(csvReader, generator.csvReader) && Objects.equals(
        templateProcessor, generator.templateProcessor) && Objects.equals(
        customerCollection, generator.customerCollection);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outputFileName, csvReader, templateProcessor, customerCollection);
  }

  @Override
  public String toString() {
    return "Generator{" +
        "outputFileName='" + outputFileName + '\'' +
        ", csvReader=" + csvReader +
        ", templateProcessor=" + templateProcessor +
        ", customerCollection=" + customerCollection +
        '}';
  }
}
