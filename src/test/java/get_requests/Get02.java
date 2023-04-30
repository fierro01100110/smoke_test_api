package get_requests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {


    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

@Test
    public void get02(){

    String url = "https://restful-booker.herokuapp.com/booking/0";

    Response response = given().get(url);
    response.prettyPrint();

    response.then().
            statusCode(404).
            statusLine("HTTP/1.1 404 Not Found");


    //How to assert if response body contains any data
    //Response body contains "Not Found"
    assertEquals("Not Found",response.asString());//checks if the expected data and actual data matches

    //Response body does not contain "TechProED"
    assertFalse(response.asString().contains("TechProEd"));//passes if the value between parenthesis is "false"

    //Server is "Cowboy"
    assertTrue(response.header("Server").contains("Cowboy"));//passes if the value between parenthesis is "true"
    assertEquals("Cowboy",response.header("Server"));//2.nd way ===> Recommended


}

}
