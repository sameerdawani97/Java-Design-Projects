import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class to parse json file to hashmap
 */
public class JsonParser {

  private String directoryPath;
  private List<File> filesList;
  /**
   * Constructor for JsonParser
   * @param directoryPath the directory path storing the json files
   * @throws IOException input or output exception
   * @throws ParseException parseException
   */
  public JsonParser(String directoryPath) throws IOException, ParseException {
    this.directoryPath=directoryPath;
    File folder = new File(this.directoryPath);
    this.filesList = getJsonFiles(folder);
  }

  /**
   * This method returns the hashmap of json file setting key as unTerminal words
   * @param fileName filename of json file
   * @return parsedJsonHashMap
   * @throws IOException input/output exception
   * @throws ParseException parseException by JsonParser class
   */
  public HashMap<String, Object> parseJsonToHashMap(String fileName) throws IOException, ParseException {
    JSONParser parser = new JSONParser();

    Object obj = parser.parse(new FileReader(fileName));
    JSONObject json = (JSONObject) obj;

    HashMap<String, Object> parsedJsonHashMap = new Gson().fromJson(String.valueOf(json), HashMap.class);
    return parsedJsonHashMap;

  }

  /**
   * This method stores the json files
   * @return json files name/address
   */

  private List<File> getJsonFiles(File directory){
    //extension for json files, ending with this extension
    String extensionJson = ".json";
    List<File> result= new ArrayList<>();
    for(File file:directory.listFiles()){
      if(file.getName().endsWith(extensionJson)){
        result.add(file);
      }
    }
    return result;
  }

  /**
   * This method returns the list of grammar titles
   * @return return string list containing files' title
   * @throws IOException input/output exception
   * @throws ParseException parseException
   */
  public List<String> getGrammarTitles() throws IOException, ParseException {
    List<String> result=new ArrayList<>();
    String grammarTitle = "grammarTitle";
    for (int j = 0; j < this.filesList.size(); j++) {
      JSONParser parser = new JSONParser();
      Object obj = parser.parse(new FileReader(this.filesList.get(j)));
      JSONObject json = (JSONObject) obj;
      result.add(json.get(grammarTitle).toString());
    }

    return result;
  }

  /**
   * This method gets the grammar object containing rules of grammar
   * @param index index given by user
   * @return grammar object
   * @throws IOException input/output exception
   * @throws ParseException ParseException
   */
  public Grammar getGrammarByUserInput(Integer index) throws IOException, ParseException {

    Grammar grammar;
    grammar= new Grammar(this.parseJsonToHashMap(String.valueOf(this.filesList.get(index))));
    return grammar;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonParser that = (JsonParser) o;
    return Objects.equals(directoryPath, that.directoryPath) && Objects.equals(
        filesList, that.filesList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(directoryPath, filesList);
  }

  @Override
  public String toString() {
    return "JsonParser{" +
        "directoryPath='" + directoryPath + '\'' +
        ", filesList=" + filesList +
        '}';
  }
}
