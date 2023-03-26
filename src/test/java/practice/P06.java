package practice;

import base_urls.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class P06 extends ReqresBaseUrl {
    /*
      Given
             https://reqres.in/api/unknown/
      When
           I send GET Request to the URL
      Then

           1)Status code is 200
           2)Print all pantone_values
             (Tüm pantone_value değerlerini yazdırınız)
           3)Print all ids greater than 3 on the console
             (3'ten büyük id'leri yazdırınız)
             Assert that there are 3 ids greater than 3
             (3'ten büyük 3 adet id olduğunu doğrulayınız)
           4)Print all names whose ids are less than 3 on the console
             (id'si 3'ten küçük isimleri yazdırınız)
             Assert that the number of names whose ids are less than 3 is 2
             (id'si 3'ten küçük 2 isim olduğunu doğrulayınız)
   */
    @Test
    public void get06() {
        spec.pathParam("first", "unknown");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data", hasSize(6));


//        1)Status code is 200
        response.then().assertThat().statusCode(200);

//        2)Print all pantone_values
        JsonPath json = response.jsonPath();
        List<String> pantone_values = json.getList("data.pantone_value");
        System.out.println("pantone_values = " + pantone_values);


//        3)Print all ids greater than 3 on the console
        List<Integer> ids = json.getList("data.findAll{it.id>3}.id");//Groovy
        System.out.println("ids = " + ids);//[4, 5, 6]

//        Assert that there are 3 ids greater than 3
        assertEquals(3, ids.size());

//        4)Print all names whose ids are less than 3 on the console
        List<String> names = json.getList("data.findAll{it.id<3}.name");
        System.out.println("names whose ids are less than 3 = " + names);

//        Assert that the number of names whose ids are less than 3 is 2
        assertEquals(2, names.size());

    }


//2)

  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */

    @Test
    public void post01() {
        spec.pathParam("first", "users");
        ReqresTestData reqresTestData = new ReqresTestData();//Map oluşturacak method oluşturunuz
        Map<String, String> expectedData = reqresTestData.reqresUsersSetUp("morpheus", "leader");
        System.out.println("expectedData = " + expectedData);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));
    }

//3)

/*
   "https://petstore.swagger.io/" dökümanını kullanarak 'user' oluşturacak bir otomasyon testi yazınız
    Not: Test Case'i gherkin language ile yazınız.
*/

          /*
        Given
            1) https://petstore.swagger.io/v2/user
            2) {
                  "username": "JohnDoe",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john@doe.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 123
                }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
     */

    @Test
    public void post02() {
        spec.pathParam("first", "user");
        Map<String, Object> expectedData = new HashMap<>();//Pojo class ile de payload oluşturulabilri
        expectedData.put("username", "JohnDoe");
        expectedData.put("firstName", "John");
        expectedData.put("lastName", "Doe");
        expectedData.put("email", "john@doe.com");
        expectedData.put("password", "1234");
        expectedData.put("phone", "1234");
        expectedData.put("userStatus", 123);

        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(200, actualData.get("code"));
        assertEquals("unknown", actualData.get("type"));


    }


}

