package test_data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppTestData {



    //this method creates a map for inner join
    public Map<String,String> bookingDates(String checkin, String checkout){

        Map<String,String> bookingDates = new HashMap<>();
        bookingDates.put("checkin",checkin);
        bookingDates.put("checkout",checkout);

        return bookingDates;

    }


    //This method creates for outer join
    public Map<String,Object> expectedData(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String,String> bookingDates,String additionalneeds){

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname",firstname);
        expectedData.put("lastname",lastname);
        expectedData.put("totalprice",totalprice);
        expectedData.put("depositpaid",depositpaid);
        expectedData.put("bookingdates",bookingDates);

        if (additionalneeds!=null) {
            expectedData.put("additionalneeds", additionalneeds);
        }

     return  expectedData;


    }










}
