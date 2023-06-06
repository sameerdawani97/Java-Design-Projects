package Problem1;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FlyerStoreTest {
  FlyerStore flyerStore;

  @BeforeEach
  void setUp() {
    flyerStore = new FlyerStore();
  }

  @Test
  void testCreateFlyer() {
    Flyer flyer = flyerStore.createFlyer("123456789012","sam@gmail.com",
        new MilesBalance(1000,500,500),
        new Name("sam","","daw"),flyerStore);

    assertEquals(flyerStore.getFlyerStore().get("123456789012"),flyer);
  }

  @Test
  void testFlyerStore() {

    flyerStore.setFlyerStore(new HashMap<String,Flyer>());
    assertEquals(flyerStore.getFlyerStore(), new FlyerStore().getFlyerStore());
  }
  @Test
  void testHashCode() {
    assertEquals(flyerStore.hashCode(), flyerStore.hashCode());
    HashMap<String,Flyer> current = new HashMap<String,Flyer>();
    current.put("123",new Flyer());
    assertNotEquals(current.hashCode(),flyerStore.getFlyerStore().hashCode());

  }

  @Test
  void equals(){
    assertFalse(flyerStore.equals(null));
    assertTrue(flyerStore.equals(new FlyerStore()));
    assertTrue(flyerStore.equals(flyerStore));
  }

}
