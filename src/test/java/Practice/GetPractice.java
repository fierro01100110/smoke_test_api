package Practice;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.HerOkuAppInnerPojo;
import pojos.HerOkuAppPojo;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class GetPractice extends HerOkuAppBaseUrl {

      /*
        Given
                https://restful-booker.herokuapp.com/booking/43
        When
                I send GET Request to the URL
        Then
                Status code is 200
                        {
                            "firstname": "Josh",
                            "lastname": "Allen",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "super bowls"
                        }
     */

@Test
    public void get() throws IOException {

    spec.pathParams("first","booking","second",82);

    HerOkuAppInnerPojo bookingDates = new HerOkuAppInnerPojo("2018-01-01","2019-01-01");

    HerOkuAppPojo expectedData = new HerOkuAppPojo("Josh","Allen",111,true,bookingDates,"super bowls");
    System.out.println("expectedData = " + expectedData);

    Response response = given(spec).body(expectedData).get("{first}/{second}");
    response.prettyPrint();


    HerOkuAppPojo actualData = new ObjectMapper().readValue(response.asString(),HerOkuAppPojo.class);
    System.out.println("actualData = " + actualData);



    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(response.getStatusCode(),200);


    assertEquals(actualData.getFirstname(),expectedData.getFirstname());
    assertEquals(actualData.getLastname(),expectedData.getLastname());
    assertEquals(actualData.getTotalprice(),expectedData.getTotalprice());
    assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid());
    assertEquals(actualData.getBookingdates().getCheckin(),expectedData.getBookingdates().getCheckin());
    assertEquals(actualData.getBookingdates().getCheckout(),expectedData.getBookingdates().getCheckout());
    assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds());





}




}
