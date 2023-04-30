package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class Post07ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) { "userId": 55,
          "title": "Tidy your room",
          "completed": false
          }


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


    @Test
    public void post() throws IOException {


        //Set the url
        spec.pathParam("first","todos");

        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        Response response = given(spec).body(expectedData).post("{first}");


        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(expectedData.getUserId(),actualData.getUserId());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());


    }



}
