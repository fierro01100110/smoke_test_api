package gmibank_api;

import base_urls.GmiBankBaseUrl;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import pojos.CountryPojo;
import pojos.States;
import utils.ObjectMapperUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static utils.GmiBankToken.generateToken;

public class PostCountry extends GmiBankBaseUrl {
    //Type an automation test that creates a "country" which includes at least 3 "states" using the document //https://app.swaggerhub.com/apis/yasinaniltechpro/GmiBank/0.0.1
    /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
          "name": "My Country",
          "states": [
            {
              "id": 0,
              "name": "My State"
            },
            {
              "id": 1,
              "name": "Your State"
            },
            {
              "id": 2,
              "name": "Her State"
            }
          ]
        }

   When
            Send post request
   Then
            Status code should be 201
   And
            Response body should be like this:
             {
    "id": 181971,
    "name": "My Country",
    "states": [
                {
                    "id": 0,
                    "name": "My State",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Your State",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Her State",
                    "tpcountry": null
                }
            ]
           }
     */


    @Test
    public void post() throws IOException {

        spec.pathParams("first","api","second","tp-countries");


        //Set the expected data

        States states1 = new States(0,"My state");
        States states2 = new States(1, "Your state");
        States states3 = new States(2,"Her state");

        List<States> statesList = new ArrayList<>();
        statesList.add(states1);
        statesList.add(states2);
        statesList.add(states3);

        CountryPojo expectedData = new CountryPojo("My country",statesList);

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .headers("Authorization","Bearer "+generateToken())
                .body(expectedData)
                .post("{first}/{second}");
        response.prettyPrint();


        //1.Way assertion
        response.then().statusCode(201)
                .body("name",equalTo(expectedData.getName())
                ,"states.id[0]",equalTo(expectedData.getStates().get(0).getId())
                ,"states.name[0]",equalTo(expectedData.getStates().get(0).getName())
                        ,"states.id[1]",equalTo(expectedData.getStates().get(1).getId())
                        ,"states.name[1]",equalTo(expectedData.getStates().get(1).getName())
                        ,"states.id[2]",equalTo(expectedData.getStates().get(2).getId())
                        ,"states.name[2]",equalTo(expectedData.getStates().get(2).getName()));



        //2.Way assertion
        JsonPath jsonPath = response.jsonPath();
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getName(),jsonPath.getString("name"));

        assertEquals(expectedData.getStates().get(0).getId(),jsonPath.getList("states.id").get(0));
        assertEquals(expectedData.getStates().get(0).getName(),jsonPath.getList("states.name").get(0));
        assertEquals(expectedData.getStates().get(1).getName(),jsonPath.getList("states.name").get(1));
        assertEquals(expectedData.getStates().get(1).getId(),jsonPath.getList("states.id").get(1));
        assertEquals(expectedData.getStates().get(2).getId(),jsonPath.getList("states.id").get(2));
        assertEquals(expectedData.getStates().get(2).getName(),jsonPath.getList("states.name").get(2));




        //3Way assertion

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        assertEquals(expectedData.getName(),actualDataMap.get("name"));
        assertEquals(expectedData.getStates().get(0).getId(),((Map)((List<Object>)actualDataMap.get("states")).get(0)).get("id"));
        assertEquals(expectedData.getStates().get(0).getName(),((Map)((List<Object>)actualDataMap.get("states")).get(0)).get("name"));
        assertEquals(expectedData.getStates().get(1).getId(),((Map)((List<Object>)actualDataMap.get("states")).get(1)).get("id"));
        assertEquals(expectedData.getStates().get(1).getName(),((Map)((List<Object>)actualDataMap.get("states")).get(1)).get("name"));
        assertEquals(expectedData.getStates().get(2).getId(),((Map)((List<Object>)actualDataMap.get("states")).get(2)).get("id"));
        assertEquals(expectedData.getStates().get(2).getName(),((Map)((List<Object>)actualDataMap.get("states")).get(2)).get("name"));


        //4Way assertion
        CountryPojo actualDataPojo = response.as(CountryPojo.class);
        assertEquals(expectedData.getName(),actualDataPojo.getName());
        assertEquals(expectedData.getStates().get(0).getId(),actualDataPojo.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(),actualDataPojo.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(),actualDataPojo.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(),actualDataPojo.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(),actualDataPojo.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(),actualDataPojo.getStates().get(2).getName());




        //5Way assertion

        CountryPojo actualDataObjectMapper = ObjectMapperUtils.convertJsonToJavaObject(response.asString(),CountryPojo.class);
        assertEquals(expectedData.getName(),actualDataPojo.getName());
        assertEquals(expectedData.getStates().get(0).getId(),actualDataObjectMapper.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(),actualDataObjectMapper.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(),actualDataObjectMapper.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(),actualDataObjectMapper.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(),actualDataObjectMapper.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(),actualDataObjectMapper.getStates().get(2).getName());





        //6Way assertion

       CountryPojo actualDataGson = new Gson().fromJson(response.asString(),CountryPojo.class);
        assertEquals(expectedData.getName(),actualDataPojo.getName());
        assertEquals(expectedData.getStates().get(0).getId(),actualDataGson.getStates().get(0).getId());
        assertEquals(expectedData.getStates().get(0).getName(),actualDataGson.getStates().get(0).getName());
        assertEquals(expectedData.getStates().get(1).getId(),actualDataGson.getStates().get(1).getId());
        assertEquals(expectedData.getStates().get(1).getName(),actualDataGson.getStates().get(1).getName());
        assertEquals(expectedData.getStates().get(2).getId(),actualDataGson.getStates().get(2).getId());
        assertEquals(expectedData.getStates().get(2).getName(),actualDataGson.getStates().get(2).getName());








    }


}
