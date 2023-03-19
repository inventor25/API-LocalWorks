package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PatchWork01 extends JsonPlaceHolderBaseUrl {
     /*
            Given
                1) https://jsonplaceholder.typicode.com/todos/25
                2) {
                     "title": "voluptas quo tenetur perspiciatis explicabo natus"
                   }
            When
                 I send PATCH Request to the Url
            Then
                  Status code is 200
                  And response body is like   {
                                             "userId": 2,
                                                "id": 25,
                                                "title": "Erzurum Kete açmış seni beklir",
                                                "completed": true
                                            }
         */

    @Test
    public void patch01() {
        // Set the URL
        spec.pathParams("first", "todos", "second", 25);


        //Set the expected data
        //JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();  ilk olarak bu objeyi kullanamadık çünkü diğer parametreleri
        //girmezsek hata verir yada null girersek  map a eklenir datalar bozulur.  Fakat bu durum Class'taki hazır methodun içine gidip==>
        // if()  kullanarak null değerlerinin eklenmesi önlenebilir hazır methodu kullanmak için
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMethod(null, "Erzurum Kete açmış seni beklir", null);
        System.out.println("expectedData = " + expectedData);

        // Send the request and get the response
        Response response = given().spec(spec).body(expectedData).patch("/{first}/{second}");

        //****************
        //setContentType(ContentType.Type.JSON)==>> Bizim data tipimizdir
        //setAccept(ContentType.JSON) ==>> Karşı tarafın kabul ettiği data tipidir bunu spec'in içine ekleyebiliriz yada
        //body(expectedData).setContentType(ContentType.Type.JSON) şeklinde belirterek kullanabiliriz bu
        //setContentType ve setAccept içinde geçerlidir
        //****************


        response.prettyPrint();

        //Do Assertion
       Map<String,Object> actualData = response.as(HashMap.class);
       System.out.println("actualData = " + actualData);

       assertEquals(200,response.statusCode());
       assertEquals(expectedData.get("title"),actualData.get("title"));
       assertEquals(2,actualData.get("userId"));
       assertEquals(true,(boolean)actualData.get("completed"));


       //*******
        //actualData.get("completed") wrapper olduğu için assertTrue kullanımı önermiyor
        //Eğer (boolean)actualData.get("completed")); olarak cast yapıp assert istenseydi assertTrue kullanımı önerilecekti
        //******

    }
}
