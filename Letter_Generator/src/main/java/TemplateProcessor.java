import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * Class for template processing
 */
public class TemplateProcessor {

  private StringBuilder template;

  /**
   * Method to return string of template after replacement of placeholders
   * @param filename filename
   * @param placeholders placeholders
   * @return template
   * @throws IOException when error occurs during accessing file
   */
  public String parseTemplate(String filename, Map<String, String> placeholders) throws IOException {
    template = new StringBuilder();
    try{
      // Read the template file into a string
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      String line;
      while ((line = reader.readLine()) != null) {
        template.append(line).append(System.lineSeparator());
      }
      reader.close();

      // Replace the placeholders with their values
      for (Map.Entry<String, String> entry : placeholders.entrySet()) {
        String placeholder = entry.getKey();
        String value = entry.getValue();
        template = new StringBuilder(template.toString().replace(placeholder, value));
      }

    }
    catch (IOException e) {
      // Handle the IOException here
      System.out.println("An error occurred while accessing the file: " + e.getMessage());
    }
    return template.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemplateProcessor that = (TemplateProcessor) o;
    return Objects.equals(template, that.template);
  }

  @Override
  public int hashCode() {
    return Objects.hash(template);
  }

  @Override
  public String toString() {
    return "TemplateProcessor{" +
        "template=" + template +
        '}';
  }
}
