import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class NameTest {

  Name name;
  @BeforeEach
  void setUp() {
    name = new Name("sam","daw");
  }

  @Test
  void testFirstName() {
    name.setFirstName("sameer");
    assertEquals(name.getFirstName(),"sameer");
    assertNotEquals(name.getFirstName(),"sam");
  }

  @Test
  void testLastName() {
    name.setLastName("dawani");
    assertEquals(name.getLastName(),"dawani");
    assertNotEquals(name.getLastName(),"daw");
  }

  @Test
  void testHashCode() {

    assertEquals(name.hashCode(), name.hashCode());
    name.setFirstName("sameer");
    assertEquals(name.hashCode(),name.hashCode());
  }

  @Test
  void testEquals(){
    Beer beer = new Beer();
    assertTrue(name.equals(name));
    assertFalse(name.equals(beer));
    assertFalse(name.equals(null));
    assertTrue(name.equals(new Name("sam","daw")));
  }
}
