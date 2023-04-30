package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingResponsePojo;
import pojos.HerOkuAppInnerPojo;
import pojos.HerOkuAppPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends HerOkuAppBaseUrl {

    /*
      Given
       1)  https://restful-booker.herokuapp.com/booking
       2)   {
             "firstname": "John",
             "lastname": "Doe",
             "totalprice": 999,
             "depositpaid": true,
             "bookingdates": {
                 "checkin": "2021-09-21",
                 "checkout": "2021-12-21"
              },
              "additionalneeds": "Breakfast"
          }
     When
    I send POST Request to the URL
   Then
    Status code is 200
And
    Response body is like {
                            "bookingid": 16,
                            "booking" :{
                                     "firstname": "John",
                                     "lastname": "Doe",
                                     "totalprice": 999,
                                     "depositpaid": true,
                                     "bookingdates": {
                                         "checkin": "2021-09-21",
                                         "checkout": "2021-12-21"
                                     },
                                     "additionalneeds": "Breakfast"
                                  }
                               }
  */



    @Test
    public void test(){

        spec.pathParam("first","booking");

        HerOkuAppInnerPojo bookingDates = new HerOkuAppInnerPojo("2021-09-21","2021-12-21");

        HerOkuAppPojo expectedData = new HerOkuAppPojo("John","Doe",999,true,bookingDates,"Breakfast");

        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).post("{first}");


        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);

        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());





    }













}
