package Practice;

import base_urls.ReqresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q02 extends ReqresBaseUrl {

    /*
    Given
            1) https://reqres.in/api/users
            2) {
        "name": "morpheus",
                "job": "leader"
    }
    When
    I send POST Request to the Url
            Then
    Status code is 201
    And response body should be like {
        "name": "morpheus",
                "job": "leader",
                "id": "496",
                "createdAt": "2022-10-04T15:18:56.372Z"
    }
     */



    @Test

    public void post(){


        spec.pathParams("first","api","second","users");

        Map<String,String> expectedData = new HashMap<>();
        expectedData.put("name","morpheus");
        expectedData.put("job","leader");

        System.out.println("expectedData = " + expectedData);

      Response response = given(spec).body(expectedData).post("{first}/{second}");
      response.prettyPrint();

      assertEquals(201,response.statusCode());



        Map<String,String> actuallData = response.as(HashMap.class);

        assertEquals(expectedData.get("name"),actuallData.get("name"));


    }



}
