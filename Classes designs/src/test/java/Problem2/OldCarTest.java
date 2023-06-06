package Problem2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OldCarTest {

  OldCar oldCar;
  @BeforeEach
  void setUp() {
    oldCar = new OldCar();
  }

  @Test
  void testVehicleId() {
    oldCar.setVehicleId("123");
    assertEquals("123",oldCar.getVehicleId());
  }
  @Test
  void testManufacturingYear() {
    oldCar.setManufacturingYear(2000);
    assertEquals(2000,oldCar.getManufacturingYear());
  }
  @Test
  void testMsrp() {
    oldCar.setMsrp(20.0);
    assertEquals(20.0,oldCar.getMsrp());
    assertNotEquals(10.0,oldCar.getMsrp());
  }

  @Test
  void testMakeModel() {
    MakeModel makeModel = new MakeModel("Toyota","Accord");
    oldCar.setMakeModel(makeModel);
    assertEquals(makeModel,oldCar.getMakeModel());
  }

  @Test
  void testMileage() {
    oldCar.setMileage(10000);
    assertEquals(10000,oldCar.getMileage());
  }

  @Test
  void testNumOfPreviousOwners() {
    oldCar.setNumberOfPreviousOwners(2);
    assertEquals(2,oldCar.getNumberOfPreviousOwners());
    assertNotEquals(5,oldCar.getNumberOfPreviousOwners());

  }

  @Test
  void testNumOfMinorAccidents() {
    oldCar.setNumberOfMinorAccidents(5);
    assertEquals(5,oldCar.getNumberOfMinorAccidents());
    assertNotEquals(2,oldCar.getNumberOfMinorAccidents());
  }

  @Test
  void testHashCode() {
    assertEquals(oldCar.hashCode(),oldCar.hashCode());
  }

  @Test
  void testEquals() {
    OldCar oldCar1 = new OldCar();
    assertTrue(oldCar.equals(oldCar));
    assertFalse(oldCar.equals(null));
    oldCar.setManufacturingYear(2001);
    assertTrue(oldCar.equals(oldCar));
    assertTrue(oldCar.getClass().equals(oldCar1.getClass()));
  }


}
