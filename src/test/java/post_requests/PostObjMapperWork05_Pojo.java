package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostObjMapperWork05_Pojo extends JsonPlaceHolderBaseUrl {
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
    public void postObjMapWork_Pojo() {
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(55, "Erzurum dan Selam", false);

        //Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion
        /*
        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(),JsonPlaceHolderPojo.class);
        JsonPlaceHolderPojo class'ından oluşturduğumuz Objeyi actual data olarak belirtip ObjectMapper kullanarak
        JsonPlaceHolderPojo data tipine çeviriyoruz ancak readValue bizden exception'u handle etmemizi istiyor
        Bu nedenle daha dinamik hale getirip her seferinde bu işlemi yapmamak için ve sürekli Object data tipini
        Kullanmamak için biz Object mapper utils oluşturuyoruz bu işlemleri içinde dinamik olarak gerçekleştiriyoruz


       ******* -----import com.fasterxml.jackson.core.JsonProcessingException;------
       *******      import com.fasterxml.jackson.databind.ObjectMapper;
            bu importlar çalışmazsa code house dan import edilmeli

         */
        JsonPlaceHolderPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        // ObjectMapperUtils 'e gidip olusturduğumuz convertJsonToJava method'unu kullanarak response.asString
        //kullanıp stringe çevirip JsonPlaceHolderPojo data tipi olarak actaulData mızı java da kullanabileceğimiz
        //şekilde oluşturduk.
        System.out.println("actualData = " + actualData);
        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());

    }
}
