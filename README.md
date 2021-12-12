# Why Serenity Framework with Cucumber


- Serenity BDD provides strong support for different types of automated acceptance testing, including:


- Rich built-in support for web testing with Selenium.


- REST API testing with RestAssured.


- Highly readable, maintainable and scalable automated testing with the Screenplay pattern.

# Feature File 


``` 
Feature: Validate user e-mail address
  Scenario: Validate all returned e-mails
    Given The user sets json placeholder API
    When The user gets all user posts by user ID
    And The user gets all comments by post ID
    Then The user validates all e-mails
```

Serenity With Cucumber Runner Class
```
package starter.steps;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features = "classpath:Features"
)
public class starter.CucumberTestSuite {}

```

All Step Definitions 

```

package starter.steps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
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
    @Then("The user validates all e-mails")
    public void the_user_validates_all_e_mails() {
        RestAssured.basePath = "/comments/";
        List post_id_list = the_user_gets_all_user_posts_by_user_id();
        System.out.println("List of all User Post IDs....."+post_id_list);
        Response response = SerenityRest.given().when().queryParam("id", post_id_list).get();
        List email = response.then().extract().path("email");
        response.then().assertThat().body("email", is(email));
         for (int j = 0; j < email.size(); j++)
        {
            System.out.println(email.get(j)+ ", Is Valid e-mail ");
            int statusCode = response.getStatusCode();
            System.out.println("The status code recieved is : " + statusCode);

        }
    }

}



```

# How to Run the Test cases 

This inscructions on how to run the Serenity with BDD test cases

### Prerequisites
 
- Cucumber

- Core Serenity dependency

- JUnit Serenity dependency

- The Maven Failsafe plugin

- The Serenity Maven Plugin

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>serenitybdd</groupId>
    <artifactId>serenity-rest</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>Json Placeholder BE Automation using Cucumber with Serenity </name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <serenity.version>2.4.51</serenity.version>
        <serenity.maven.version>2.4.51</serenity.maven.version>
        <serenity.cucumber.version>2.4.51</serenity.cucumber.version>
        <logback.version>1.2.3</logback.version>
        <encoding>UTF-8</encoding>
        <tags></tags>
        <parallel.tests>4</parallel.tests>
    </properties>

    <dependencies>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>

        <!-- Serenity Dependencies -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <version>${serenity.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit</artifactId>
            <version>${serenity.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
            <version>${serenity.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay</artifactId>
            <version>${serenity.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-screenplay-rest</artifactId>
            <version>${serenity.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber6</artifactId>
            <version>${serenity.cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- libraries used directly from serenity-core-->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.3.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>json-path</artifactId>
            <version>4.3.3</version>
        </dependency>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>xml-path</artifactId>
            <version>4.3.3</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy</artifactId>
            <version>3.0.6</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.groovy</groupId>
            <artifactId>groovy-xml</artifactId>
            <version>3.0.6</version>
        </dependency>

        <!-- General test libraries -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.assertj</groupId>
            <artifactId>assertj-core</artifactId>
            <version>3.6.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest</artifactId>
            <version>2.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <skip>true</skip>
                </configuration>
            </plugin>
            <plugin>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/Test*.java</include>
                        <include>**/*TestSuite.java</include>
                        <include>**/When*.java</include>
                    </includes>

                    <parallel>classes</parallel>
                    <threadCount>${parallel.tests}</threadCount>
                    <forkCount>${parallel.tests}</forkCount>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.0</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.maven.version}</version>
                <configuration>
                  <tags>${tags}</tags>
                </configuration>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>

```


### Executing the tests

$ git clone https://github.com/amirasolimanahmed/json_placeholder_restassured.git



$ mvn clean verify

## Generating the reports

$ mvn serenity:aggregate


