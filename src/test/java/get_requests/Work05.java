package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Work05 extends HerokuappBaseUrl {
     /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a GET request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "John" and last name is "Smith"
            (Data içerisinde firstname değeri "John", lastname değeri "Smith" olan biri olmalı)
            https://restful-booker.herokuapp.com/booking?firstname=John&lastname=Smith
     */
     @Test
     public void get06() {
         //Set the URL

         spec.pathParam("first","booking").queryParams("fistname","John","lastname","Smith");

         //Set the expected data

         //Send the request and get the response
         Response response = given().when().spec(spec).get("/{first}");
         response.prettyPrint();

         //Do Assertion
         response.then().statusCode(200);//Status code is 200

         // Among the data there should be someone whose firstname is "John" and last name is "Smith"
         assertTrue(response.asString().contains("bookingid"));
     }

}
