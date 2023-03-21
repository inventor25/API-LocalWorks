package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work12 extends HerokuappBaseUrl {

       /*
    Given
        https://restful-booker.herokuapp.com/booking/142
    When
   I send GET Request to the URL
  Then
   Status code is 200
And
   Response body is like:
              {
                    "firstname": "Josh",
                    "lastname": "Allen",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                    },
                    "additionalneeds": "super bowls"
                }
 */

    @Test
    public void getWork12() {
        //Set the URL
        spec.pathParams("first","booking","second",142);

        //Set the expected data
        //BookingDatesPojo class'ında hazır oluşturulmuş inner map imiz için objeyi oluşturup atamasını yaptık
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01","2019-01-01");

        //BookingDataPojo class'ında hazır oluşturulmuş datalarımız için expectedData mızı oluşturup atamalarını yaptık
        BookingDataPojo expectedData = new BookingDataPojo("Josh","Allen",111,true,bookingDatesPojo,"super bowls");
        System.out.println("expectedData = " + expectedData);
        //Send the request and get the response

        //Response mizdan belirttiğimiz datamızı çağırdık
        Response response =given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //DO Assertion
        //Response den çağırdığıöız datamızı as(BookingDataPojo.class) method'unu kullanarak casting yapıp actualData olarak tanımladık
        BookingDataPojo actualData =response.as(BookingDataPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());


    }
}
