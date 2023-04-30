package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;

public class Get05 extends HerOkuAppBaseUrl {


    /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
             Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test
    public void Get05(){

        //Set url
        //https://restful-booker.herokuapp.com/booking/?firstname=Jane&lastname=Doe

        spec.pathParams("first","booking").
                queryParams("firstname","Jane","lastname","Doe");

        //set the expected data


        //Send the request and get the response

        Response response = given(spec).get("{first}");
        response.prettyPrint();


        //Do assertion
        response.then().statusCode(200).body("",hasSize(greaterThan(0)));

        //2.Way

        assertTrue(response.asString().contains("bookingid"));


    }




}
