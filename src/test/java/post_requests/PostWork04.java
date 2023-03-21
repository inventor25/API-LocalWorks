package post_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDataPojo;
import pojos.BookingDatesPojo;
import pojos.BookingResponseDataPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWork04 extends HerokuappBaseUrl {
    /*
    Pojo Class'larının tercih edilmemsinin sebepleri
    -- Daha güvenli olması
    -- Encapsulation edilebilmesi
    -- Daha hızlı olması
     */
      /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                "firstname": "QA",
                "lastname": "Ahmet",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2023-09-21",
                    "checkout": "2024-12-21"
                 },
                 "additionalneeds": "Cağ Kebap"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
    "bookingid": 3526,
    "booking": {
        "firstname": "QA",
        "lastname": "Ahmet",
        "totalprice": 100,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2023-09-21",
            "checkout": "2024-12-21"
        },
        "additionalneeds": "Cağ Kebap"
    }
}

          i)   Set the URL
//        ii)  Set the expected data
//        iii) Send the request and get the response
//        iv)  Do assertion

     */
      @Test
      public void postWork04() {
//        i)   Set the URL
          spec.pathParam("first","booking");

//        ii)  Set the expected data


          //BookingDatesPojo class'ında inner map için dizayn yapıp pojo oluşturduk ve elimizde bulunan
          //datalarımızı orda tanımladık ve bu class'ımız da kullanabilmemiz için o class data tipinde objemizi oluşturup atamaları yaptık
          BookingDatesPojo bookingDatesPojo= new BookingDatesPojo("2023-09-21","2024-12-21");

          //Expected datamız için BookingDataPojo class'ını oluşturduk yani outher map için de diyebiliriz ve bu class'ta
          //obje oluşturup değer ataması yaptığımızda inner map 'imiz için bookingDatesPojo field ataması yaptık
          BookingDataPojo expectedData= new BookingDataPojo("QA","Ahmet",100,true,bookingDatesPojo,"Cağ Kebap");
          System.out.println("expectedData = " + expectedData);

//        iii) Send the request and get the response
          Response response = given().spec(spec).when().body(expectedData).post("{first}");
          response.prettyPrint();
//        iv)  Do assertion

          BookingResponseDataPojo actualData= response.as(BookingResponseDataPojo.class);
          System.out.println("actualData = " + actualData);

          assertEquals(200,response.statusCode());
          // assertEquals("Ali",actualData.getBooking().getFirstname());
          assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
          assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
          assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
          assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
          assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
          assertEquals(bookingDatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
          assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


      }

}
