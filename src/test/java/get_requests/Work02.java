package get_requests;

import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Work02 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/100
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "QAhmet"
        And
            Server is "Cowboy"
     */
    @Test
    public void work02() {
        //  i)   Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/10012"; //Dataya sahip olmayan bir url çağırdık

        //  ii)  Set the expected data

        //  iii  Send the request and get the response
        Response response =given().when().get(url); //Api den dönen datalar response edilen datalar response konteyner ine koyuldu
        response.prettyPrint();// konteyner e koyulan datalar method kullanılarak yazdırıldı (Not Found)

        //  iv)  Do assertion
        response.then().
                statusCode(404).statusLine("HTTP/1.1 404 Not Found");// böyle bir booking olmadığını 404 kod ile test ettik
        assertTrue(response.asString().contains("Not Found"));//böyle bir url olmadığından Not Found yazısı doğrulandı
        assertFalse(response.asString().contains("QAhmet"));//Gelen response datasının QAhmet içermediği doğrulandı



        assertEquals("Cowboy",response.header("Server"));// Server is "Cowboy"
        //Cowboy olarak beklenen header bölümünde ki Server isminin Cowboy olduğu doğrulandı ve response.header() kullanılarak Server ismi çağrıldı

    }
}
