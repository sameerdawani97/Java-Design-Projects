package Problem1;

import static org.junit.jupiter.api.Assertions.*;

import Problem1.exceptions.customException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositTest {
  Deposit deposit;
  Deposit deposit1;

  @BeforeEach
  void setUp() {
    deposit = new Deposit();
    deposit1 = new Deposit(2000, "123456789012",new Name("Sam","","Daw"));
  }

  @Test
  void testDepositRangeException() {
    try {
      Deposit deposit2 = new Deposit(500, "123456789012",
          new Name("Sam", "", "Daw"));
      fail("Invalid deposit...Deposit range should be between 1000 and 10000");
    } catch (customException e) {
      if (e.getMessage() == "Invalid deposit...Deposit range should be between 1000 and 10000") {
        assertEquals("Invalid deposit...Deposit range should be between 1000 and 10000",
            e.getMessage());
      }
    }
  }

  @Test
  void testDepositAmount() {
    deposit.setDepositAmount(2000);
    assertEquals(2000,deposit.getDepositAmount());
  }

  @Test
  void testAccountId() {
    deposit.setAccountId("098765432112");
    assertEquals("098765432112",deposit.getAccountId());
  }

  @Test
  void testRecipientName() {
    deposit.setRecipientName(new Name("Sameer","","Daw"));
    assertEquals(new Name("Sameer","","Daw"),deposit.getRecipientName());
  }

  @Test
  void testDepositRange() {
    assertFalse(deposit.checkDepositRange(500));
    assertTrue(deposit.checkDepositRange(2000));
  }

  @Test
  void testHashcode() {
    assertEquals(deposit.hashCode(),deposit.hashCode());
    assertNotEquals(deposit.hashCode(),deposit1.hashCode());
  }

  @Test
  void testEquals() {

    assertFalse(deposit.equals(deposit1));
    assertTrue(deposit.equals(new Deposit()));
    deposit.setAccountId("123456789012");
    assertTrue(deposit.getAccountId().equals(deposit1.getAccountId()));
    assertTrue(deposit.equals(deposit));
    assertFalse(deposit1.equals(null));
    assertFalse(deposit.equals(null));

  }
}
