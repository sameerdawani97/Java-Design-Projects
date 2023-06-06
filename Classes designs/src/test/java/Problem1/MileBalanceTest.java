package Problem1;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MileBalanceTest {

  MilesBalance milesBalance;
  @BeforeEach
  void setUp() {
    milesBalance = new MilesBalance(2000,1000,500);
  }

  @Test
  void testTotalMiles() {
    milesBalance.setTotalMiles(500);
    assertEquals(500,milesBalance.getTotalMiles());
    assertNotEquals(600,milesBalance.getTotalMiles());

  }

  @Test
  void testEarnedMiles() {
    milesBalance.setEarnedMiles(200);
    assertEquals(200,milesBalance.getEarnedMiles());
    assertNotEquals(600,milesBalance.getEarnedMiles());

  }

  @Test
  void testExpiringMiles() {
    milesBalance.setExpiringMiles(200);
    assertEquals(200,milesBalance.getExpiringMiles());
    assertNotEquals(600,milesBalance.getExpiringMiles());

  }

  @Test
  void testHashCode() {

    assertEquals(milesBalance.hashCode(), milesBalance.hashCode());
    milesBalance.setExpiringMiles(200);
    assertEquals(milesBalance.hashCode(),milesBalance.hashCode());
  }

  @Test
  void testEquals(){
    assertTrue(milesBalance.getClass().equals(MilesBalance.class));
    MilesBalance milesBalance1 = new MilesBalance(1000,200,500);
    assertFalse(milesBalance.equals(null));
    assertTrue(milesBalance.equals(milesBalance));
    assertFalse(milesBalance.equals(new MilesBalance(200,500,100)));
    assertFalse(milesBalance.equals(milesBalance1));
  }
}
