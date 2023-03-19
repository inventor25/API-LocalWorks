package post_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostWork02 extends HerokuappBaseUrl {
    /*
        Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
"firstname": "Fabio",
        "lastname": "Colque",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
    },
    "additionalneeds": "Breakfast"
}
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
   {
    "bookingid": 2126,
    "booking": {
        "firstname": "Fabio",
        "lastname": "Colque",
        "totalprice": 111,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2018-01-01",
            "checkout": "2019-01-01"
        }
    }
}
     */
    @Test
    public void postWork02() {
        //Send the URL
        spec.pathParam("first","booking");

        //Set the expected data
        HerOkuTestData obj = new HerOkuTestData();
        //HerOkuTestData Class'ından obje oluşturdum
        //Datayı post yapabilmemiz için ilk önce inner map imizi oluşturmamız gerekiyordu objemizde ki hazır method'u kullanarak
        //bookingdatesMap imizi oluşturduk
        Map<String,String> bookingdatesMap= obj.bookingdatesMapMethod("2018-01-01","2019-01-01");
        //Datayı post yapabilmemiz için objemizde ki hazır methodumuz ile gerekli değişken atamalarını yaptım
        //additionalneeds koymadığımız için objemiz içinde ki if() sayesinde null ataması yaparak bunu es geçebildik
        Map<String,Object>expectedData=obj.expectedDataMethod("Fabio","Colque",111,true,bookingdatesMap,null);
        System.out.println("expectedData = " + expectedData);

        //Send request get the response
        Response response= given().spec(spec).body(expectedData).post("{first}");
        //.post("{first}"); sayesinde response içinde post işlemini gerçekleştirmiş olduk
        //------- Bu şekilde yazdırdığımızda "Internal Server Error" aldık bu sorun .setContentType(ContentType.JSON) eklemesi
        // yapıldığında giderilmiş olur ContentType'i tekrar kod önlenmesi için HerokuappBaseUrl içerinde ki spec objemize ekledik

        response.prettyPrint();


        //Do Assertion

        //Karşıdan yani Api den gelen datamızı expectedData mızla aynı formata çevirmek için as(HashMap.class) methodunu kullandım

        Map<String,Object>actualData=response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        // Dikkat edilmesi gereken diğer konu ise Gelen data da , bizim gönderdiğimiz datadan fazla olarak bir Json daha var
        //yani bizim gönderdiğimiz başka bir json içine koyulmuş durumda
        //Bu nedenle bize gelen data object olarak geleceği için map'e çevirerek biz burada type casting yapmak zorundayız


        assertEquals(200, response.statusCode());

        //----- (Map)actualData("firstname") type casting yaparak bu şekilde çağırım yaparsak çalışmaz çünkü map içinde map vardır önce
        //booking'e gidip firstname i çağırmamız gerekmektedir
        assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

        //expectedData dan checkin'e ulaşmaya çalışsaydık type casting yapmak zorundaydık ancak elimizde
        //bookingdatesMap mapi olduğu için kod fazlalığı yapmayıp checkin i bu map ten kullandık
        assertEquals(bookingdatesMap.get("checkin"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin") );

        // 1. Map ==> (Map)actualData.get("booking") obje döner , 2. Map (Map)((Map)actualData.get("booking")).get("bookingdates") sonrasında
        //get() methodu kullanarak "checkout" datamızı string olarak alabiliyoruz
        assertEquals(bookingdatesMap.get("checkout"), ((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout") );
    }
}
