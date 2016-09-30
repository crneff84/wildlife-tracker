import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class UnendangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void unendangered_instantiatesCorrectly_true() {
    Unendangered testUnendangered = new Unendangered("Rhino", "Healthy", "Newborn");
    assertEquals(true, testUnendangered instanceof Unendangered);
  }

}
