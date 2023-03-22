package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work14ObjectMapper_Pojo  extends JsonPlaceHolderBaseUrl {
          /*
       Given
          https://jsonplaceholder.typicode.com/todos/198
       When
        I send GET Request to the URL
     Then
        Status code is 200
        And response body is like {
                "userId": 2,
                "id": 25,
                "title": "voluptas quo tenetur perspiciatis explicabo natus",
                "completed": true
}
    */
          @Test
          public void get14() {
              //Set the URl
              spec.pathParams("first","todos","second",25);

              //Set the expected data
              JsonPlaceHolderPojo expectedData =new JsonPlaceHolderPojo(2,"voluptas quo tenetur perspiciatis explicabo natus",true);

              System.out.println("expectedData = " + expectedData);

              //Send the request get the response

              Response response = given().spec(spec).get("/{first}/{second}");
              response.prettyPrint();

              //Do Assertion
              JsonPlaceHolderPojo actaulData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);

              assertEquals(200,response.statusCode());
              assertEquals(expectedData.getUserId(),actaulData.getUserId());
              assertEquals(expectedData.getTitle(),actaulData.getTitle());
              assertEquals(expectedData.getCompleted(),actaulData.getCompleted());


          }
}
