package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Work03 {
// Given
// https://jsonplaceholder.typicode.com/todos/25
// When
// User send GET Request to the URL
//         Then
// HTTP Status Code should be 200
// And
// Response format should be “application/json”
// And
//		    “title” is “voluptas quo tenetur perspiciatis explicabo natus”,
// And
//		    “completed” is true
// And
//		    “userId” is 2

    @Test
    public void work03() {
        //1. Adım : Set the URL
        String url = "https://jsonplaceholder.typicode.com/todos/25";
        // Set the expected data
        //Sent the request get the response
        Response response = given().when().get(url);
        response.prettyPrint();
        //Do Assertion
        // 1.yol
       // response.then().statusCode(200)
       //         .contentType(ContentType.JSON).body("title", equalTo("voluptas quo tenetur perspiciatis explicabo natus"))
       //         .body("completed", equalTo(true))//"completed": true
       //         .body("userId", equalTo(2));//"userId": 2,
        //"application/json"----- (ContentType.JSON ) aynı görevi görür

        //2.Yol soft Assertion hata durumunda durmaz devam eder
          response.then().statusCode(200)
                  .contentType("application/json").body("title", equalTo("voluptas quo tenetur perspiciatis explicabo natus"), "completed", equalTo(true), "userId", equalTo(2));

          //******* equalTo() ==>> hamcrast Matcher kütüphanesinden gelir

        // Tek body() method'u içerisinde çoklu assertion yaparak soft assertion oluşturulabilir. Fail durumunda java çalışmayı durdurmaz
        //Çoklu body() method'u ile assertion yapıldığında fail durumunda fail olan body() den sonra ki kodları okumaz dolayısıyla diğer body() methodları çalışmaz

    }
}
