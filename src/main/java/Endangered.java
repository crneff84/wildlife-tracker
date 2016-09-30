import java.util.List;
import org.sql2o.*;

public class Endangered extends Animal {
  public static final String DATABASE_TYPE = "endangered";

  public Endangered(String name, String health, String age) {
    this.name = name;
    this.health = health;
    this.age = age;
    type = DATABASE_TYPE;
  }


}
