package Practice;

import base_urls.PetStore;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class GetPractice02 extends PetStore {
    /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        User sends Get request
    Then
        Assert that number of pets whose status is "available" is more than 100
     */


    @Test

    public void get(){

        spec.pathParams("first","pet","second","findByStatus").queryParams("status","available");

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();


        JsonPath jsonPath = response.jsonPath();

        List<Integer> numOfPets =  jsonPath.getList("available");
        System.out.println("numOfPets = " + numOfPets);

        int numOfPetSize = numOfPets.size();

        assertTrue(numOfPetSize>100);






    }


}
