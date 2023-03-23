package herokuapp_smoketest;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import pojos.BookingResponseDataPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Smoke_Post01 extends HerokuappBaseUrl {

    // uthentication kimlik doğrulamadır

    /*


    //Given endpoint ve body'e ihtiyacımız var given kısmında
        //1-) https://restful-booker.herokuapp.com/booking
     2-)
    {
        "firstname" : "QA",
            "lastname" : "Ahmet",
            "totalprice" : 25,
            "depositpaid" : true,
            "bookingdates" : {
        "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
    },
        "additionalneeds" : "Cağ"
    }

     When
        Send post request
    Then
     Status Code should be 200
     And
     Body should be like
    {
    "bookingid": 3551,
    "booking": {
        "firstname": "QA",
        "lastname": "Ahmet",
        "totalprice": 25,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        },
        "additionalneeds": "Cağ"
    }
}


     */
    static int bookingId;

    @Test
    public void post01() {
        //Set the URL
        spec.pathParam("first","booking");
        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2019-01-01","2019-01-01");

        BookingDataPojo expectedData = new BookingDataPojo("QA","Ahmet",25,true,bookingDatesPojo,"Breakfast");
        System.out.println("expectedData = " + expectedData);
        //Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion
        BookingResponseDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),BookingResponseDataPojo.class);
        System.out.println("actualPojo = " + actualData);

        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckout());
        bookingId = actualData.getBookingid();
    }

}
