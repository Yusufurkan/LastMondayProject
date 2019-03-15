package restAsured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class FirstRest {

    @BeforeClass
    public void setUP(){
        baseURI = "https://www.example.yufu.dev.cc/wp-json";
        basePath = "/wp/v2/";


    }

    @Test
    public void test() {
        given().
                relaxedHTTPSValidation().

        when()
                .get("https://www.example.yufu.dev.cc/wp-json/wp/v2/posts/")
        .then()
                .statusCode(200);
    }



    @Test
    public void test2() {
        given().
                relaxedHTTPSValidation().

        when()
                .get("https://www.example.yufu.dev.cc/wp-json/wp/v2/posts/20")
        .then()
                .statusCode(200)
        .and().

                body("id" , equalTo(20));
    }

    @Test
    public void getTheInnerObject() {


        // "title": {
        //            "rendered": "This is a post from website"
        //        },

        given().
                relaxedHTTPSValidation().

                when()
                .get("https://www.example.yufu.dev.cc/wp-json/wp/v2/posts/20")
                .then()
                .statusCode(200)
                .and().

                body("id" , equalTo(20)).
                body("title.rendered" ,equalTo("This is a post from website") );
    }


    @Test
    public void idTest_withLogDetail() {
        // "title": {
        //            "rendered": "This is a post from website"
        //        },

        given().
                relaxedHTTPSValidation().

        when()
                .log().all()
                .get("https://www.example.yufu.dev.cc/wp-json/wp/v2/posts/{id}", 20)
//                .get("https://www.example.yufu.dev.cc/wp-json/wp/v2/posts/24)
        .then()
                .log().all()
                .statusCode(200)
        .and().
                body("id" , equalTo(20)).
                body("title.rendered" ,equalTo("This is a post from website") );
    }

    @Test
    public void NeatURL() {

        baseURI = "https://www.example.yufu.dev.cc/wp-json";
        basePath = "/wp/v2/";


        given().
                relaxedHTTPSValidation().

                when()
                .log().all()
                .get("posts/{id}", 20);
    }



}
