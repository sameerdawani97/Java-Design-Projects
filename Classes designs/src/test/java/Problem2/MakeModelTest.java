package Problem2;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MakeModelTest {

  MakeModel makeModel;
  @BeforeEach
  void setUp() {
    makeModel = new MakeModel("Toyota","Accord");
  }

  @Test
  void testMake() {
    makeModel.setVehicleMake("Audi");
    assertEquals("Audi",makeModel.getVehicleMake());
    assertNotEquals("Toyota",makeModel.getVehicleMake());
  }

  @Test
  void testModel() {
    makeModel.setVehicleModel("A6");
    assertEquals("A6",makeModel.getVehicleModel());
    assertNotEquals("Accord",makeModel.getVehicleModel());
  }

  @Test
  void testMakeModel() {
    MakeModel makeModel1 = new MakeModel("Toyota","Accord");
    assertEquals(makeModel1,makeModel1);
    assertNotEquals(makeModel,null);

  }

  @Test
  void testHashCode() {
    MakeModel makeModel1 = new MakeModel("Audi","A6");
    assertNotEquals(makeModel.hashCode(),makeModel1.hashCode());
    assertEquals(makeModel.hashCode(),makeModel.hashCode());

  }

  @Test
  void testEquals() {
    MakeModel makeModel1 = new MakeModel("Audi","A6");
    assertTrue(makeModel.equals(makeModel));
    assertFalse(makeModel.equals(makeModel1));
    assertFalse(makeModel.equals(null));
    assertFalse(makeModel1.equals(null));
    assertFalse(makeModel.getVehicleMake().equals(makeModel1.getVehicleMake()));
    assertFalse(makeModel.getVehicleModel().equals(makeModel1.getVehicleModel()));
    assertTrue(makeModel.getClass().equals(makeModel1.getClass()));


  }




}
