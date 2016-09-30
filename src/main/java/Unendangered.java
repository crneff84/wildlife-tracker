import java.util.List;
import org.sql2o.*;

public class Unendangered extends Animal {
  public static final String DATABASE_TYPE = "unendangered";

  public Unendangered(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }

}
