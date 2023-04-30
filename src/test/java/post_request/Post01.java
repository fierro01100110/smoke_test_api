package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2)  {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
         }
   When
       I send POST Request to the Url

   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/


    @Test// Not recommended. Post02 is recommended
    public void post01() {

        //Set the URL
        spec.pathParam("first", "todos");


        //Set the expected data
        Map<String, Object> expectedData1 = new HashMap<>();
        expectedData1.put("userId", 5);
        expectedData1.put("title", "Dirty room");
        expectedData1.put("completed", true);

        System.out.println("expectedData1 = " + expectedData1);


        //Send the request and get the response
        Response response = given(spec).contentType(ContentType.JSON).body(expectedData1).post("{first}");

        response.prettyPrint();


        //Do assertion
        Map<String,Object> actualData1 = response.as(HashMap.class);
        System.out.println("actualData1 = " + actualData1);

        assertEquals(201,response.statusCode());

        assertEquals(expectedData1.get("userId"),actualData1.get("userId"));
        assertEquals(expectedData1.get("title"),actualData1.get("title"));
        assertEquals(expectedData1.get("completed"),actualData1.get("completed"));


        ////////////////////////////////////////////////////////////////////////////////////















    }


}
