package put_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutWork01 extends JsonPlaceHolderBaseUrl {
    /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 25,
                 "title": "Erzuruma git ve cağ kebap ye",
                 "completed": false
                }
        When
         I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                "userId": 2,
                                "id": 25,
                                "title": "voluptas quo tenetur perspiciatis explicabo natus",
                                "completed": true
}
     */

    @Test
    public void putWork01() {
        // Set the URL
        spec.pathParams("first","todos","second",25);
        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();// Boş bir map oluşturduk  ve datalarımızı ekledik
        expectedData.put("userId",2);
        expectedData.put("title","Erzuruma git ve cağ kebap ye");
        expectedData.put("completed",true);
        expectedData.put("id",25);
        System.out.println("expectedData = " + expectedData);

        //Send the request get the response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");//
        //.contentType(ContentType.JSON) kullanmadığımızda tip belirtmediğimiz için datalarımız istediğimiz formatta gelmiyor oluşmuyor bu nedenle bunu kullanmamız gerekli

        response.prettyPrint();

        // Do Assertion
        Map<String,Object>actualData= response.as(HashMap.class);//DE-Serialization ==> Json to Java Response den actual gelen datayı Java ya çevrip as map diyerek map a atamamız sağlandı
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));



    }
    @Test
    public void putWork01Dynamic() {
        // Set the URL
        spec.pathParams("first","todos","second",25);
        //Set the expected data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        //JsonPlaceHolderTestData class'ından Obje oluşturduk dinamik methodu kullanabilmemiz için

        Map<String,Object> expectedData = obj.expectedDataMethod(2,"Erzuruma git ve cağ kebap ye",true);// Boş bir map oluşturduk  ve datalarımızı ekledik

        System.out.println("expectedData = " + expectedData);

        //Send the request get the response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).put("/{first}/{second}");//
        //.contentType(ContentType.JSON) kullanmadığımızda tip belirtmediğimiz için datalarımız istediğimiz formatta gelmiyor oluşmuyor bu nedenle bunu kullanmamız gerekli

        response.prettyPrint();

        // Do Assertion
        Map<String,Object>actualData= response.as(HashMap.class);//DE-Serialization ==> Json to Java Response den actual gelen datayı Java ya çevrip as map diyerek map a atamamız sağlandı
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("userId"),actualData.get("userId"));



    }
}
