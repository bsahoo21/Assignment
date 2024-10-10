package stepDefinitions;
import resources.pojos.Todos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.CommonUtilities;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TaskDetailsSteps {
    List<String> fancodeUserIds = new ArrayList<>();
    //List<String> usersWithMoreThan50PercentCompleted = new ArrayList<>();
    private Response usersResponse;
    private Response todosResponse;
    private RequestSpecification specification;
    @Before
    public void setUp(){
        specification=new RequestSpecBuilder().setBaseUri(CommonUtilities.getProperty("src/test/java/resources/config/application.properties","uri")).build();
    }
    @Given("User has the todo tasks")
    public void user_has_the_todo_tasks() {
        todosResponse = given()
                .spec(specification)
                .when()
                .get("/todos")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    @And("User belongs to the city FanCode")
    public void user_belongs_to_the_city_fan_code() {
        usersResponse = given()
                .baseUri("http://jsonplaceholder.typicode.com")
                .when()
                .get("/users")
                .then()
                .statusCode(200).log().all()
                .extract()
                .response();

        List<Map<String, Object>> usersList = usersResponse.jsonPath().getList("");

        for (Map<String, Object> user : usersList) {
            Object geo=((Map<String, Object>) (user.get("address"))).get("geo");
            Double lat = Double.parseDouble(((Map<String, Object>) geo).get("lat").toString());
            Double lng = Double.parseDouble(((Map<String, Object>) geo).get("lng").toString());


            if (lat > -40 && lat < 5 && lng > 5 && lng < 100) {
                fancodeUserIds.add(user.get("id").toString());
            }
        }

        Assert.assertTrue("No users found from Pune", !fancodeUserIds.isEmpty());
    }
    @Then("User Completed task percentage should be greater than {int}%")
    public void  user_completed_task_percentage_should_be_greater_than(Integer int1) throws JsonProcessingException {
        for (String userId : fancodeUserIds) {
            int totalTasks = 0;
            int completedTasks = 0;
            ObjectMapper objectMapper = new ObjectMapper();
            List<Todos> todoList=objectMapper.readValue(todosResponse.asString(), new TypeReference<List<Todos>>() {});
            for (Todos todo : todoList) {
                String todoUserId=todo.getUserId();
                boolean completed =todo.isCompleted();
                if (todoUserId.equals(userId)) {
                    totalTasks++;
                    if (completed) completedTasks++;
                }
            }

            double completionRate = (double) completedTasks / totalTasks * 100;

            Assert.assertTrue("User ID: " + userId + " has completed less than 50% tasks", completionRate > 50);
        }
    }
}
