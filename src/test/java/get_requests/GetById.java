package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetById extends HerOkuAppBaseUrl {

    /*

    Given
            https://restful-booker.herokuapp.com/booking/1

    When
            User send Get request

    Then
            Status code should be 200

    And
            Body should be like:

            {
    "firstname": "Eric",
    "lastname": "Ericsson",
    "totalprice": 749,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2016-06-11",
        "checkout": "2019-01-14"
    }
}
     */


    @Test

    public void getById(){

        spec.pathParams("first","booking","second",11);


        Response response = given(spec).get("/{first}/{second}");

        response.prettyPrint();

        response.then().statusCode(200)
                .body("firstname",equalTo("Jane") ,
                        "lastname",equalTo("Doe"),
                "totalprice",equalTo(111),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "additionalneeds",equalTo("Extra pillows please"));




    }




}
