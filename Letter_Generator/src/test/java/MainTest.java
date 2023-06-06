import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MainTest {


  @Test
  void testMain() throws Exception {
    Main.main(
        new String[]{"--email", "--email-template", "email-template.txt", "--output-dir",
            "emails", "--csv-file", "insurance-company-members.csv"});
    Main.main(
        new String[]{"--letter", "--letter-template", "letter-template.txt", "--output-dir",
            "letter", "--csv-file", "insurance-company-members.csv"});
  }
}
