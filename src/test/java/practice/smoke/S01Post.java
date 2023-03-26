package practice.smoke;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import pojos.BookingResponseDataPojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S01Post extends HerokuappBaseUrl {

    /*
    Given
       1) https://restful-booker.herokuapp.com/booking
       2) {
            "firstname" : "QA",
            "lastname" : "Ahmet",
            "totalprice" : 2525,
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
        Body should be like:
        {
            "bookingid": 9268,
            "booking": {
                "firstname": "QA",
                "lastname": "QAhmet",
                "totalprice": 2525,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Cağ Kebap"
            }
        }
     */
    static int bookingId;//Oluşturulan bookingId'yi sonraki classlarda kullanılmak üzere bir containera alıyoruz

    @Test
    public void post01() {
        //Set the URL
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        BookingDataPojo expectedData = new BookingDataPojo("QA", "Ahmet", 2525, true, bookingDatesPojo, "Cağ Kebap");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponseDataPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), BookingResponseDataPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.getFirstname(), actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingId = actualData.getBookingid();//Oluşturulan bookingId'yi sonraki classlarda kullanılmak üzere bir containera alıyoruz

    }
}
