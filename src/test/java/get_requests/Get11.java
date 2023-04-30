package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerOkuAppInnerPojo;
import pojos.HerOkuAppPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get11 extends HerOkuAppBaseUrl {
     /*
    Given
        https://restful-booker.herokuapp.com/booking/47
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
    public void get11(){

        spec.pathParams("first","booking","second",175);

        //Set the expected data

        //Inner for bookingdates
        HerOkuAppInnerPojo bookingDates = new HerOkuAppInnerPojo("2018-01-01","2019-01-01");

        //Expected data
        HerOkuAppPojo expectedData = new HerOkuAppPojo("John","Smith",111,true, bookingDates, "Breakfast" );


        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).get("{first}/{second}");
        response.prettyPrint();


        //Deserialization
        HerOkuAppPojo actualData = response.as(HerOkuAppPojo.class);

        //Do assertion
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());




    }


}
