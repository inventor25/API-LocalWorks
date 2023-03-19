package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Work11 extends GoRestBaseUrl {
    /*
           Given
               https://gorest.co.in/public/v1/users
           When
               User send GET Request
           Then
               The value of "pagination limit" is 10
                Then
               The value of "pagination page" is 250
           And
               The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
           And
               The number of users should  be 10
           And
               We have at least one "active" status
           And
               "Bhuvaneshwar Bhattacharya", "Charuvrat Ahluwalia" and "Arindam Desai" are among the users
           And
               The active users are less than or equals to inactive users

        */
    @Test
    public void work11() {
        //Set the URL
        spec.pathParam("first", "users");
        //ii) Set the expected data

        // iii) Send the request and get the response
        Response response = given().spec(spec).get("{first}");
        response.prettyPrint();

        // iv) Do assertion
        response.then().statusCode(200).body("meta.pagination.limit", equalTo(10),

                //hamcrest.Matchers.* class'ını kullanarak response dan data çağırıp ardından body() içerisinde equalTo,kullanarak eşitlik assert
                //hasSize() kullanarak data nın gelen uzunlık değerini assert yaptım.
                //"data.status" data içerisnde ki status'e gidir hasItem() method'u ile "active" içerdiğini assert yaptım
                //"data.name" data içerisnde ki name'e gider hasItems() method'u ile aşağıda ki isimleri çoğul olarak  içerdiğini assert yaptım
                //hasSize(data.) data. diyerek detaylı saydırma yapılması mümkün değildir

                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                "meta.pagination.pages", equalTo(250),
                "data", hasSize(10), "data.status", hasItem("active"), "data.name", hasItems("Bhuvaneshwar Bhattacharya", "Ms. Anish Guha", "Shiv Shukla"));


        //"active" ve "inactive" sayılarını karşılaştıralım

        // 1.Yol
        //Response ilk önce jsonPath'e çevirip sonrasında getlist() methodu ile "data.status " içinde ki stringleri bir list'e atadım
        //Karşılaştırma yapabilmemiz için sayac görevi yapacak bir variable olusturdum for each ile statusten gelen stringleri w içine atayarak
        //sayacı artırarak "active" sayısını buldum

        JsonPath jsonPath = response.jsonPath();
        List<String> status = jsonPath.getList("data.status"); //response.jsonPath() obje oluşturarak bunu kısalttık tekrar etmemesi için
        System.out.println("status = " + status);

        int activeSayisi = 0;
        for (String w : status) {
            if (w.equals("active")) {
                activeSayisi++;
            }

        }
        System.out.println("activeSayisi = " + activeSayisi);
        assertTrue(activeSayisi >= status.size() - activeSayisi);


        //"active" ve "inactive" sayılarını grovvy ile karşılaştıralım

        // 2.Yol

        // response.jsonPath() tekrar tekrar jsonPath() kullanılmaması için en doğrusu bir jsonPath objesi oluşturmaktır
        //Yukarıya dönüp JsonPath jsonPath =response.jsonPath(); bu objeyi oluşturdum

        List<String> activeList = jsonPath.getList("data.findAll{it.status=='active'}.status");
        /*

        Grovvy Kulanımı

        --data.findAll{} diyerek tüm datayı tanımlıyoruz ardından "it" anahtar sözcüğünü kullanıyoruz "it.status=='active'"
        --belirtimi yapıyorum çünkü status'u 'active' e eşit olanları seçiyoruz ardında .status olarak seçim yaptırıp liste atamasını yaptrıyorum
        Not : Grovvy language de String belirtimi için çift tırnak yerine tek tırnak kullanımı yapılır örnek ==> 'active'

         */
        System.out.println("activeList = " + activeList);

        List<String> inactiveList = jsonPath.getList("data.findAll{it.status=='active'}.status");
        System.out.println("inactiveList = " + inactiveList);

        //oluşturduğumuz listlerin size'ini karşılaştırma yaptım
        assertTrue(inactiveList.size() <= activeList.size());
    }


}
