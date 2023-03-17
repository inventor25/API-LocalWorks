package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Work06 extends HerokuappBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking/6
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
  {
   "firstname": "Susan",
    "lastname": "Ericsson",
    "totalprice": 281,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2016-02-16",
        "checkout": "2020-08-22"
    }
}
     */
    @Test
    public void work06() {
        //Set the URL
        spec.pathParams("first", "booking", "second",6);

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        //1.Yol
        //response.then()
        //        .statusCode(200).contentType(ContentType.JSON).body("firstname"
        //                ,equalTo("Mary"),"lastname",equalTo("Ericsson")//Matcher classından body() methodundan sonra  equalTo() methodu kullanılarak doğrulama yaptım
        //         ,"totalprice",equalTo(193),"depositpaid",equalTo(true)
        //         ,"bookingdates.checkin",equalTo("2015-02-17"),"bookingdates.checkout",equalTo("2019-05-16"));



        //2.yol Response imizi alıp JsonPath'e çevirip JsonPath datasına koyup çalışabiliriz. JsonPath class'ı --rastassured.path.json-- kütüphanesinden gelir
        JsonPath jsonPath = response.jsonPath(); //gelen response dan jsonPath çevrimi yaparak içinde ki datayı kullanabiliyoruz

       // assertEquals("Mark",jsonPath.getString("firstname"));//jsonPath.getString("firstname") ==> json kullanıp getString ile içine data path'i girip datayı aldık ve assert yaptık
       // assertEquals("Smith",jsonPath.getString("lastname"));
       // assertEquals(552,jsonPath.getInt("totalprice"));
       // assertFalse(jsonPath.getBoolean("depositpaid"));//AssertFalse kullanarak jsonPath ile aldığımız datanın value nin False olduğunu doğruladık
       // assertEquals("2016-09-30",jsonPath.getString("bookingdates.checkin"));
       // assertEquals("2017-06-16",jsonPath.getString("bookingdates.checkout"));//JS diline benzer gibi .checkout kullarak string alıp expected değeri ile karşılaştırma yaptk



        //3.Yol
        //Softassertion kullanmamız için pom 'a TestNG dependency eklememiz gerekliydi eklemeyi yaptım
        //1. Soft Assert objesi oluştur
        SoftAssert softAssert = new SoftAssert(); //Soft Assert kullanmamız için obje oluşturdum

        //Test New Generation Soft Assert TestNG


        //2. Assertion
        softAssert.assertEquals(jsonPath.getString("firstname"),"Susan","firstname uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Wilson","lastname uyuşmadı");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),123,"totalprice uyuşmadı");
        softAssert.assertFalse(jsonPath.getBoolean("depositpaid"),"depositpaid uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2017-12-08","checkin uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2020-12-08","checkout uyuşmadı");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds uyuşmadı");

        //3. softAssertion.assertAll() ile doğrulamayı kontrol et. Aksi taktirde test hep "PASS" olur
        softAssert.assertAll();

        //*******Soft Assertion kullanıldığında test içinde steplerden birisi exception atsa bile diğer adımları da test etmeye devam eder
        //*******Bu nedenle çok tercih edilmez çünkü Tester genelde hata anında testin durması ve hatanın nerde olduğunu görüp düzeltmesi tercih eder

    }
}

