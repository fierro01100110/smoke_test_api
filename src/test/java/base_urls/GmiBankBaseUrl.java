package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {
    protected RequestSpecification spec;





    @Before

    public void baseUrl(){


        spec=new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri("https://www.gmibank.com/").build();

    }
}
