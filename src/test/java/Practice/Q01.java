package Practice;

import base_urls.ReqresBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Q01 extends ReqresBaseUrl {


    /*
    Given
    https://reqres.in/api/unknown/
    When
    I send GET Request to the URL
            Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
    Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
    Assert that the number of names whose ids are less than 3 is 2
            */


    @Test

    public void test01(){


        spec.pathParams("first","api","second","unknown");

        Response response = given(spec).get("{first}/{second}");



        //2.Print all pantone_value

        JsonPath jsonPath = response.jsonPath();

        List<Integer> list = jsonPath.getList("data.pantone_value");

        System.out.println("list = " + list);



        //3.Print all ids greater than 3 on the console
        //    Assert that there are 3 ids greater than 3

        List<Integer> idList = jsonPath.getList("data.findAll{it.id>3}.id");
        System.out.println("idList = " + idList);

        assertEquals(3,idList.size());
        assertEquals(200,response.statusCode());


        List<String> names = jsonPath.getList("data.findAll{it.id<3}.names");
        System.out.println("names = " + names);


//        4)Print all names whose ids are less than 3 on the console
//        Assert that the number of names whose ids are less than 3 is 2


    }



}
