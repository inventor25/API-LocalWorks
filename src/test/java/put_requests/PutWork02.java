package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponsePojo;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PutWork02 extends DummyRestApiBaseUrl {
      /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "QA Ahmet",
                        "employee_salary": 2525,
                        "employee_age": 23,
                        "profile_image": "Perfect City"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                    "status": "success",
                    "data": {
                        "employee_name": "QA Ahmet",
                        "employee_salary": 2525,
                        "employee_age": 25,
                        "profile_image": "Perfect City" },

                    "message": "Successfully! Record has been updated."
                                                                            }
     */

      @Test
      //Set the URL
      public void put02() {
          spec.pathParams("first","update","second",25);

          //Set the expected data
          //Oluşturduğumuz DummyRestApiDataPojo data tipinde ki inner olan objemize yani inner expected datamıza atamaları yaptık
          DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("QA Ahmet",2525,25,"Perfect City");
          System.out.println("expectedData = " + expectedData);//Update için gönderilecek data
          //Complete olan expected datamızı bütün olarak tanımladık bütün field ler ile
          DummyRestApiResponsePojo expectedBodyPojo = new DummyRestApiResponsePojo("success",expectedData,"Successfully! Record has been updated.");


          //Sen the request get the response
          //Response ' ile Api ye oluşturduğumuz bütün olarak expected datamızı body() ile put yaptık update ettik
          Response response = given().spec(spec).when().body(expectedData).put("/{first}/{second}");
          response.prettyPrint();

          //Do Assertion
          //gelen datayı DummyRestApiResponsePojo data tipinde actaulData konteyner'ine atadık ancak öncesinde
          //ObjectMapperUtils classında ki convertJsonToJava methodu ile gelen datayı çevirme yaptık
          DummyRestApiResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponsePojo.class);
          System.out.println("actualData = " + actualData);

          assertEquals(200,response.statusCode());
          assertEquals(expectedBodyPojo.getStatus(),actualData.getStatus());
          assertEquals(expectedBodyPojo.getMessage(),actualData.getMessage());
          assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
          assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
          assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
          assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());



      }

}
