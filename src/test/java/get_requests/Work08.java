package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work08 extends JsonPlaceHolderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/25
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 2
            And "title" voluptas quo tenetur perspiciatis explicabo natus"
            And header "Age" is 6263
            And header "Pragma" is "no-cache"
            {
                "userId": 25,
                "id": 2,
                "title": "voluptas quo tenetur perspiciatis explicabo natus
            }
     */

    @Test
    public void work08() {
        spec.pathParams("first","todos","second",25);
        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String,Object> expectedData = obj.expectedDataMethod(2,"voluptas quo tenetur perspiciatis explicabo natus",true);
        expectedData.put("id",25);
        expectedData.put("Expires","-1");
        expectedData.put("Pragma","no-cache");

        System.out.println("expectedData = " + expectedData);
        //Send the request and get the response
      Response response =given().spec(spec).get("/{first}/{second}");
       response.prettyPrint();
       //Do Assertion
       Map<String,Object>actualData=response.as(HashMap.class);// De -Serialization //Response mızdan gelen actual datamızı map in içine koyduk
       System.out.println("actualData = " + actualData);
       assertEquals(200,response.statusCode());
       assertEquals(expectedData.get("completed"),actualData.get("completed"));
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(expectedData.get("userId"),actualData.get("userId"));
       assertEquals(expectedData.get("id"),actualData.get("id"));

       //  And header "Expires" is "1.1 Expires"
       assertEquals(expectedData.get("Expires"),response.getHeader("Expires"));
       //Oluşturduğumuz expectedData mızı map in içine koymuştuk ve put ile headerlar eklemiştik
        //Response dan gelen ve java'ya çevirdiğimiz datalarımızdan headerlardan
        // "Expires" i getHeader() methodu ile çağırıp expectedData daki eklediğimiz ile doğrulatma yaptık


       //            And header "Pragma" is "no-cache"
       assertEquals(expectedData.get("Pragma"),response.getHeader("Pragma"));
       //*******Bir önce ki doğrulatmada ki notlar bu step içinde geçerlidir

    }
}
