package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Work07 extends JsonPlaceHolderBaseUrl {
    /*
       Given
             https://jsonplaceholder.typicode.com/todos
     When
         I send GET Request to the URL == > URL'e Get Request gonderin
     Then
         1)Status code is 200 == > Status kodu 200 olmali
         2) ==> id si 10'dan dan küçük olanlari konsola yazdirin
           == > 9 tane id nin 10 dan küçük oldugunu dogrulayin
         3)==> id si 10 den kucuk olan tum userid lerini konsolunu yazdirin
            ==> id si 10 den kucuk olan 9 tane userId oldugunu dogrulayin
         4)Print all titles whose ids are less than 10 ==> ıd si 10 den kucuk olan tum basliklari yazdirin
           Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
           basliginin "delectus aut autem" icerdigini dogrulayin
    */
    @Test
    public void work07() {
        spec.pathParam("first", "todos");

        //Set the expected data

        //Send the request and get the response
        Response response = given().spec(spec).get("/{first}");
        //  response.prettyPrint();


        // Do Assertion
        assertEquals(200, response.statusCode());// Status code is 200

        // id si 10'dan dan küçük olanlari konsola yazdirin ==>
        JsonPath jsonPath = response.jsonPath();
        List<Object> list = jsonPath.getList("findAll{it.id<10}.id");//Groovy Language Java temelli programlama dili

        //*******jsonPath.getList("id"); kullanımı yapsaydık list içinde ki bütün id leri yazdırabiliriz ancak
        //******* groovy dilini kullanarak jsonPath.getList("findAll{it.id<10}.id"); detaylı dataların elemesini yaptırıp çağırabilirz


        assertEquals(9, list.size());//9 tane id nin 10 dan küçük oldugunu dogrulayin
        // id si 10 den kucuk olan tum userid lerini konsolunu yazdirin
        System.out.println("list = " + list);

        // Print all userIds whose ids are less than 10 on the console ==> id si 10 den kucuk olan tum userid lerini konsolunu yazdirin
        List<Integer> list1 = jsonPath.getList("findAll{it.id<10}.userId");
        System.out.println("list1 = " + list1);
        // // Assert that the number of userIds whose ids are less than 10 is 9 ==> id si 10 den kucuk olan 9 tane userId oldugunu dogrulayin
        assertEquals(9, list1.size());

        // // Assert that "delectus aut autem" is one of the titles whose id is less than 10 ==> id si 10 den kucuk olan datalarin birinin
        List<String> titleList = jsonPath.getList("findAll{it.id<10}.title");
        System.out.println("titleList = " + titleList);
        assert titleList.contains("quo adipisci enim quam ut ab");
        assertTrue("Fail uyarı için mesaj", titleList.contains("quo adipisci enim quam ut ab"));
        // basliginin "quo adipisci enim quam ut ab" icerdigini dogrulayin
    }
}
