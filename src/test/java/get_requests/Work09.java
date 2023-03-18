package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Work09 extends HerokuappBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/454
      When
          I send GET Request to the url
      Then
          Response body should be like that;
          {
    "firstname": "Josh",
    "lastname": "Allen",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2018-01-01",
        "checkout": "2019-01-01"
    },
    "additionalneeds": "midnight snack"
}
   */
    /*
        Payload data oluşturmak için ilk önce  inner yani içerde ki map i oluşturmamız gerekmemktedir.
      Bu inner map String key ve String value olacak şekilde ayarlanabilir çünkü  "checkin": "2018-01-01",
      bu 2 değer de string'tir.
        Expected data için oluşturmamız gereken map String key ve Object value olacak şekilde tasarlanmalıdır
     çünkü value'lar içeriside value'lar string,int,boolean ve map data türünde derğerler vardır

      */
    @Test
     public void get09() {
         spec.pathParams("first","booking","second",1009);
         //Set the expected data

         Map<String, String> bookingdatesMap= new HashMap<>();
         bookingdatesMap.put("checkin","2018-01-01");
         bookingdatesMap.put("checkout","2019-01-01");

         Map<String,Object> expectedData= new HashMap<>();
         expectedData.put("firstname","Josh");
         expectedData.put("lastname","Allen");
         expectedData.put("totalprice",111);
         expectedData.put("depositpaid",true);
         expectedData.put("bookingdates",bookingdatesMap);
         expectedData.put("additionalneeds","super bowls");


         //******Bu şekilde expected data yani map  içerisinden value lar ile manipülasyon yapabiliriz
        System.out.println(((Integer)expectedData.get("totalprice"))+1);



         //Set the request and get the response
        Response response= given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion


        // Json olarak Gelen Response ilk önce map a çevirerek Java dilinde expected datamızla kıyas yapmamız sağlandı

         Map<String,Object> actualData = response.as(HashMap.class);
         System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), (actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));


        //Burada Assertion yapabilmemiz için actualData.get("bookingdates") datasının data tipi Object'tir yani
        //actualData ve expectedData .get() dediğimizde Object data türü return eder bu nedenle type casting yapmak zorunda kaldırk
        //Object datasını map 'a casting yaparak çevirdik.
        //assertEquals(bookingdatesMap.get("checkin") burda ise "checkin" datasını direk inner map'ten çağırarak 2. bir casting yapmanın önüne geçmş olduk
        //yani "checkin" eğer assert için bunu expectedData.get diyerek çağırsaydık cast yapmak zorunda kalacaktık
        assertEquals(bookingdatesMap.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
     }

    @Test
    public void get09dynamic() {
        spec.pathParams("first", "booking", "second", 794);
        //Set the expected data

        //HerOkuTestData class'ında dynamic bir inner map ve expectedData method'u oluşturdum
        //HerOkuTestData classından bir obje oluşturarak inner map ve expected datamızı map olarak dinamik bir oluşturma yaptım

        HerOkuTestData obj = new HerOkuTestData();

        Map<String, String> bookingdatesMap = obj.bookingdatesMapMethod("2018-01-01", "2019-01-01");


        Map<String, Object> expectedData = obj.expectedDataMethod("John", "Smith", 111, true, bookingdatesMap, "Dinner");


        //Set the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        //Response'dan gelen dataları as(HashMap.class) methodunu kullanarak java dilinde map'a çevirdik
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), (actualData.get("totalprice")));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));


        //Burada Assertion yapabilmemiz için actualData.get("bookingdates") datasının data tipi Object'tir yani
        //actualData ve expectedData .get() dediğimizde Object data türü return eder bu nedenle type casting yapmak zorunda kaldırk
        //Object datasını map 'a casting yaparak çevirdik.
        //assertEquals(bookingdatesMap.get("checkin") burda ise "checkin" datasını direk inner map'ten çağırarak 2. bir casting yapmanın önüne geçmş olduk
        //yani "checkin" eğer assert için bunu expectedData.get diyerek çağırsaydık cast yapmak zorunda kalacaktık


        assertEquals(bookingdatesMap.get("checkin"), ((Map) actualData.get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesMap.get("checkout"), ((Map) actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

    }

}
