package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWork01 extends JsonPlaceHolderBaseUrl {
     /*
         Given
           1)  https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 25,
                 "title": "Erzuruma Hoş geldiniz",
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


      /*
    De-Serialization : Json datanın Java objesine çevrilmesi.
    Serialization : Java objesinin, Json dataya çevrilmesi.
    2 türlü De-Serialization yapacağız:
    i)Gson : Google tarafından üretilmiştir.
    ii) Object Mapper : En popüleri

     */

    @Test
    public void postWork01() {
        //Set the URL
        spec.pathParam("first", "todos");
        //Set the expected data ==> Payload

        // {
        //     "userId": 25,
        //         "title": "Erzuruma Hoş geldiniz",
        //         "completed": false
        // }


        // Bir map oluşturuyoruz çünkü Json dataları java dilini kullanarak oluşturmamız lazım
        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("userId", 25);
        // Map imize key value olarak json da olan datalarımızı put yaparak ekleme yapıyoruz Response daki dataları yani actualDatamızla kıyaz yapanilmemiz için expected data oluşturdum

        expectedData.put("title", "Erzuruma Hoş geldiniz");
        expectedData.put("completed", false);
        System.out.println("expectedData = " + expectedData);
        //Send the request and get the response

        //------*******body() methodu Response da kullandığımız method'dan farklıdır  burada ki body() oluştuduğumuz expectedDatamızı(payload) içeren datamızı body içine koymak için kullandık
        Response response = given().spec(spec).contentType(ContentType.JSON).when().body(expectedData).post("/{first}");


        //*****.body(expectedData) Java olan datayı Json datasına çevirebilip ve Api ye gönderebilmemiz için bazı dependency ler eklememiz gereklidir
        //*****burada oluşturduğumuz Java objemizi Api ya gönderdiğimizde Api java'yı okuyamıyor bu nedenle json yada
        //*****gson kütüphanesi ekleyerek çeviri işleminin sağlıklı kütüphanesini kullanmasını sağladık


        response.prettyPrint();
        //Do Assertion

        //********* JAVA dilinde assert işlemi yapabilmemiz için DE-Serialization yapıp Response dan gelen datamızı Java da ki Map oluşturumu yaparak içine koydurduk
        //********* Response muzu Json dan Java çevirdik yani map in içine koyduk bunun düzgün doğru dilde çevrilenilmesi için  pom a koyduğumuz "jackson-databind" kütüphanesi kullanıldı

        Map<String, Object> actualData = response.as(HashMap.class);//DE-Serialization ==> Json to Java
        //Response dan gelen data yı Java'ya çevirerek actualData'mızı belirledik ve expectedData ile karşılaştırmasına olanak sağladık
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());//201 post işleminin status code'dur bunu doğruladım
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));

    }
}
