package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get12 extends GoRestBaseUrl {
    /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        "Gov. Vrinda Panicker", "Sen. Devika Embranthiri" and "Rev. Jay Shukla" are among the users
    And
        The female users are less than or equals to male users
*/


    @Test
    public void test() {

        spec.pathParam("first", "users");


        Response response = given(spec).get("{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200)
                .body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data", hasSize(10), "data.status", hasItem("active")
                        , "data.name", hasItems("Sushma Somayaji", "Girika Dwivedi", "Anjali Sharma III"));


        //The female users are less than or equals to male users
        //I will compare number of female with male users

        //1.Way: I will get all genders in a list then calculate the number
        JsonPath jsonPath = response.jsonPath();
        List<String> allGenders = jsonPath.getList("data.gender");
        System.out.println("allGenders = " + allGenders);


        int numOfFemales = 0;

        for (String w : allGenders) {

            if (w.equals("female")) {
                numOfFemales++;
            }

        }
        System.out.println("number of females: " + numOfFemales );


        assertTrue(numOfFemales <= allGenders.size() - numOfFemales);




        //2.Way: I will get all females by using "GROOVY LANGUAGE" then compare numbers
        int numOfFemale = jsonPath.getList("data.findAll{it.gender=='female'}").size();
        System.out.println("number of females = "+ numOfFemale);


    }


}