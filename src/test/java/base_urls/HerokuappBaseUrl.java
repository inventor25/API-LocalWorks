package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utilities.AuthenticationHerOkuApp.generateToken;

public class HerokuappBaseUrl {
    protected RequestSpecification spec;

    //spec = new RequestSpecBuilder().addHeader("Cookie", "token=" + generateToken()).
    //setContentType(ContentType.JSON).setBaseUri("https://restful-booker.herokuapp.com").build();
    @Before//Her Test Method'undan önce çalışır
    public void setUp() { //setAccept(ContentType.JSON) bu olmasada çalışır

        //Api de restful da put update yapabilmemiz için swagger da Header kısmına Cookie ekleme şartı vardı
        //bu nedenle addheader() ile Cookie ekledik //.addHeader("Cookie",
        // "token="+generateToken()). bunu direk test te responso içerisinde de kullanabiliriz


        spec = new RequestSpecBuilder().addHeader("Cookie",
                        "token=" + generateToken()).setContentType(ContentType.JSON).
                setBaseUri("https://restful-booker.herokuapp.com").build();




    }
}

