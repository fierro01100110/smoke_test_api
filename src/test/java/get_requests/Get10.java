package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends HerOkuAppBaseUrl {


    /*
    Given
        https://restful-booker.herokuapp.com/booking/328
    When
        I send GET Request to the url
    Then
        Response body should be like that;
        {
            "firstname": "Jane",
            "lastname": "Doe",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
        }
 */


    @Test

    public void test10(){

        spec.pathParams("first","booking","second",3331);


        //Set the expected data
        Map<String,String> bookingDates = new HerOkuAppTestData().bookingDates("2018-01-01","2019-01-01");



        Map<String,Object> expectedData = new HerOkuAppTestData().expectedData("Jane","Doe",111,true, bookingDates, "Extra pillow please");

        System.out.println("expectedData = " + expectedData);




        //Send the request and get the responce
        Response response = given(spec).get("{first}/{second}");

        response.prettyPrint();



        //Do assertion
        Map<String,Object> actualData = response.as(HashMap.class);


        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(bookingDates.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingDates.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));


    }


}
