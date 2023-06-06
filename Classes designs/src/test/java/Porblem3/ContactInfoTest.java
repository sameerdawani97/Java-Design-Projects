package Porblem3;
import static org.junit.jupiter.api.Assertions.*;

import Problem3.ContactInfo;
import Problem3.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactInfoTest {

  ContactInfo contactInfo;
  Name name;
  @BeforeEach
  void setUp() {
    contactInfo = new ContactInfo();
    name = new Name("sameer","","dawani")
;
  }

  @Test
  void testName() {
    contactInfo.setName(name);
    assertEquals(name, contactInfo.getName());
  }

  @Test
  void testAddress() {
    contactInfo.setAddress("Apt 101");
    assertEquals("Apt 101", contactInfo.getAddress());
  }

  @Test
  void testPhoneNumber() {
    contactInfo.setPhoneNumber("123456789");
    assertEquals("123456789", contactInfo.getPhoneNumber());
  }

  @Test
  void testEmail() {
    contactInfo.setEmail("same@gmail.com");
    assertEquals("same@gmail.com", contactInfo.getEmail());

  }

  @Test
  void testHashCode() {
    assertTrue(contactInfo.getClass().equals(ContactInfo.class));
    contactInfo.setEmail("same@gmail.com");
    assertEquals(contactInfo.hashCode(), contactInfo.hashCode());

  }

  @Test
  void testEquals() {
    ContactInfo contactInfo1 = new ContactInfo();
    contactInfo1.setEmail("s@gmail.com");
    assertTrue(contactInfo.getClass().equals(ContactInfo.class));
    assertFalse(contactInfo.equals(null));
    assertTrue(contactInfo.equals(contactInfo));
    assertFalse(contactInfo.equals(contactInfo1));


  }
}
