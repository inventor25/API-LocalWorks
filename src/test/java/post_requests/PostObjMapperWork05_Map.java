package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostObjMapperWork05_Map extends JsonPlaceHolderBaseUrl {
    /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2) {
            "userId": 25,
            "title": "Erzurum dan Selam",
            "completed": false
          }


       I send POST Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 25,
                                "title": "Erzurum dan Selam",
                               "completed": false,
                               "id": 201
                               }
*/
    @Test
    public void postObjMapper() throws JsonProcessingException {


        spec.pathParam("first", "todos");

        Map<String, Object> expectedData = new JsonPlaceHolderTestData().expectedDataMethod(25, "Erzurum dan Selam", false);
        System.out.println("expectedData = " + expectedData);
        Response response = given().spec(spec).body(expectedData).post("{first}");
        response.prettyPrint();


        // Do Assertion

        //ObjectMapper kullanarak asString() diyerek Stringe çevrilmiş response u HashMap çevirmeyi yaptık
        //ObjectMapper().readValue içine koyulan stringi HashMap e çevirebilir
        //Burda actaulData mız map a çevrilmiş oldu

        //****** ObjectMapper çeviricidir istersek HashMap.class yerine bir pojo class koyup ona da çevirebiliriz
        // Dilersek ObjectMapper kullanarak ==>  JsonPlaceHolderPojo actualData1 = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
        //çevirmeyi de yapabiliriz

        /*
                 @JsonIgnoreProperties(ignoreUnknown = true)
      ******* -----import com.fasterxml.jackson.core.JsonProcessingException;------
       *******      import com.fasterxml.jackson.databind.ObjectMapper;
            bu importlar çalışmazsa code house dan import edilmeli
         */

        Map<String, Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);

        System.out.println("actualData = " + actualData);

        assertEquals("assert", 201, response.getStatusCode());

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
