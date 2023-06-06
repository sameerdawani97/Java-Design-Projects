package Porblem3;
import static org.junit.jupiter.api.Assertions.*;

import Problem3.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NameTest {

  Name name;
  @BeforeEach
  void setUp() {
    name = new Name("sameer","","daw");
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
  void testMiddleName() {
    name.setMiddleName("example");
    assertEquals(name.getMiddleName(),"example");
    assertNotEquals(name.getMiddleName(),"ds");
  }

  @Test
  void testHashCode() {

    assertEquals(name.hashCode(), name.hashCode());
    name.setFirstName("sameer");
    assertEquals(name.hashCode(),name.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(name.getClass().equals(Name.class));
    assertTrue(name.equals(name));
    assertFalse(name.equals(null));
    assertFalse(name.equals(new Problem1.Name("same","","d")));
  }
}
