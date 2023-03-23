package herokuapp_smoketest;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import utilities.ObjectMapperUtils;

import static herokuapp_smoketest.Smoke_Post01.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Smoke_Put01 extends HerokuappBaseUrl {
       /*
    Given
    1-) https://restful-booker.herokuapp.com/booking

    2-)
    {
    "firstname" : "QA",
    "lastname" : "Ahmet",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Cağ Kebap"
}
    When
        Send post request
    Then
     Status Code should be 200
     And
     Body should be like
{
    "firstname": "QA",
    "lastname": "Ahmet",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "Cağ Kebap"
}
         */
       @Test
       public void put01() {
           //Set the URL
           spec.pathParams("first","booking","second", bookingId);

           //Set the expected data
           BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
           BookingDataPojo expectedData = new BookingDataPojo("QA", "Ahmet", 111, true, bookingDatesPojo, "Cağ Kebap");
           System.out.println("expectedData = " + expectedData);

           //Send the request and get the response
           Response response= given().spec(spec).body(expectedData).put("/{first}/{second}");
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
