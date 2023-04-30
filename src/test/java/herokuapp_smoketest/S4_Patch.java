package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppInnerPojo;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S4_Patch extends HerOkuAppBaseUrl {

    /*

    Given
            https://restful-booker.herokuapp.com/booking/{id}

    And
            {
    "firstname" : "Jim",
    "lastname" : "Brown"
}

    When
            Send patch request

    Then
            Status code should be 200

    And
            {
    "firstname": "Jim",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2023-01-01",
        "checkout": "2024-01-01"
    },
    "additionalneeds": "Breakfast"
}     */

    @Test
    public void patch(){
        //set the url

        spec.pathParams("first","booking","second",bookingId);

        Map<String,String> expectedData = new HashMap<>();
        expectedData.put("firstname","John");
        expectedData.put("lastname","Doe");

        //Send the request and get the response
        Response response = given(spec).body(expectedData).patch("{first}/{second}");
        response.prettyPrint();

//        Map<String,String> actualData = response.as(HashMap.class);
//        assertEquals(expectedData.get("John"),actualData.get("John"));
//        assertEquals(expectedData.get("Doe"),actualData.get("Doe"));



        //2.Way: actualData

        Map<String,Object> actualData = ObjectMapperUtils.convertJsonToJavaObject(response.asString(), HashMap.class);
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));

    }

}
