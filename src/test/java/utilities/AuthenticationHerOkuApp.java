package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthenticationHerOkuApp {


    public static String generateToken() {

        String url = "https://restful-booker.herokuapp.com/auth";
        Map<String, String> tokenBody = new HashMap<>();
        tokenBody.put("username", "admin");
        tokenBody.put("password", "password123");

        Response response = given().contentType(ContentType.JSON).body(tokenBody).when().post(url);


        return response.jsonPath().getString("token");

        //tokenBody adında bir map oluşturduk çünkü token üretmemiz için bu body gerekli
        //tokenBody yi koyup url adresini gösterdik ve dönen datayı response'ye atadık,
        //Return olarak teken değerini alacağız

         /*
        -****** TOKEN URETME**********

        1-)  Swagger dökümanın a göre değişebilir
        ilk önce Post bölümünde token bodysi  eklenir ve ardından üretilen token kullanılabilir

         */

    }

}