package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeleteWork01 extends JsonPlaceHolderBaseUrl {
    /*
     Given
         https://jsonplaceholder.typicode.com/todos/198
     When
   I send DELETE Request to the Url
Then
   Status code is 200
   And Response body is { }
  */
    @Test
    public void delete01() {
        //Set the url
        spec.pathParams("first", "todos", "second", 25);

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        // System.out.println(response.asString().length());// Bu rada gelen boş response yi stringe çevirip karakter sayısını
        //istediğimizde boş olmadığını görüyoruz 2 karakter var içinde space
        //Map a çevirdiğimizde içinde karakter olmayacaktır ve boş bşr map olduğunu assert yapabiliriz

        Map<String, String> actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(200, response.statusCode());

        //1. Yol
        assertEquals(expectedData, actualData);

        //2. Yol
        //actual data nın isEmpty methoduyla boş olduğunu sorgulayaıp assert yapabiliriz
        assertTrue(actualData.isEmpty());

        //3. Yol
        assertEquals(0, actualData.size());

    }
}

