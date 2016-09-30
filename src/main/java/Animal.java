import java.util.Arrays;
import org.sql2o.*;
import java.util.List;

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

  public int getId() {
    return id;
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

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      if(this.getName().equals("")) {
        throw new IllegalArgumentException("You must enter a name!");
      }
      String sql = "INSERT INTO animals (name, health, age, type) VALUES (:name, :health, :age, :type)";
      this.id = (int) con.createQuery(sql, true)
                         .addParameter("name", this.name)
                         .addParameter("health", this.health)
                         .addParameter("age", this.age)
                         .addParameter("type", this.type)
                         .executeUpdate()
                         .getKey();
    }
  }
}
