package post_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponsePojo;
import utilities.ObjectMapperUtils;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyRestApiBaseUrl {
     /*
       URL: https://dummy.restapiexample.com/api/v1/create

       Request body:
                     {
                        "employee_name": "QA Ahmet",
                        "employee_salary": 252525,
                        "employee_age": 25,
                        "profile_image": "Cold City",
                        "id": 4891
                     }

       Test Case: Type by using Gherkin Language

         HTTP Request Method: POST Request
       Assert:

                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

      /*
      Given
        URL: https://dummy.restapiexample.com/api/v1/create
      And
      Request body:
         {
           "employee_name": "QA Ahmet",
                    "employee_salary": 252525,
                    "employee_age": 25,
                    "profile_image": "Cold Man",
                    "id": 4891
         }
      When
       HTTP Request Method: POST Request
      Then
          Status code is 200
      And
          Response body should be like the following
            {
                "status": "success",
                "data": {
                    "employee_name": "QA Ahmet",
                    "employee_salary": 252525,
                    "employee_age": 25,
                    "profile_image": "Cold Man",
                    "id": 4891
                },
                "message": "Successfully! Record has been added."
            }
     */


    @Test
    public void post06() {
        //Set the url
        spec.pathParam("first", "create");

        //Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("QA Ahmet", 252525, 25, "Cold Man");
        System.out.println("expectedData = " + expectedData);
        DummyRestApiResponsePojo responseBodyPojoExpected = new DummyRestApiResponsePojo("success", expectedData, "Successfully! Record has been added.");

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do Assertion
        DummyRestApiResponsePojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponsePojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(responseBodyPojoExpected.getStatus(), actualData.getStatus());
        assertEquals(expectedData.getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(expectedData.getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(expectedData.getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getProfile_image(), actualData.getData().getProfile_image());
        assertEquals(responseBodyPojoExpected.getMessage(), actualData.getMessage());
    }
}
