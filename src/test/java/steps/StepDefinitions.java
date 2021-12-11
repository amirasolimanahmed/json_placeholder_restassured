package steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;


public class StepDefinitions {
    @Step
    @Given("The user sets json placeholder API")
    public void the_user_sets_json_placeholder_api() {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

    }
    @Step
    @When("The user gets all user posts by user ID")
    public List the_user_gets_all_user_posts_by_user_id() {
        RestAssured.basePath="/posts/";
        Response response= SerenityRest.given().when().queryParam("userId","9").get();
        List post_id = response.then().extract().path("id");
        int statusCode = response.getStatusCode();
        System.out.println("The status code recieved is.... : " + statusCode);
        return  post_id;

    }
    @Step

    @When("The user gets all comments by post ID")
    public List the_user_gets_all_comments_by_post_id() {
        RestAssured.basePath = "/comments/";
        List post_id_list = the_user_gets_all_user_posts_by_user_id();
        System.out.println("List of all User Post IDs....."+post_id_list);
        Response response = SerenityRest.given().when().queryParam("id", post_id_list).get();
        List email = response.then().extract().path("email");
        return email;
    }
    @Step
    @Then("The user validates all e-mails")
    public void the_user_validates_all_e_mails() {
        List email = the_user_gets_all_comments_by_post_id();

         for (int j = 0; j < email.size(); j++)
        {
            Assert.assertEquals ("email", is(email));
            System.out.println(email.get(j)+ ", Is Valid e-mail ");

        }
    }

}
