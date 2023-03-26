package practice;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class P08 extends PetStoreBaseUrl {

    //1) https://petstore.swagger.io/ dokumanını kullanarak statüsü "available" olan "pet" sayısını bulup 100'den fazla olduğunu assert eden bir otomasyon testi yazınız.

    /*
       Given
           https://petstore.swagger.io/v2/pet/findByStatus?status=available
       When
           User sens Get request
       Then
           Assert that number of pets whose status is "available" is more than 100
        */
    @Test
    public void petStoreAvailablePets(){
        spec.pathParams("first","pet","second","findByStatus").
                queryParam("status","available");

        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        int availablePetSayisi = response.jsonPath().getList("id").size();
        assertTrue(availablePetSayisi>100);

    }

}
