package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Work01 {
    /*
       Given
           https://restful-booker.herokuapp.com/booking/34
       When
           User sends a GET Request to the url
       Then
           HTTP Status Code should be 200
       And
           Content Type should be JSON
       And
           Status Line should be HTTP/1.1 200 OK
         */
    @Test
    public void work01() {
        //  i)   Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/34"; //url deki datalar stringe aktarıldı

        //  ii)  Set the expected data

        //  iii  Send the request and get the response
       Response response =given().when().get(url); //Api den dönen datalar response edilen datalar response konteyner ine koyuldu
       response.prettyPrint();// konteyner e koyulan datalar method kullanılarak yazdırıldı

        //  iv)  Do assertion
        response.then().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK"); //methodlar kullanılarak assertionlar yapıldı


    }
}
