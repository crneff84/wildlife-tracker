import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.Arrays;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/endangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/endangereds.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      try {
      Endangered newEndangered = new Endangered(name, health, age);
      newEndangered.save();
      } catch (IllegalArgumentException exception) {}
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/endangereds/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      Endangered endangered = Endangered.find(Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
      model.put("sighting", sighting);
      model.put("endangered", endangered);
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/endangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/endangeredsightings", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      try {
        Endangered endangered = Endangered.find(Integer.parseInt(request.queryParams("animalId")));
        String location = request.queryParams("location");
        String rangerName = request.queryParams("ranger-name");
        try {
        Sighting newSighting = new Sighting(endangered.getId(), location, rangerName);
        newSighting.save();
        } catch (IllegalArgumentException exception) {}
      } catch (NumberFormatException exception) {}
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/unendangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      model.put("unendangereds", Endangered.all());
      model.put("template", "templates/unendangereds.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/unendangereds", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      String name = request.queryParams("name");
      String health = request.queryParams("health");
      String age = request.queryParams("age");
      try {
      Unendangered newUnendangered = new Unendangered(name, health, age);
      newUnendangered.save();
      } catch (IllegalArgumentException exception) {}
      model.put("unendangereds", Unendangered.all());
      model.put("endangereds", Endangered.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    post("/unendangeredsightings", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      try {
        Unendangered unendangered = Unendangered.find(Integer.parseInt(request.queryParams("animalId")));
        String location = request.queryParams("location");
        String rangerName = request.queryParams("ranger-name");
        try {
        Sighting newSighting = new Sighting(unendangered.getId(), location, rangerName);
        newSighting.save();
        } catch (IllegalArgumentException exception) {}
      } catch (NumberFormatException exception) {}
      model.put("endangereds", Endangered.all());
      model.put("unendangereds", Unendangered.all());
      model.put("sightings", Sighting.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/unendangereds/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<>();
      Unendangered unendangered = Unendangered.find(Integer.parseInt(request.params(":id")));
      Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
      model.put("sighting", sighting);
      model.put("unendangered", unendangered);
      model.put("unendangereds", Unendangered.all());
      model.put("template", "templates/unendangered.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
