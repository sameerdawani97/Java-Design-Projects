package sequentialSolution;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * A class to check if the directory contains required OULAD csv files
 */
public class OULADFilesChecker {

  private String nameOfDirectory;

  /**
   * Constructor for OULADFilesChecker
   * @param nameOfDirectory - the name of directory
   */
  public OULADFilesChecker(String nameOfDirectory) {
    this.nameOfDirectory = nameOfDirectory;
  }

  /**
   * Constructor for OULADFilesChecker
   */
  public OULADFilesChecker() {}

  /**
   * Gets the name of directory
   * @return the name of directory
   */
  public String getNameOfDirectory() {
    return this.nameOfDirectory;
  }

  /**
   * Sets the name of directory
   * @param nameOfDirectory - the name of directory
   */
  public void setNameOfDirectory(String nameOfDirectory) {
    this.nameOfDirectory = nameOfDirectory;
  }

  /**
   * A method to check if the directory contains required files
   * @param nameOfDirectory - name of the directory containing the files
   * @return true if files are contained in the directory, false otherwise
   */
  public boolean checkContaining(String nameOfDirectory) {
    boolean isContained = new File(Paths.get(nameOfDirectory + "studentVle.csv").toString()).exists()
        && new File(nameOfDirectory, "courses.csv").exists();
    return isContained;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OULADFilesChecker that = (OULADFilesChecker) o;
    return Objects.equals(nameOfDirectory, that.nameOfDirectory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nameOfDirectory);
  }

  @Override
  public String toString() {
    return "OULADFilesChecker{" +
        "nameOfDirectory='" + nameOfDirectory + '\'' +
        '}';
  }
}
