package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Work04 extends HerokuappBaseUrl {

     /*
        Given
            https://https://restful-booker.herokuapp.com/booking
        When
          I send a GET request to the Url
       And
           Accept type is "application/json"
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"

       And
           "bookingid" should be one of the booking title
       And
           174,310 and 1177 should be among the bookingid
     */



    @Test
    public void work04() {
        //Set the URL
        // String url = "https://restful-booker.herokuapp.com/booking";

        spec.pathParam("first", "booking");
        //Set the expected data

        //Send the request and get the response
        Response response = given().when().spec(spec).get("/{first}");
        response.prettyPrint();

        //Do Assertion
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body(

                        "bookingid", hasItems(174, 310, 1177));//174,310,1177 id leri içerdiği hasItems ile doğruladık
    }
}
