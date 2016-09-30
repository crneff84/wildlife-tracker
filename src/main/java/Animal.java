import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public abstract class Animal {

  public String name;
  public int id;
  public String health;
  public String age;
  public String type;

  public static final String HEALTH_1 = "Healthy";
  public static final String HEALTH_2 = "Okay";
  public static final String HEALTH_3 = "Ill";
  public static final String AGE_1 = "Newborn";
  public static final String AGE_2 = "Young";
  public static final String AGE_3 = "Adult";

  public String getName() {
    return name;
  }

  public String getHealth() {
    return health;
  }

  public String getAge() {
    return age;
  }

  @Override
  public boolean equals(Object otherAnimal){
    if (!(otherAnimal instanceof Animal)) {
      return false;
    } else {
      Animal newAnimal = (Animal) otherAnimal;
      return this.getName().equals(newAnimal.getName()) &&
             this.getHealth().equals(newAnimal.getHealth()) &&
             this.getAge().equals(newAnimal.getAge());
    }
  }
}
