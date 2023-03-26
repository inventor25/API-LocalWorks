package practice.smoke;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static practice.smoke.S01Post.bookingId;

public class S02Put extends HerokuappBaseUrl {
     /*
    Given
       1) https://restful-booker.herokuapp.com/booking/{id}
       2) {
            "firstname" : "Ahmet",
            "lastname" : "Tester",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Döner"
        }
    When
        Send Put request
    Then
        Status code should be 200
    And
          {
            "firstname": "Ahmet",
            "lastname": "Tester",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Döner"
        }

     */

    @Test
    public void put01() {
        //Set the url
        spec.pathParams("first", "booking", "second", bookingId);

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingDataPojo expectedData = new BookingDataPojo("Ahmet", "Tester", 111, true, bookingDatesPojo, "Döner");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).body(expectedData).put("/{first}/{second}");

        response.prettyPrint();

        //Do Assertion
        BookingDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingDataPojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());

        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

    }
}
