package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.GoRestAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work10 extends GoRestBaseUrl {

     /*

        Given
            https://gorest.co.in/public/v1/users/152944
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            /*
 {
    "meta": null,
    "data": {

              "id": 149678,
        "name": "Adikavi Chopra II",
        "email": "chopra_adikavi_ii@mcclure.io",
        "gender": "female",
        "status": "inactive"
    }
}
     */
     @Test
     public void get10() {
         //Set the URL
         spec.pathParams("first", "users", "second", 149678);
         //Set the expected data
         GoRestAppTestData obj = new GoRestAppTestData();
         Map<String,String> dataMap =obj.dataMapMethod("Adikavi Chopra II","chopra_adikavi_ii@mcclure.io","female","inactive");

         Map<String,Object> expectedData = obj.expectedDataMapMethod(null,dataMap);
         System.out.println("expectedData = " + expectedData);

         //Send the request get the response
         Response response =given().spec(spec).get("/{first}/{second}");
         response.prettyPrint();

         //Do assertion
         //ÖDEV: 500 hatası nedeniyle verildi...


         Map<String, Object> actualData = response.as(HashMap.class);
         System.out.println("actualData = " + actualData);
         assertEquals(200, response.statusCode());

         //ExpectedDataget() ve actualData().get() ikiside object döndüğü için cast işlemine gerek yok
        assertEquals(expectedData.get("meta"),actualData.get("meta"));

         //assert deki expected data için dataMap.get() kullandık çünkü string dönüyor,
         //eğer expectedData.get() kullarak data çağırsaydık bir cast işlemi daha gerekecekti çünkü object i stringe çevirmemiz gerekecekti
         //actualData().get("data") çağırdık ve bunu assertte kullanmamız için gelen datayı map'a çevirip içinde ki name key'ine ulaşmamız  lazımdır
         //bu nedenle dataMap ten gelen string ile  actualData dan gelen object i map a çevirip içinde ki key yani name'e ulaşıp value'suna ulaştım string değer ile assert yaptım


         //Diğer steplermizde yukarda ki notla aynı durumdur inner map'ta  bu işlemi anlamak önemlidir
        assertEquals(dataMap.get("name"),((Map)actualData.get("data")).get("name"));

        assertEquals(dataMap.get("email"),((Map)actualData.get("data")).get("email"));
        assertEquals(dataMap.get("gender"),((Map)actualData.get("data")).get("gender"));
        assertEquals(dataMap.get("status"),((Map)actualData.get("data")).get("status"));




     }
}
