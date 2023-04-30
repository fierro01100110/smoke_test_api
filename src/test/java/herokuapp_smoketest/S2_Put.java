package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingResponsePojo;
import pojos.HerOkuAppInnerPojo;
import pojos.HerOkuAppPojo;
import utils.ObjectMapperUtils;

import static herokuapp_smoketest.S1_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static utils.AuthenticationHerOkuApp.generateToken;

public class S2_Put extends HerOkuAppBaseUrl {


    /*
    Given
            https://restful-booker.herokuapp.com/booking/{id}

    And
            {
         "firstname": "Saly",
         "lastname": "Brow",
         "totalprice": 1111,
          "depositpaid": false,
         "bookingdates": {
        "checkin": "2021-02-23",
        "checkout": "2022-10-23"
     },
             "additionalneeds": "Clean room"
        }

    When
            Send Put request

    Then
            Status code should be 200

    And
            Body should be

            {
         "firstname": "Saly",
         "lastname": "Brow",
         "totalprice": 1111,
          "depositpaid": false,
         "bookingdates": {
        "checkin": "2021-02-23",
        "checkout": "2022-10-23"
     },
             "additionalneeds": "Clean room"
        }
     */



    @Test
    public void putTest(){

        //Set the url
        spec.pathParams("first","booking","second",bookingId);



        //Set the expected data
        HerOkuAppInnerPojo bookingDates = new HerOkuAppInnerPojo("2021-01-01","2022-01-01");
        HerOkuAppPojo expectedData = new HerOkuAppPojo("Saly","Brown",111,false,bookingDates,"Clean room");


        //Send the request and get the response
        Response response = given(spec).
                headers("Cookie","token="+generateToken()).
                body(expectedData).
                put("{first}/{second}");
        response.prettyPrint();



        //Do assertion

       HerOkuAppPojo actualData = ObjectMapperUtils.convertJsonToJavaObject(response.asString(), HerOkuAppPojo.class);

        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(expectedData.getBookingdates().getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());

    }















}
