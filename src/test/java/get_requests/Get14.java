package get_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get14 extends DummyRestApiBaseUrl {
    /*
Given
    https://dummy.restapiexample.com/api/v1/employees
When
    User sends Get Request to the Url
Then
    Status code is 200
And
    There are 24 employees
And
    "Tiger Nixon" and "Garrett Winters" are among the employees
And
    The greatest age is 66
And
    The name of the lowest age is "[Tatyana Fitzpatrick]"
And
    Total salary of all employees is 6,644,770
 */

    @Test
    public void get(){

        spec.pathParam("first","employees");

        Response response = given(spec).contentType(ContentType.JSON).get("{first}");
        response.prettyPrint();

        response.then().statusCode(200).body("data.id",hasSize(24),"data.employee_name",hasItems("Tiger Nixon","Garrett Winters"));

        JsonPath jsonPath = response.jsonPath();

        List<Integer> ages = jsonPath.getList("data.employee_age");
        System.out.println("ages = " + ages);

        int max = 0;
        for (Integer w : ages){
            max = Math.max(max,w);
        }
        System.out.println(max);

        if (max==66){
            assertEquals(200,response.statusCode());
        }


    }


}
