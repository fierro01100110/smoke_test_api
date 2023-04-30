package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends JsonPlaceHolderBaseUrl {


    @Test
    public void post01() {

        //Set the URL
        spec.pathParam("first", "todos");


        //Set the expected data
        Map<String, Object> expectedData = new JsonPlaceHolderTestData().expectedDataMap(55, "Dirt your room", true);


        System.out.println("expectedData = " + expectedData);


        //Send the request and get the response
        Response response = given(spec).contentType(ContentType.JSON).body(expectedData).post("{first}");

        response.prettyPrint();


        //Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}