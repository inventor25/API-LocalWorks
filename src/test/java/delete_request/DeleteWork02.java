package delete_request;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDeleteBodyPojo;
import utilities.ObjectMapperUtils;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class DeleteWork02 extends DummyRestApiBaseUrl {
     /*
     URL: https://dummy.restapiexample.com/api/v1/delete/25
     HTTP Request Method: DELETE Request
     Test Case: Type by using Gherkin Language
     Assert:     i) Status code is 200
                 ii) "status" is "success"
                 iii) "data" is "25"
                 iv) "message" is "Successfully! Record has been deleted"

       */

    /*
   Given
      URL: https://dummy.restapiexample.com/api/v1/delete/25
   When
      HTTP Request Method: DELETE Request
   Then
       Status code is 200
   And
       "status" is "success"
   And
       "data" is "25"
   And
       "message" is "Successfully! Record has been deleted"
     */
    @Test
    public void delete02() {
        //Set the url
        spec.pathParams("first", "delete", "second", 25);

        //Set the expedted data
        //Expected data için bir pojo oluşturduk çünkü aynı data tiplerinde kullancağımız pojo class'ımız yoktu
        DummyRestApiDeleteBodyPojo expedtedData = new DummyRestApiDeleteBodyPojo("success", "25", "Successfully! Record has been deleted");
        System.out.println("expedtedData = " + expedtedData);

        //Send the request and get the response
        //Response de delete method'unu kullanarak silme işlemini gerçekleştiriyoruz
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        DummyRestApiDeleteBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiDeleteBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expedtedData.getStatus(), actualData.getStatus());
        assertEquals(expedtedData.getData(), actualData.getData());
        assertEquals(expedtedData.getMessage(), actualData.getMessage());

    }
}