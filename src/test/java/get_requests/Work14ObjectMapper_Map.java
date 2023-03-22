package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work14ObjectMapper_Map extends JsonPlaceHolderBaseUrl {
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
      public void work14(){
          //Set the URL
          spec.pathParams("first","todos","second",25);

          //Set the expected data
//        String json = "{\n" +
//                "   \"userId\": 2,\n" +
//                "    \"id\": 25,\n" +
//                "    \"title\": \"voluptas quo tenetur perspiciatis explicabo natus\",\n" +
//                "    \"completed\": true\n" +
//                "  }";



          String json = JsonPlaceHolderTestData.expectedDataInString(2,"voluptas quo tenetur perspiciatis explicabo natus",true);
          //JsonPlaceHolderTestData class'ında olusturduğumuz expectedDataInString method'u ile
          // json'ı(expectedData mızı) string'e çevirterek alıp  convertJsonToJava methodu ile kullanılabilir

          Map<String,Object> expectedData = ObjectMapperUtils.convertJsonToJava(json, HashMap.class);
          System.out.println("expectedData = " + expectedData);

          //Send the rquest and get the response

          // Response response = given(spec).when().get("/{first}/{second}"); Spec bu şekilde de kullanılabilir
          Response response = given().spec(spec).when().get("/{first}/{second}");
          response.prettyPrint();

          //Do Assertion
          Map<String,Object> actualData= ObjectMapperUtils.convertJsonToJava(response.asString(),HashMap.class);
          System.out.println("actualData = " + actualData);

          assertEquals(expectedData.get("userId"),actualData.get("userId"));
      }
}


