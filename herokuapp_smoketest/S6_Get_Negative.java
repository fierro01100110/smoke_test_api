package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S6_Get_Negative extends HerOkuAppBaseUrl {

    /*

    Given
            https://restful-booker.herokuapp.com/booking/{id}

    When
            Send the request

    Then
            Status code should be 404

    And
            Body should be "Not Found"

     */






    @Test
    public void getNegative(){

        spec.pathParams("first","booking","second",bookingId);

        String expectedData = "Not Found";

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        assertEquals(404,response.getStatusCode());
        assertEquals(expectedData,response.asString());



    }
}
