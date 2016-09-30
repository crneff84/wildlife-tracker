import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class EndangeredTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangered_instantiatesCorrectly_true() {
    Endangered testEndangered = new Endangered("Rhino", "Healthy", "Newborn");
    assertEquals(true, testEndangered instanceof Endangered);
  }

}
