package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerOkuAppBaseUrl {


    /*
        Given
            https://restful-booker.herokuapp.com/booking/32
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
                {
                    "firstname": "Jane",
                    "lastname": "Doe",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Extra pillows please"
                }
     */


    @Test

    public void get06(){

        spec.pathParams("first","booking","second",21);


        Response response = given(spec).get("/{first}/{second}");
        response.prettyPrint();


        //Do assertion

        //1.Way

        response.then().statusCode(200).contentType(ContentType.JSON)
                .body("firstname",equalTo("Josh"),
                        "depositpaid",equalTo(true));


        //2.Way: We will use JsonPath Class

        JsonPath jsonPath = response.jsonPath(); //We can extract the data from body to outside
                                                                                //with JsonPath

        //Assertion
        assertEquals("Josh",jsonPath.getString("firstname"));
        assertEquals("Allen",jsonPath.getString("lastname"));
        assertEquals(111,jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));



        //Soft assertion
        //to do soft assetion follow these 3 steps

        //1.Step: Create soft assertion object

        SoftAssert softAssert = new SoftAssert();


        //2.Step: Do assertion

        softAssert.assertEquals(jsonPath.getString("firstname"),"Josh","firstname did NOT match");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Allen","lastname did NOT match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"lastname did NOT match");



        //3.Use assertAll()

        softAssert.assertAll();

    }



}
