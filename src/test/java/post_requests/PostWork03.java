package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWork03 extends JsonPlaceHolderBaseUrl {
    /*
      Given
         https://jsonplaceholder.typicode.com/todos
         {
         "userId": 25,
         "title": "Erzurum da Kayak yapmadan dönme",
         "completed": false
         }
     When
         I send POST Request to the Url
     Then
         Status code is 201
     And
         response body is like {
 "userId": 25,
 "title": "Erzurum da Kayak yapmadan dönme",
 "completed": false,
 "id": 201
}
  */
    @Test
    public void postWork03() {
        //Set the URL
        spec.pathParam("first", "todos");

        //Set the expected data

        /*
        parametreli constructor ile bir obje oluşturumu yaptım objeyle getter ve setter lara ulaşabilirim
         Bu objenin java kullarak oluşturduğum map lar gibi düşünülebilir çok farkı yok
        Oluşturduğum obje ye yani expectedData ya data tipi olarak JsonPlaceHolderPojo belirtimi yaptım
        Bu değişiklikler static olmadığı için objeyi bağlar class'ı değiştirmez

         */


        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(25, "Erzurum da Kayak yapmadan dönme", false);
        System.out.println("expectedData = " + expectedData);

        //Send the request get the response

        Response response = given().spec(spec).body(expectedData).post("{first}");
        //Response ya oluşturduğum expectedData ya body() içine koyarak post işlemi yapıyorum
        response.prettyPrint();

        //Do Assertion
        /*


        Oluşturduğum datayı response dan çekiyorum ancak response.as(JsonPlaceHolderPojo.class);
         Belirtimi yaparak gelen datayı JsonPlaceHolderPojo data tipine çevirme işlemi yapıyorum
        Daha önce ki as(HashMap) te yaptğımız gibi aynı işlemdir

         */
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        /*
         Biz oluşturduğumuz datayı response den çektiğimizde gelen data da id değişkeni fazladır
         Yani oluşturduğumuz pojo objesinde id değişkeni yok bu nedenle çevirme işlemini ilk önce yapamadı
        Bu sorunu çözmemiz için JsonPlaceHolderPojo class'ına gidip üstüne  @JsonIgnoreProperties(ignoreUnknown = true)
        Bunu belirtiyoruz ve pojo class'ında olmayan  id değişkenini görmezden gel demiş oluyoruz
        UnrecognizedPropertyException: Unrecognized field "id" (class pojos.JsonPlaceHolderPojo),
         not marked as
         ignorable (3 known properties: "title", "completed", "userId"])

         */
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getTitle(), actualData.getTitle());
        assertEquals(expectedData.getCompleted(), actualData.getCompleted());


    }
}
