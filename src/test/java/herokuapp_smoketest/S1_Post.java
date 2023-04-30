package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingResponsePojo;
import pojos.HerOkuAppInnerPojo;
import pojos.HerOkuAppPojo;
import pojos.RestfulPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class S1_Post extends HerOkuAppBaseUrl {


    /*

    Given
            https://restful-booker.herokuapp.com/booking

    And
            {
        "firstname": "Sally",
        "lastname": "Brown",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
        },
        "additionalneeds": "Breakfast"
            }

   When
           Send post request

   Then
           Status code should be 200

   And
           Response body should be like:

           {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
     */

    public static int bookingId;//We put the created bookingId into container and use it in other classes


    @Test
    public void test(){

        spec.pathParam("first","booking");

        HerOkuAppInnerPojo bookingDatesPojo = new HerOkuAppInnerPojo("2013-02-23","2014-10-23");

        HerOkuAppPojo expectedData = new HerOkuAppPojo("Sally","Brown",111,true,bookingDatesPojo,"Breakfast");

        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();


        //Do assertion

       BookingResponsePojo actualData = ObjectMapperUtils.convertJsonToJavaObject(response.asString(), BookingResponsePojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


        bookingId = actualData.getBookingid();

    }














}
