import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 * A class to choose a folder/directory where all grammars in that directory are loaded/read in.
 */
public abstract class ChooseDirectory {

  /**
   * Constructor for class ChooseDirectory
   */
  public ChooseDirectory() {
  }

  /**
   * A helper method returns the directory after the user select a directory,
   * using JFileChooser library
   * @return the selected path of the directory.
   * @throws IOException if the current directory is ambiguous
   */
  public String LoadInFile() throws IOException {
    String fileName = "" ;
    File file = new File(new File(".").getCanonicalPath());
    JFileChooser jFileChooser = new JFileChooser();
    jFileChooser.setCurrentDirectory(file);
    jFileChooser.setDialogTitle("Please choose the directory of your grammar files.");
    jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int result = jFileChooser.showOpenDialog(null);

    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jFileChooser.getSelectedFile();
      try {
        if (selectedFile != null) {
          fileName = selectedFile.getCanonicalPath();
        }
      } catch (IOException e) {
      }
    }

    return fileName;
  }
}
